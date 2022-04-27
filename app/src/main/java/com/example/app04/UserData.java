package com.example.app04;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 */
public class UserData implements Serializable {

   private int id ;
   private  String name ;
   private String[] hobby ;

    public UserData(int id, String name, String[] hobby) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
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

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobby=" + Arrays.toString(hobby) +
                '}';
    }
}
