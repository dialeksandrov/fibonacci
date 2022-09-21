package com.aleksandrov.fibonacci.domain.enums;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
public enum Status {
    SUCCESS("success"), ERROR("error");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
