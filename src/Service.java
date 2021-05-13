public class Service {
    
    private String service_name;
    private double service_price;


    public Service() {
        this.service_name = "";
        this.service_price = 0.0;
    }

    public Service(String service_name, double d){
        this.service_name = service_name;
        this.service_price = d;
    }

    
    
    /**
     * @return A String representing the service's name
     */
    public String getService_name() {
        return service_name;
    }

    /**
     * @param service_name -> the name to be set
     */
    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    
    
    /**
     * @return A float representing the service's price
     */
    public double getService_price() {
        return service_price;
    }

    /**
     * @param service_price -> the service's price to set
     */
    public void setService_price(double service_price) {
        this.service_price = service_price;
    }

    
    
    /**
     * @return A String containing all {@code Service} data
     */
    public String toString(){
        return "\nNome do servico: "  + service_name
             + "\nPreco do servico: " + service_price;
    }

}
