public class AutoPart {
	
    private String part_name;
    private int quantity;
    private double part_price;


    public AutoPart() {
    }

    public AutoPart(String part_name, int quantity, double d){
        this.part_name = part_name;
        this.quantity = quantity;
        this.part_price = d;
    }

    
    
    /**
     * @return A String representing the automobile part's name
     */
    public String getPart_name() {
        return part_name;
    }

    /**
     * @param part_name -> the part's name to be set
     */
    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    
    
    /**
     * @return An int representing the quantity of that automobile part
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity -> the automobile part quantity to be set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
    /**
     * @return A float representing the automobile part price
     */
    public double getPart_price() {
        return part_price;
    }

    /**
     * @param part_price -> automobile part's price to be set
     */
    public void setPart_price(double part_price) {
        this.part_price = part_price;
    }

    
    
    /**
     * @return a String containing all {@code AutoPart} data 
     */
    public String toString(){
        return "\nNome da peca: "      + getPart_name()
             + "\nPreco da peca"       + getPart_price()
             + "\nQuantidade de pecas" + getQuantity();
    }

}
