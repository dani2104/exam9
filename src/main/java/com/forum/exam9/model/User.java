package com.forum.exam9.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BasicEntity implements UserDetails {

    private String userName;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Theme> themes=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments=new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("full"));
    }

    @Override
    public String getUsername() {
        return getUsername();
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

}
