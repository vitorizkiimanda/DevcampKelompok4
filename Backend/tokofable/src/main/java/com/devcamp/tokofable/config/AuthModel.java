package com.devcamp.tokofable.config;

public class AuthModel {
    private String userId;
    private String roles;
    private String tenantId;

    public AuthModel() {
    }

    public AuthModel(String userId, String roles, String tenantId) {
        this.userId = userId;
        this.roles = roles;
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRoles() {
        return this.roles;
    }

    public String getTenantId() {
        return this.tenantId;
    }
}
