package com.etc.trader.service;

import com.etc.trader.model.Company;
import com.etc.trader.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstName;

    private String lastName;

    private boolean activated;

    private byte[] image;

    private String imageContentType;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Company company;

    private Collection<? extends GrantedAuthority> roles;

    public CustomUserDetails(Long id, String firstName, String lastName,
                         boolean activated, byte[] image, String imageContentType,
                         String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities, Company company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.activated = activated;
        this.image = image;
        this.imageContentType = imageContentType;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = authorities;
        this.company = company;
    }

    public static CustomUserDetails build(User user) {
        List<GrantedAuthority> roles = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new CustomUserDetails(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.isActivated(),
                user.getImage(),
                user.getImageContentType(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                roles,
                user.getCompany()
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public byte[] getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public  Company getCompany() {return company;}

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomUserDetails user = (CustomUserDetails) o;
        return Objects.equals(id, user.id);
    }
}
