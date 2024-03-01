package edu.eci.mcsw.Model;

public class Service {
    private String nombre;
    private String company;
    private String consume;

    /**
     * Default constructor
     */
    public Service() {
    }


    /**
     * constructo with parameters
     * @param nombre
     * @param company
     * @param consume
     */
    public Service(String nombre, String company, String consume) {
        this.nombre = nombre;
        this.company = company;
        this.consume = consume;
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getConsume() {
        return consume;
    }
    public void setConsume(String consume) {
        this.consume = consume;
    }
    
}
