public class User {
    
    private String name;
    private String cpf;
    private String email;
    private String psswd;


    public User() {
    }

    public User(String name, String cpf, String email, String psswd) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.psswd = psswd;
    }

    
    
    /**
     * @return A String, which represents the user name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name -> the user's name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    
    
    /**
     * @return A String, which is the user CPF
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf -> the user's CPF to be set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    
    /**
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email -> the user's email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    
    /**
     * @return The user's password
     */
    public String getPsswd() {
        return psswd;
    }

    /**
     * @param psswd -> the user's password
     */
    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    
    
    /**
     * @return A String containing all {@code User} data
     */
    @Override
    public String toString() {
        return "\nNome: "  + this.name  + 
               "\nEmail: " + this.email + 
               "\nCPF: "   + this.cpf   + "\n";
    }

}
