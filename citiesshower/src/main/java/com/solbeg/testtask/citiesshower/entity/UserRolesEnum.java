package com.solbeg.testtask.citiesshower.entity;

public enum UserRolesEnum {
    USER("ALLOW_READ"),
    ADMIN("ALLOW_EDIT");

    private final String role;

    UserRolesEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
