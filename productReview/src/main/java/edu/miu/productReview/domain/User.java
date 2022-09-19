package edu.miu.productReview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email; // use it as username
    private String password;
    private String firstName;
    private String lastName;
    private String gender; //female, male

    //user can create many review
    @OneToMany(mappedBy="user")
    private List<Review> reviews;

    //user can have only one Address
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="id_address")
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
        Set<User> roleUser = role.getUsers();
        if(roleUser == null) {
            roleUser = new HashSet<>();
            roleUser.add(this);
            role.setUsers(roleUser);
        } else
            role.getUsers().add(this);
    }

    public void removeRole(long roleId) {
        Role role = this.roles.stream().filter(r -> r.getId() == roleId).findFirst().orElse(null);
        if (role != null) {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        this.roles.forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getRole().toUpperCase())));

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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
