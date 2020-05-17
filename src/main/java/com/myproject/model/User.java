package com.myproject.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private int age;
    @Column(name = "password")
    private String password;
    @Column
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;


    public User() {

    }
    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.age = userDTO.getAge();
        this.password = userDTO.getPassword();
        this.roles = setRoles(userDTO.getRoles());
    }

    public User( String firstName, String password, String lastName, String email, int age) {
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public User(String firstName, String password, String lastName, String email, int age, Set<Role> roles) {
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public User(long id, String firstName, String lastName, int age, String email, String password, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }
    public User(long id, String firstName, String lastName, int age, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.age = age;

    }


    public String getRoles() {
        return roles.toString().replaceAll("\\p{P}", "");
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Принимаем строку с ролями из userDTO и распределяем роли для User
    public Set<Role> setRoles(String role) {
        Set<Role> roles = new HashSet<>();
        try {
            String[] partsRole = role.split("[, ]");
            roles.add(new Role(partsRole[1]));
            roles.add(new Role(partsRole[0]));
            return this.roles = roles;
        } catch (Exception e) {

        }
        roles.add(new Role(role));
        return this.roles = roles;
    }

}
