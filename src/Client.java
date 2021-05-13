import java.util.*;

public class Client {
    
    public String name;
    public String cpf;
    public Map<String, Vehicle> vehicles;

    public Client() {
    	this.vehicles = new HashMap<>();
    }
    
    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.vehicles = new HashMap<>();
    }

    
    
    /**
     * @return A String which is the client's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name -> the client's name
     */
    public void setId(String name) {
        this.name = name;
    }

    
    
    /**
     * @return A String representing the client's CPF
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf -> the client's CPF to be set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    
    /**
     * Push a vehicle to {@code Client.vehicles}
     * 
     * @param key -> the vehicle's plate
     * @param vehicle -> the vehicle's object
     */
    public void push_vehicles(String key, Vehicle vehicle) {
        boolean aux = this.vehicles.containsKey(key);

        if (aux == false){
            this.vehicles.put(key, vehicle);
        }
    }

    /**
     * Removes a vehicle from {@code Vehicle.historic}
     * 
     * @param key -> The vehicle's plate
     */
    public void pop_vehicles(String key) {
        boolean aux = this.vehicles.containsKey(key);

        if (aux == true){
            this.vehicles.remove(key);
        }
    }

    /**
     * @return All vehicles data registered in this client
     */
    public String get_vehicles_data(){
    	String plates = "";
    	
    	int aux = 0;
    	
    	for(Map.Entry<String, Vehicle> entry : this.vehicles.entrySet()) {
    		aux += 1;
    		if (aux <= 1) {
    			plates = plates.concat(entry.getValue().getPlaca());
    			
    		}else {    			
    			plates = plates.concat(" | " + entry.getValue().getPlaca());
    		}
        }
    	
    	
        return plates;
    }

    
    /**
     * @return A String with all {@code Client} relevant information
     */
    public String toString(){
        return "\nNome: "     + name
             + "\nCPF: "      + cpf
             + "\nVeiculos: " + get_vehicles_data();
    }

}
