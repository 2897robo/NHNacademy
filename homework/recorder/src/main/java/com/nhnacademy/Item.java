package com.nhnacademy;

public class Item {
    int id;
    String model;
    int health;
    int attack;
    int defense;
    int move_speed;
    int attack_speed;

    public Item(int id, String model, int health, int attack, int defense, int move_speed, int attack_speed) {
        this.id = id;
        this.model = model;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.move_speed = move_speed;
        this.attack_speed = attack_speed;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack_speed() {
        return attack_speed;
    }

    public int getMove_speed() {
        return move_speed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setMove_speed(int move_speed) {
        this.move_speed = move_speed;
    }

    public void setAttack_speed(int attack_speed) {
        this.attack_speed = attack_speed;
    }
}
