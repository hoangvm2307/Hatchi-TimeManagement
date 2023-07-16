/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.model.Simon;

import java.io.Serializable;

/**
 *
 * @author hoangvmdeptrai
 */
public class SimonDTO implements Serializable {

    private String id;
    private String name;
    private String itemLink;
    private String price;
    private String enable;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public SimonDTO() {
    }

    public SimonDTO(String id, String name, String itemLink, String price, String enable) {
        this.id = id;
        this.name = name;
        this.itemLink = itemLink;
        this.price = price;
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
