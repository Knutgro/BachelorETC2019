package com.etc.trader.service.jwt;

import com.etc.trader.service.CustomUserDetails;
import com.etc.trader.service.UserDTO;
import lombok.Getter;
import lombok.Setter;


public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private CustomUserDetails user;

    public JwtResponse(String token, CustomUserDetails user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomUserDetails getUser() {
        return user;
    }

    public void setUser(CustomUserDetails user) {
        this.user = user;
    }
}
