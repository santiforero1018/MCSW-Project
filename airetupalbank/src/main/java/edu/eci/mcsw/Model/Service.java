package edu.eci.mcsw.Model;

public class Service {
    private String nombre;
    private String company;
    private int consume;
    private String billReference;

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
    public Service(String nombre, String company, int consume, String billReference) {
        this.nombre = nombre;
        this.company = company;
        this.consume = consume;
        this.billReference = billReference;
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
    public int getConsume() {
        return consume;
    }
    public void setConsume(int consume) {
        this.consume = consume;
    }


    public String getBillReference() {
        return billReference;
    }


    public void setBillReference(String billReference) {
        this.billReference = billReference;
    }
    
}
