package com.reactnativestarter;

public class Contact {
    String id;
    String name;
    int age;

    Contact(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return  id;
    }

    public String getContactName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContactName(String name) {
        this.name = name;
    }

    public void setContactAge(int age) {
        this.age = age;
    }
}
