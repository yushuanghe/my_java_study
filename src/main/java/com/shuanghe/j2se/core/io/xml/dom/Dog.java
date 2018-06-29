package com.shuanghe.j2se.core.io.xml.dom;

/**
 * Created by yobdc on 2016/12/15.
 */
public class Dog {
    private int id;
    private String name;
    private int health;
    private int love;
    private String strain;
    private Master master;

    public Dog() {
    }

    public Dog(int id, String name, int health, int love, String strain, Master master) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.love = love;
        this.strain = strain;
        this.master = master;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", love=" + love +
                ", strain='" + strain + '\'' +
                ", master=" + master +
                '}';
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }
}
