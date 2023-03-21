package br.com.areadigital.areadigital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A class representing a person.
 *
 * @author Your Name
 */

@Data
@Builder
@Entity(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails, IBaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

 
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String username;

//    @NotBlank(message = "Email Obrigatório")
    // private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();




    /**
     * Returns the roles this person has.
     *
     * @return a collection of {@link GrantedAuthority} objects representing the roles this person has
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the person's password.
     *
     * @return the person's password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the person's email address.
     *
     * @return the person's email address
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * Returns {@code true} if the credentials (password) has not expired.
     *
     * @return {@code true} if the credentials has not expired, {@code false} otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Returns {@code true} if the account is enabled.
     *
     * @return {@code true} if the account is enabled, {@code false} otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


}