import java.util.*;

public class Vehicle {
    
    private String placa;
    private double km;
    private String owner;
    private Map<Integer, ServiceOrder> historic;

    public Vehicle() {
    }
    public Vehicle(String placa, double km, String owner) {
        this.placa = placa;
        this.km = km;
        this.owner = owner;
        this.historic = new HashMap<>();
    }

    
    
    /**
     * @return String return the vehicle's plate
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa -> Vehicle's plate to be set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    
    
    /**
     * @return double return vehicle's KM
     */
    public double getKm() {
        return km;
    }

    /**
     * @param km -> Sets vehicle's KM
     */
    public void setKm(double km) {
        this.km = km;
    }

    
    
    /**
     * @return String returns vehicle's owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner -> the owner to be set to a this vehicle
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    
    
    /**
     * Push a service order to vehicle's repair history
     * 
     * @param key -> the service order ID
     * @param service_order -> the service order object
     */
    public void push_historic(int key, ServiceOrder service_order) {
        boolean aux = this.historic.containsKey(key);

        if (aux == false){
            this.historic.put(key, service_order);
        }
    }

    /**
     * Remove a service order from vehicle's repair history
     * 
     * @param key -> Service order's id
     */
    public void pop_historic(int key) {
        boolean aux = this.historic.containsKey(key);

        if (aux == true){
            this.historic.remove(key);
        }
    }

    /**
     * @return the map {@code Vehicle.historic}
     */
	public Map<Integer, ServiceOrder> getHistoric() {
		
		return this.historic;
		
	}

	
    
    /**
     * @return a String containing all class data
     */
    public String toString(){
        return "\nPlaca: "        + placa
             + "\nKilometragem: " + km
             + "\nHistorico: "    + historic.toString();
    }

}
