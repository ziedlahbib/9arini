/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshodb.entities;

/**
 *
 * @author yahia
 */
public class React {
    private int id;
    private reactType reacType;

    public React(int id, reactType reaType) {
        this.id = id;
        this.reacType = reaType;
    }

    public React() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public reactType getReacType() {
        return reacType;
    }

    public void setRecaType(reactType reaType) {
        this.reacType = reaType;
    }

    @Override
    public String toString() {
        return "React{" + "id=" + id + ", reacType=" + reacType + '}';
    }
    
    
    
}
