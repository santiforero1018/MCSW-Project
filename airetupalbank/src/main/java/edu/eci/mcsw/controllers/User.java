package edu.eci.mcsw.controllers;

/**
 * this it's the users
 */
public class User {

    private String user;
    private String password;
    private String role;

    /**
     * Constructor of the class
     * @param user
     * @param password
     * @param role
     */
    public User(String user, String password, String role){
        this.user = user;
        this.password = password;
        this.role = role;
    }

    /**
     * Assigns a new role, administrator, to the user
     * @param user
     * @param newRole
     * @return
     */
    public String setNewRole(User user, String newRole){
        if(validRole(newRole) && this.role.equals("Admin")){
            user.setRole(newRole);
        }
        return "Invalid rol, please try again";
    }

    /**
     * Change the role
     * @param newRole
     */
    public void setRole(String newRole){
        this.role = newRole;
    }

    /**
     * return true if the role it's valid
     * @param roleNew
     * @return
     */
    public boolean validRole(String roleNew){
        return "Admin".equals(roleNew) || "Auditor".equals(roleNew) || "User".equals(roleNew);
    }

    /**
     * Get a role
     * @return
     */
    public String getRole(){
        return role;
    }
}
