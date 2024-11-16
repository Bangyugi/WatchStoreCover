package com.group2.watchstorecover.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int accountId;

    @Column(columnDefinition = "varchar(60) collate 'utf8_bin'",unique = true,nullable = false)
    String username;

    @Column(columnDefinition = "varchar(60) collate 'utf8_bin'",nullable = false)
            @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;

    @ManyToOne @JoinColumn(name = "role_id")
    Role role;

    @Builder.Default
    boolean accountAvailable = true;

    @OneToOne @JoinColumn(name = "customer_id")
    Customer customer;


    /**
     * {@inheritDoc}
     *
     * <p>Returns a collection of {@link SimpleGrantedAuthority} instances,
     * each containing the role name of the user's assigned role.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    /**
     * Checks if the account is non-expired.
     *
     * @return true if the account is non-expired, otherwise false.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if the account is non-locked.
     *
     * @return true if the account is non-locked, otherwise false.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if the account credentials are non-expired.
     *
     * @return true if the account credentials are non-expired, otherwise false.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the account is enabled.
     *
     * @return true if the account is enabled, otherwise false.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
