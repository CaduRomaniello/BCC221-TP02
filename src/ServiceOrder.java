import java.util.Map;
import java.util.HashMap;

public class ServiceOrder {
    
    private int id;
    private String motivo;
    private String placa;
    private double km;
    private boolean concluded;
    private boolean approved;
    private boolean executed;
    private double cost;
    private String owner_cpf;
    private Map<String, Service> services;
    private Map<String, AutoPart> automobile_parts;
    public static int count = 1;


    public ServiceOrder() {
        this.services =  new HashMap<>();
        this.automobile_parts =  new HashMap<>();
    }
    public ServiceOrder(String motivo, String placa, double km, String owner_cpf) {
        this.id = this.get_count();
        ServiceOrder.increment_count();
        this.motivo = motivo;
        this.placa = placa;
        this.km = km;
        this.owner_cpf = owner_cpf;
        this.concluded = false;
        this.approved = false;
        this.executed = false;
        this.services = new HashMap<>();
        this.automobile_parts = new HashMap<>();
    }

    
    
    public static void increment_count(){
        count++;
    }

    public int get_count(){
        return count;
    }

    /**
     * @return The service order's id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id -> the id to be set to the new object
     */
    public void setId(int id) {
        this.id = id;
    }

    
    
    /**
     * @return A String representing the client's motive, such as: Flat tire
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo -> the motive to be set, e.g, 'Flat tire'
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
    
    /**
     * @return The vehicle's plate
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa -> the vehicle's plate to be set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    
    
    /**
     * @return The value of the vehicle's KM
     */
    public double getKm() {
        return km;
    }

    /**
     * @param km -> The vehicle's KM which will be set
     */
    public void setKm(double km) {
        this.km = km;
    }

    
    
    /**
     * @return boolean meaning if the service order has been concluded or not
     */
    public boolean isConcluded() {
        return concluded;
    }

    /**
     * @param concluded -> the service order's object conclusion status
     */
    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    
    
    /**
     * @return boolean meaning if the service order has been approved by the client or not
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved -> the service order's object approved status
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    
    
    /**
     * @return  boolean meaning if the service order has been executed by the mechanic or not
     */
    public boolean isExecuted() {
        return executed;
    }

    /**
     * @param executed -> the service order's object execution status
     */
    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    
    
    /**
     * @return The {@code ServiceOrder} current/total cost ... It includes all the services and automobile parts used
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost -> the cost to be set to the object
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    
    
    /**
     * @return A String containing the owner's CPF
     */
    public String getOwner_cpf() {
        return owner_cpf;
    }

    /**
     * @param owner_cpf -> the vehicle owner's CPF
     */
    public void setOwner_cpf(String owner_cpf) {
        this.owner_cpf = owner_cpf;
    }

    
    
    /**
     * Push a service used in the {@code ServiceOrder} to the  {@code ServiceOrder.services}
     * 
     * @param key -> the name of the service
     * @param service -> the service object
     */
    public void push_services(String key, Service service) {
        boolean aux = this.services.containsKey(key);

        if (aux == false){
            this.services.put(key, service);
        }
    }

    /**
     * Removes a service from {@code ServiceOrder.services}
     * 
     * @param key -> the name of the service
     */
    public void pop_services(String key) {
        boolean aux = this.services.containsKey(key);

        if (aux == true){
            this.services.remove(key);
        }
    }

    
    
    /**
     * Push a service used in the {@code ServiceOrder} to the  {@code ServiceOrder.automobile_parts}
     * 
     * @param key -> the automobile part's name
     * @param auto_part -> the AutoPart object
     */
    public void push_automobile_parts(String key, AutoPart auto_part) {
        boolean aux = this.automobile_parts.containsKey(key);

        if (aux == false){
            this.automobile_parts.put(key, auto_part);
        }
    }


    /**
     * Removes an automobile part used from {@code ServiceOrder.automobile_parts}
     * 
     * @param key -> the name of the part
     */
    public void pop_automobile_parts(String key) {
        boolean aux = this.automobile_parts.containsKey(key);

        if (aux == true){
            this.automobile_parts.remove(key);
        }
    }

    
    
    /**
     * @return A String containing all important {@code Service Order} data
     */
    public String toString() {
        String status = "Sem informacoes!";

        if (isApproved() == false){
            status = "Aguardando aprovacao!";
        }
        if (isApproved() == true && isExecuted() == false){
            status = "Aprovado!";
        }
        if (isApproved() == true && isExecuted() == true){
            status = "Executado!";
        }
        if (isConcluded() == true){
            status = "Servico fechado!";
        }

        return "\nID: "                  + getId()        + 
               "\nMotivo: "              + getMotivo()    +
               "\nPlaca do automovel: "  + getPlaca()     +
               "\nCPF do proprietario: " + getOwner_cpf() + 
               "\nStatus: "              + status         +
        	   "\nCusto: " + getCost() + "\n";
    }

    
    /**
     * Print the services used in the terminal
     */
    public void showServices(){
        for(Map.Entry<String, Service> entry : this.services.entrySet()) {

            System.out.println(entry.getValue());

        }
    }

}
