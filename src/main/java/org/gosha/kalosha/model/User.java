package org.gosha.kalosha.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user_data")
public class User
{
    @Id
    @SequenceGenerator(name = "user_data_sequence", sequenceName = "user_data_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_data_sequence")
    private Long id;

    private String username;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles")
    private Collection<Role> roles = new ArrayList<>();

    public User()
    {
    }

    public User(Long id, String username, String email, String password, Collection<Role> roles)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Collection<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Collection<Role> roles)
    {
        this.roles = roles;
    }
}
