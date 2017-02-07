package com.cs442.sshinde5.mynewapp_chckout;

import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Shivani Shinde on 01-10-2016.
 */


class User {
    public String name;
    public int price;
    public String desc;
    public int count;
    public String ID;
    public int value;


    public User(){

    }

    public User(String ID, String _name, int _price, int _count, String _desc) {
        this.name = _name;
        this.price = _price;
        this.desc = _desc;
        this.count = _count;
        this.ID = ID;;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("1","Pizza Bread\t", 10,0, "\tPizza bread is a type of sandwich that is often served open-faced which consists of bread, pizza or tomato sauce, cheese and various toppings."));
        users.add(new User("2","Hamburger\t", 15,0, "\tA hamburger (or cheeseburger when served with a slice of cheese) is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun"));
        users.add(new User("3","Pasta\t", 20,0, "\tA baked pasta dish or casserole, consisting of noodles, tomato sauce, cheese and ground beef, with additional shredded cheese typically added to the top before baking. "));
        users.add(new User("4","Noodles\t",25,0, "\tNoodles are made from unleavened dough which is stretched, extruded, or rolled flat and cut into one of a variety of shapes."));
        users.add(new User("5","Nachos\t",10,0,"\tNachos are composed of tortilla chips (totopos) covered with cheese or cheese-based sauce, and is often served as a snack."));
        users.add(new User("6","Salad\t", 10,0, "\tA salad is a dish consisting of small pieces of food, which may be mixed with a sauce or salad dressing and are typically served cold"));
        return users;
    }
}
