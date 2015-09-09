package com.simple.plataforma.presentacion.webapp;

import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ecandela on 24/08/2015.
 */
@XmlRootElement(name = "person")
public class PersonWrapper extends ResourceSupport {

    private String name;

    private int age;

    public PersonWrapper() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
