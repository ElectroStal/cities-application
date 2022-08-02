package com.solbeg.testtask.citiesshower.util;

public enum UserRolesEnum {
    USER("ALLOW_READ"),
    ADMIN("ALLOW_EDIT");

    private String role;

    UserRolesEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
