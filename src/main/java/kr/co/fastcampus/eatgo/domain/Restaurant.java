package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Restaurant{
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;

    @Transient
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant () {

    }

    public Restaurant(String name){
        this.name = name;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

	public Restaurant(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
	}

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

	public String getName(){
        return this.name;
    }

    public String getInfomation(){
        return this.name+" in "+this.address;
    }

    public String getAddress(){
        return this.address;
    }

    public List<MenuItem> getMenuItems(){
        return this.menuItems;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems){
        for (MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }
}