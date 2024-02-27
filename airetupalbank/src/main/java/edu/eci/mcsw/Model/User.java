package edu.eci.mcsw.Model;

public class User {

    private int id;
    private String nombre;
    private String email;
    private String role;
    private String password;

    /**
     * Default constructor with atributes
     */
    public User(int id, String nombre, String email, String role, String password){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    /**
     * Default constructor
     */
    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

}
