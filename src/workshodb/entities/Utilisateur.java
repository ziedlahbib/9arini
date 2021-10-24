/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshodb.entities;

import java.util.Date;


/**
 *
 * @author bhk
 */
public abstract class Utilisateur {
    private int utilisateurID,utilisateurphone;
    private String utilisateurPdp,utilisateurNom,utilisateurPrenom,utilisateurAddress,utilisateurPays,utilisateurGenre,utilisateurAddressEmail,utilisateurMDP,utilisateurRole,utilisateurOrganisme,utilisateurFonction,utilisateurSoftskills,nomEntreprise,EntrepreneurSiteWeb,EntrepreneurUsage;
    private Date utilisateurDDN;

    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public int getUtilisateurphone() {
        return utilisateurphone;
    }

    public void setUtilisateurphone(int utilisateurphone) {
        this.utilisateurphone = utilisateurphone;
    }

    public String getUtilisateurPdp() {
        return utilisateurPdp;
    }

    public void setUtilisateurPdp(String utilisateurPdp) {
        this.utilisateurPdp = utilisateurPdp;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    public void setUtilisateurPrenom(String utilisateurPrenom) {
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public String getUtilisateurAddress() {
        return utilisateurAddress;
    }

    public void setUtilisateurAddress(String utilisateurAddress) {
        this.utilisateurAddress = utilisateurAddress;
    }

    public String getUtilisateurPays() {
        return utilisateurPays;
    }

    public void setUtilisateurPays(String utilisateurPays) {
        this.utilisateurPays = utilisateurPays;
    }

    public String getUtilisateurGenre() {
        return utilisateurGenre;
    }

    public void setUtilisateurGenre(String utilisateurGenre) {
        this.utilisateurGenre = utilisateurGenre;
    }

    public String getUtilisateurAddressEmail() {
        return utilisateurAddressEmail;
    }

    public void setUtilisateurAddressEmail(String utilisateurAddressEmail) {
        this.utilisateurAddressEmail = utilisateurAddressEmail;
    }

    public String getUtilisateurMDP() {
        return utilisateurMDP;
    }

    public void setUtilisateurMDP(String utilisateurMDP) {
        this.utilisateurMDP = utilisateurMDP;
    }

    public String getUtilisateurRole() {
        return utilisateurRole;
    }

    public void setUtilisateurRole(String utilisateurRole) {
        this.utilisateurRole = utilisateurRole;
    }

    public String getUtilisateurOrganisme() {
        return utilisateurOrganisme;
    }

    public void setUtilisateurOrganisme(String utilisateurOrganisme) {
        this.utilisateurOrganisme = utilisateurOrganisme;
    }

    public String getUtilisateurFonction() {
        return utilisateurFonction;
    }

    public void setUtilisateurFonction(String utilisateurFonction) {
        this.utilisateurFonction = utilisateurFonction;
    }

    public String getUtilisateurSoftskills() {
        return utilisateurSoftskills;
    }

    public void setUtilisateurSoftskills(String utilisateurSoftskills) {
        this.utilisateurSoftskills = utilisateurSoftskills;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getEntrepreneurSiteWeb() {
        return EntrepreneurSiteWeb;
    }

    public void setEntrepreneurSiteWeb(String EntrepreneurSiteWeb) {
        this.EntrepreneurSiteWeb = EntrepreneurSiteWeb;
    }

    public String getEntrepreneurUsage() {
        return EntrepreneurUsage;
    }

    public void setEntrepreneurUsage(String EntrepreneurUsage) {
        this.EntrepreneurUsage = EntrepreneurUsage;
    }

    public Date getUtilisateurDDN() {
        return utilisateurDDN;
    }

    public void setUtilisateurDDN(Date utilisateurDDN) {
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.utilisateurSoftskills = utilisateurSoftskills;
        this.nomEntreprise = nomEntreprise;
        this.EntrepreneurSiteWeb = EntrepreneurSiteWeb;
        this.EntrepreneurUsage = EntrepreneurUsage;
        this.utilisateurDDN = utilisateurDDN;
    }

    
    public Utilisateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.nomEntreprise = nomEntreprise;
        this.EntrepreneurSiteWeb = EntrepreneurSiteWeb;
        this.EntrepreneurUsage = EntrepreneurUsage;
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, Date utilisateurDDN) {
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.utilisateurSoftskills = utilisateurSoftskills;
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, Date utilisateurDDN) {
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole) {
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
    }

    public Utilisateur(int utilisateurID, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        this.utilisateurID = utilisateurID;
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.utilisateurSoftskills = utilisateurSoftskills;
        this.nomEntreprise = nomEntreprise;
        this.EntrepreneurSiteWeb = EntrepreneurSiteWeb;
        this.EntrepreneurUsage = EntrepreneurUsage;
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur(int utilisateurID, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String utilisateurSoftskills, Date utilisateurDDN) {
        this.utilisateurID = utilisateurID;
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.utilisateurSoftskills = utilisateurSoftskills;
        this.utilisateurDDN = utilisateurDDN;
    }

   

    public Utilisateur(int utilisateurID, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, String nomEntreprise, String EntrepreneurSiteWeb, String EntrepreneurUsage, Date utilisateurDDN) {
        this.utilisateurID = utilisateurID;
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.nomEntreprise = nomEntreprise;
        this.EntrepreneurSiteWeb = EntrepreneurSiteWeb;
        this.EntrepreneurUsage = EntrepreneurUsage;
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur(int utilisateurID, int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, Date utilisateurDDN) {
        this.utilisateurID = utilisateurID;
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.utilisateurDDN = utilisateurDDN;
    }

  
    public Utilisateur(String utilisateurNom, String utilisateurPrenom) {
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public Utilisateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, String utilisateurOrganisme, String utilisateurFonction, Date utilisateurDDN) {
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurOrganisme = utilisateurOrganisme;
        this.utilisateurFonction = utilisateurFonction;
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur(int utilisateurphone, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom, String utilisateurAddress, String utilisateurPays, String utilisateurGenre, String utilisateurAddressEmail, String utilisateurMDP, String utilisateurRole, Date utilisateurDDN) {
        this.utilisateurphone = utilisateurphone;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
        this.utilisateurAddress = utilisateurAddress;
        this.utilisateurPays = utilisateurPays;
        this.utilisateurGenre = utilisateurGenre;
        this.utilisateurAddressEmail = utilisateurAddressEmail;
        this.utilisateurMDP = utilisateurMDP;
        this.utilisateurRole = utilisateurRole;
        this.utilisateurDDN = utilisateurDDN;
    }

    public Utilisateur() {
    }
   
    
    @Override
    public String toString() {
        return "Utilisateur{" + "utilisateurID=" + utilisateurID + ", utilisateurphone=" + utilisateurphone + ", utilisateurPdp=" + utilisateurPdp + ", utilisateurNom=" + utilisateurNom + ", utilisateurPrenom=" + utilisateurPrenom + ", utilisateurAddress=" + utilisateurAddress + ", utilisateurPays=" + utilisateurPays + ", utilisateurGenre=" + utilisateurGenre + ", utilisateurAddressEmail=" + utilisateurAddressEmail + ", utilisateurMDP=" + utilisateurMDP + ", utilisateurRole=" + utilisateurRole + ", utilisateurOrganisme=" + utilisateurOrganisme + ", utilisateurFonction=" + utilisateurFonction + ", utilisateurSoftskills=" + utilisateurSoftskills + ", nomEntreprise=" + nomEntreprise + ", EntrepreneurSiteWeb=" + EntrepreneurSiteWeb + ", EntrepreneurUsage=" + EntrepreneurUsage + ", utilisateurDDN=" + utilisateurDDN + '}';
    }


   



 
    
    

    
}
