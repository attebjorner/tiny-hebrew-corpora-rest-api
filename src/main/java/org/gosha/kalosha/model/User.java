package org.gosha.kalosha.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user_data")
@Data @NoArgsConstructor @AllArgsConstructor
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
}
