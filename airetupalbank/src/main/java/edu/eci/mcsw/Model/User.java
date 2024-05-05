package edu.eci.mcsw.Model;

import edu.eci.mcsw.Model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    private List<UserRoles> roles  = new ArrayList<>();
    private String password;

    @OneToMany(mappedBy = "userRef", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    /**
     * Default constructor with atributes
     */
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.roles.add(UserRoles.USER);
    }
    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * method that adds a new Role to the user
     * @param role
     */
    public void addRole(UserRoles role){
        if(!roles.contains(role)){
            roles.add(role);
        }
    }

    /**
     * method that removes a rol from a user
     * @param role
     */
    public void deleteRole(UserRoles role){
        if(!roles.contains(role)){
            roles.remove(role);
        }
    }

    /**
     * method that adds a new account to the user
     * @param newAccount
     */
    public void addAccount(Account newAccount){
        if(!accounts.contains(newAccount)){
            accounts.add(newAccount);
            newAccount.setUserRef(this);
        }
    }
    

}
