﻿<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
<meta name="csrf_token" content="O/B5/VpJNYWtoIPvBxmtho19Sdx7Lw6V"/>
<meta name="csrf_token" content="9F/WiFiCaqkhkz5cwZmDJu5zPN3MGHnW"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta http-equiv='Pragma' content='no-cache'/>

        <script type="text/javascript">
            /*
             JQuery is not compatible with PSP & NDSi
             script execution will stop when the jquery import.
             we should put the following script before the jquery is imported
             */
            var hardwarePlatform = navigator.platform.toLowerCase();
            var agent = navigator.userAgent.toLowerCase();
            var isPsp = (agent.indexOf("playstation") != -1);
            var isNdsi = (agent.indexOf("nintendo dsi") != -1);
            if (isPsp || isNdsi) {
                window.location.href = "notsupported.html";
            }
        </script>

        <script type="text/javascript" src="../lib/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="../lib/log4javascript_lite.js"></script>
        <script type="text/javascript" src="../js/redirect.js"></script>

        <title></title>

        <script type="text/javascript">
            var DEFAULT_GATEWAY_IP = "192.168.1.1";
            var DEFAULT_GATEWAY_DOMAIN = new Array();
			var GATEWAY_DOMAIN = new Array();
            var AJAX_HEADER = '../';
            var AJAX_TAIL = '';
            var AJAX_TIMEOUT = 30000;
            
            var MACRO_NO_SIM_CARD = '255';
            var MACRO_CPIN_FAIL = '256';
            var MACRO_PIN_READY = '257';
            var MACRO_PIN_DISABLE = '258';
            var MACRO_PIN_VALIDATE = '259';
            var MACRO_PIN_REQUIRED = '260';
            var MACRO_PUK_REQUIRED = '261';

            var log = log4javascript.getNullLogger();            
            var hardwarePlatform = navigator.platform.toLowerCase();
            var agent = navigator.userAgent.toLowerCase();
            
            var isIpod = hardwarePlatform.indexOf("ipod") != -1;
            var isIphone = hardwarePlatform.indexOf("iphone") != -1;
            var isIpad =  hardwarePlatform.indexOf("ipad") != -1;
            var isAndroid = agent.indexOf("android") !=-1;
            
            log.debug("INDEX : hardwarePlatform = " + hardwarePlatform);
            log.debug("INDEX : agent = " + agent);
            function gotoPageWithoutHistory(url) {
                log.debug('MAIN : gotoPageWithoutHistory(' + url + ')');
                window.location.replace(url);
            }

            // internal use only
            function _recursiveXml2Object($xml) {
                if ($xml.children().size() > 0) {
                    var _obj = {};
                    $xml.children().each(function() {
                        var _childObj = ($(this).children().size() > 0) ? _recursiveXml2Object($(this)) : $(this).text();
                        if ($(this).siblings().size() > 0 && $(this).siblings().get(0).tagName == this.tagName) {
                            if (_obj[this.tagName] == null) {
                                _obj[this.tagName] = [];
                            }
                            _obj[this.tagName].push(_childObj);
                        }
                        else {
                            _obj[this.tagName] = _childObj;
                        }
                    });
                    return _obj;
                }
                else {
                    return $xml.text();
                }
            }

            // convert XML string to an Object.
            // $xml, which is an jQuery xml object.
            function xml2object($xml) {
                var obj = new Object();
                if ($xml.find('response').size() > 0) {
                    var _response = _recursiveXml2Object($xml.find('response'));
                    obj.type = 'response';
                    obj.response = _response;
                }
                else if ($xml.find('error').size() > 0) {
                    var _code = $xml.find('code').text();
                    var _message = $xml.find('message').text();
                    log.warn('MAIN : error code = ' + _code);
                    log.warn('MAIN : error msg = ' + _message);
                    obj.type = 'error';
                    obj.error = {
                        code: _code,
                        message: _message
                    };
                }
                else if ($xml.find('config').size() > 0) {
                    var _config = _recursiveXml2Object($xml.find('config'));
                    obj.type = 'config';
                    obj.config = _config;
                }
                else {
                    obj.type = 'unknown';
                }
                return obj;
            }

            function getAjaxData(urlstr, callback_func, options) {
                var myurl = AJAX_HEADER + urlstr + AJAX_TAIL;
                var isAsync = true;
                var nTimeout = AJAX_TIMEOUT;
                var errorCallback = null;
            
                if (options) {
                    if (options.sync) {
                        isAsync = (options.sync == true) ? false : true;
                    }
                    if (options.timeout) {
                        nTimeout = parseInt(options.timeout, 10);
                        if (isNaN(nTimeout)) {
                            nTimeout = AJAX_TIMEOUT;
                        }
            
                    }
                    errorCallback = options.errorCB;
                }
                var headers = {};
                headers['__RequestVerificationToken'] = g_requestVerificationToken;
            
                $.ajax({
                    async: isAsync,
                    headers: headers,
                    //cache: false,
                    type: 'GET',
                    timeout: nTimeout,
                    url: myurl,
                    //dataType: ($.browser.msie) ? "text" : "xml",
                    error: function(XMLHttpRequest, textStatus) {
                        try {
                            if (jQuery.isFunction(errorCallback)) {
                                errorCallback(XMLHttpRequest, textStatus);
                            }
                            log.error('MAIN : getAjaxData(' + myurl + ') error.');
                            log.error('MAIN : XMLHttpRequest.readyState = ' + XMLHttpRequest.readyState);
                            log.error('MAIN : XMLHttpRequest.status = ' + XMLHttpRequest.status);
                            log.error('MAIN : textStatus ' + textStatus);
                        }
                        catch (exception) {
                            log.error(exception);
                        }
                    },
                    success: function(data) {
                        log.debug('MAIN : getAjaxData(' + myurl + ') sucess.');
                        log.trace(data);
                        var xml;
                        if (typeof data == 'string' || typeof data == 'number') {
                            if (-1 != this.url.indexOf('/api/sdcard/sdcard')) {
                                data = sdResolveCannotParseChar(data);
                            }
                            if (!window.ActiveXObject) {
                                var parser = new DOMParser();
                                xml = parser.parseFromString(data, 'text/xml');
                            }
                            else {
                                //IE
                                xml = new ActiveXObject('Microsoft.XMLDOM');
                                xml.async = false;
                                xml.loadXML(data);
                            }
                        }
                        else {
                            xml = data;
                        }
                        if (typeof callback_func == 'function') {
                            callback_func($(xml));
                        }
                        else {
                            log.error('callback_func is undefined or not a function');
                        }
                    }
                });
            }

            function getConfigData(urlstr, callback_func, options) {
                var myurl = '../' + urlstr + '';
                //var myurl = urlstr + "";
                var isAsync = true;
                var nTimeout = AJAX_TIMEOUT;
                var errorCallback = null;

                if (options) {
                    if (options.sync) {
                        isAsync = (options.sync == true) ? false : true;
                    }
                    if (options.timeout) {
                        nTimeout = parseInt(options.timeout, 10);
                        if (isNaN(nTimeout)) {
                            nTimeout = AJAX_TIMEOUT;
                        }
                    }
                    errorCallback = options.errorCB;
                }

                $.ajax({
                    async: isAsync,
                    //cache: false,
                    type: 'GET',
                    timeout: nTimeout,
                    url: myurl,
                    //dataType: ($.browser.msie) ? "text" : "xml",
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        try {
                            log.debug('MAIN : getConfigData(' + myurl + ') error.');
                            log.error('MAIN : XMLHttpRequest.readyState = ' + XMLHttpRequest.readyState);
                            log.error('MAIN : XMLHttpRequest.status = ' + XMLHttpRequest.status);
                            log.error('MAIN : textStatus ' + textStatus);
                            if (jQuery.isFunction(errorCallback)) {
                                errorCallback(XMLHttpRequest, textStatus);
                            }
                        }
                        catch (exception) {
                            log.error(exception);
                        }
                    },
                    success: function(data) {
                        log.debug('MAIN : getConfigData(' + myurl + ') success.');
                        log.trace(data);
                        var xml;
                        if (typeof data == 'string' || typeof data == 'number') {
                            if (!window.ActiveXObject) {
                                var parser = new DOMParser();
                                xml = parser.parseFromString(data, 'text/xml');
                            }
                            else {
                                //IE
                                xml = new ActiveXObject('Microsoft.XMLDOM');
                                xml.async = false;
                                xml.loadXML(data);
                            }
                        }
                        else {
                            xml = data;
                        }
                        if (typeof callback_func == 'function') {
                            callback_func($(xml));
                        }
                        else {
                            log.error('callback_func is undefined or not a function');
                        }
                    }
                });
            }

            function getDomain(){
                getConfigData("config/lan/config.xml", function($xml){
                    var ret = xml2object($xml);
                    if(ret.type == "config")
                    {
                        DEFAULT_GATEWAY_DOMAIN.push(ret.config.landns.hgwurl.toLowerCase());
						if( typeof(ret.config.landns.mcdomain) != 'undefined' )
						{
							GATEWAY_DOMAIN.push(ret.config.landns.mcdomain.toLowerCase());
						}
                    }
                }, {
                   sync: true
                });
            }

            function getQueryStringByName(item) {
                var svalue = location.search.match(new RegExp('[\?\&]' + item + '=([^\&]*)(\&?)', 'i'));
                return svalue ? svalue[1] : svalue;
            }
            
            function isHandheldBrowser() {
                var bRet = false;
                if(0 == login_status){
                    return bRet;
                }               
                if (isIphone || isIpod) {
                    log.debug("INDEX : current browser is iphone or ipod.");
                    bRet = true;
                }
                else if (isPsp) {
                    log.debug("INDEX : current browser is psp.");
                    bRet = true;
                }
				else if (isIpad) {
					log.debug("INDEX : current browser is ipad.");
					bRet = true;
				}
                else if (isAndroid) {
                    log.debug("INDEX : current browser is android.");
                    bRet = true;
                }
                else {
                    log.debug("INDEX : screen.height = " + screen.height);
                    log.debug("INDEX : screen.width = " + screen.width);
                    if (screen.height <= 320 || screen.width <= 320) {
                        bRet = true;
                        log.debug("INDEX : current browser screen size is small.");
                    }
                }
                log.debug("INDEX : isHandheldBrowser = " + bRet);
                return bRet;
            }

            function update_openNewWindow () {
                if (window.location.href.indexOf('?updataredirect=') > -1) {
                    var tmpUrl = window.location.href.substring(window.location.href.indexOf("?updataredirect="));
                    var newUrl = "http://" + tmpUrl.substring(tmpUrl.indexOf("?updataredirect=") + 16);

                    document.getElementById("update_newPage").setAttribute("href", newUrl);
                    document.getElementById("update_newPage").setAttribute("target", "_blank");
                    if($.browser.msie) {
                        $("#update_newPage").get(0).click();
                    } else {
                        var evt = document.createEvent("MouseEvents");  
                        evt.initEvent("click", true, true);  
                        document.getElementById('update_newPage').dispatchEvent(evt);
                    }
                 }
            }
            
            var g_requestVerificationToken = '';
            function getAjaxToken() {
                 getAjaxData('api/webserver/token', function($xml) {
                    var ret = xml2object($xml);
                     if ('response' == ret.type) {
                         g_requestVerificationToken = ret.response.token;
                         
                     }
                }, {
                       sync: true
                });
            }
            
            getAjaxToken();

            var gatewayAddr = "";
            var conntection_status = null;
            var service_status = null;
            var login_status = null;
            // get current settings gateway address
            getAjaxData("api/dhcp/settings", function($xml) {
                var ret = xml2object($xml);
                if ("response" == ret.type) {
                    gatewayAddr = ret.response.DhcpIPAddress;
                }
            }, {
                sync : true
            }
            );

            // get connection status
            getAjaxData("api/monitoring/status", function($xml) {
                var ret = xml2object($xml);
                if ("response" == ret.type) {
                    conntection_status = parseInt(ret.response.ConnectionStatus,10);
                    service_status = parseInt(ret.response.ServiceStatus,10);
                }
            }, {
                sync : true
            }
            );
                        // get connection status
            getAjaxData('config/global/config.xml', function($xml) {
            var config_ret = xml2object($xml);  
            login_status = config_ret.config.login;
                
            }, {
                sync : true
            }
            );
            if ("" == gatewayAddr) {
                gatewayAddr = DEFAULT_GATEWAY_IP;
            }

            var href = "http://" + DEFAULT_GATEWAY_IP;
            try {
                href = window.location.href;
            }
            catch(exception) {
                href = "http://" + DEFAULT_GATEWAY_IP;
            }
            // get incoming url from querystring
            var incoming_url = href.substring(href.indexOf("?url=") + 5);
            // truncate http://
            if (incoming_url.indexOf("//") > -1) {
                incoming_url = incoming_url.substring(incoming_url.indexOf("//") + 2);
            }
            //get *.html
            var incoming_html = "";
            if (incoming_url.indexOf(".html") > -1) {
                incoming_html = incoming_url.substring(incoming_url.lastIndexOf("/") + 1, incoming_url.length);
            }
            // truncate tail
            if (incoming_url.indexOf("/") !=  -1) {
                incoming_url = incoming_url.substring(0, incoming_url.indexOf("/"));
            }

            incoming_url = incoming_url.toLowerCase();
            var bIsSmallPage = isHandheldBrowser();
            // var prefix = "http://" + gatewayAddr;
            var g_indexIncomingUrlIsGateway = false;
            // if incoming url == 192.168.1.1 or MobileWifi.home then goto login
            // page
            window.name = getQueryStringByName("version");
            //check login status
            var LOGIN_STATES_SUCCEED = "0";
            var userLoginState = LOGIN_STATES_SUCCEED;
            getAjaxData('api/user/state-login', function($xml) {
                var ret = xml2object($xml);
                if (ret.type == 'response') {
                    userLoginState=ret.response.State;
                }
            }, {
                sync: true
            });

            $(document).ready(function() {

                update_openNewWindow();

                if(true == bIsSmallPage) {
                    if (userLoginState != LOGIN_STATES_SUCCEED) {
						getAjaxData('config/global/config.xml', function($xml) {
							var config_ret = xml2object($xml);
							if(config_ret.type == 'config') {
								if(config_ret.config.commend_enable == '1') {
									gotoPageWithoutHistory("../html/commend.html");
									g_indexIncomingUrlIsGateway = true;
								}else {									
									g_indexIncomingUrlIsGateway = redirectOnCondition("",'index');
								}
							}
						},{
							sync: true
						});
                        
                    } else {                    
						g_indexIncomingUrlIsGateway = redirectOnCondition("",'index');
						if(!g_indexIncomingUrlIsGateway) {
                            getAjaxData("api/device/basic_information", function($xml) {
                                var basic_ret = xml2object($xml);
                                if('response' == basic_ret.type) {
                                    var basic_info = basic_ret.response;
                                    if(basic_info.restore_default_status == '1' && basic_info.classify != 'hilink') {
                                       gotoPageWithoutHistory("quicksetup.html");
                                       g_indexIncomingUrlIsGateway = true;
                                   }
                                }
                            },{
                                sync: true
                            });
                        }                   
                    }
                } else {
                    g_indexIncomingUrlIsGateway = redirectOnCondition("",'index');
                    if(!g_indexIncomingUrlIsGateway) {
                        getAjaxData("api/device/basic_information", function($xml) {
                            var basic_ret = xml2object($xml);
                            if('response' == basic_ret.type) {
                                var basic_info = basic_ret.response;
                                if(basic_info.restore_default_status == '1' && basic_info.classify != 'hilink') {
                                   gotoPageWithoutHistory("quicksetup.html");
                                   g_indexIncomingUrlIsGateway = true;
                               }
                            }
                        },{
                            sync: true
                        });
                    }
                }

                $( function() {
                    getDomain();
                    if (g_indexIncomingUrlIsGateway) {
                        return;
                    }
                    else if (conntection_status == 901 && service_status == 2) {
                        if ((incoming_url.indexOf(gatewayAddr)==0)|| (incoming_url.indexOf(DEFAULT_GATEWAY_DOMAIN)==0) 
						 || (incoming_url.indexOf(GATEWAY_DOMAIN)==0)){
                               gotoPageWithoutHistory("home.html");
                         }
                         else {
                              gotoPageWithoutHistory("opennewwindow.html");
                         }
                    }
                    else {
                        gotoPageWithoutHistory("home.html");
                    }
                });

             });
        </script>
    </head>

    <body style="background-color: #FFFFFF;">
        <div>
            <a id="update_newPage" href="#" target="_blank"></a>
        </div>
        <noscript>
            Sorry, your browser does not support javascript.
        </noscript>
    </body>
</html>
