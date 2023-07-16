/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Tag;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class TagDTO implements Serializable{
    private int id;
    private String name;
    private String color;

    public TagDTO(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public TagDTO() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
 
}
