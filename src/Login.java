import java.util.*;
import java.util.logging.Level;
import java.awt.*; // Para Grid
import java.awt.event.*; // Para ActionListener
import java.lang.System.Logger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.management.StandardEmitterMBean;
import javax.swing.*; // Para frame e panel
import javax.swing.text.MaskFormatter;

public class Login {

    // Interfaces main variables
    private static JFrame mainFrame_login;
    private static JPanel mainPanel_login;
    private static JButton mainButton_login;

    // Inputed on login
    private static String input_cpf;
    private static String input_password;
    
    // Users reference
    private static Admin admin;
    private static Mechanic mec;
    private static Vendor salesman;

    
    
    // Graphical Interface for login
    
    /**
     * Set all frame configurations for the login page
     */
    public static void setFrame_login(){
    	
        mainFrame_login = new JFrame();
        mainPanel_login = new JPanel();

        mainPanel_login.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Top - Left - Bottom - Right
        mainPanel_login.setLayout(new GridLayout(0, 1)); 

        mainFrame_login.add(mainPanel_login, BorderLayout.CENTER); // Align main panel's items to the center
        mainFrame_login.setTitle("Oficina - Login"); // Frame title
        mainFrame_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Action on closing the frame
        mainFrame_login.setMinimumSize(new Dimension(500, 400));
        mainFrame_login.setSize(450, 350); // width - height
        mainFrame_login.setLocationRelativeTo(null); // Initialize panel frame on the center of the screen
        
        login();
        
    }

    /**
     * Display login page to the user
     */
    public static void login(){      
        mainPanel_login.removeAll(); 
        
        input_cpf = "";
        input_password = "";
        
        admin = null;
        mec = null;
        salesman = null;
        
        JPanel    cpf_panel = new JPanel(); // Panel for cpf forms -> mainPanel_login inner panel
        JPanel  psswd_panel = new JPanel(); // Panel for password forms -> mainPanel_login inner panel
        JPanel button_panel = new JPanel(); // Panel for buttons -> mainPanel_login inner panel

        JLabel   cpf_label = new JLabel("CPF");
        JLabel psswd_label = new JLabel("Senha");

        JFormattedTextField cpf = new JFormattedTextField();
        JPasswordField password = new JPasswordField();

        try {
            MaskFormatter cpf_mask = new MaskFormatter("### . ### . ### - ##"); // Formats the text field of cpf variable
            cpf_mask.setPlaceholderCharacter('_');
            cpf_mask.install(cpf);

        } catch (ParseException e) {
            System.out.println(e);
        }

        cpf.setBorder(BorderFactory.createCompoundBorder(cpf.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
        password.setBorder(BorderFactory.createCompoundBorder(password.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

        mainButton_login = new JButton("Login");

        GridBagLayout gridBagLayout = new GridBagLayout(); // Creates a layout to manipulate grids (very useful for many components in a single panel)
        GridBagConstraints gridBagConstraints = new GridBagConstraints(); // Creates a grid constraint (used to configure its display and distribution of contents in the panel).

        gridBagConstraints.ipadx = 60; // width of the button -> padding
        gridBagConstraints.ipady = 15; // height of the button -> padding
        gridBagConstraints.insets = new Insets(2, 2, 2, 2); // In case the is more than 1 button - top - left - bottom - right

        gridBagLayout.setConstraints(mainButton_login, gridBagConstraints); // Adds the constraint created above

        cpf_panel.setLayout(new GridLayout(0, 1)); // Creates a simple grid layout 
        psswd_panel.setLayout(new GridLayout(0, 1)); // Creates a simple grid layout
        button_panel.setLayout(gridBagLayout); // Sets the button grid layout created above

        cpf_panel.add(cpf_label);
        cpf_panel.add(cpf);
        psswd_panel.add(psswd_label);
        psswd_panel.add(password);

        button_panel.add(mainButton_login);

        mainPanel_login.add(cpf_panel);    // Adds all inner panels to the main panel
        mainPanel_login.add(psswd_panel);  // Adds all inner panels to the main panel
        mainPanel_login.add(button_panel); // Adds all inner panels to the main panel

        mainFrame_login.setVisible(true);

        /**
         * Once the login button is pressed, it will then proceed to the login information checkout procedure
         */
        mainButton_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String[] user_cpf = cpf.getText().split("", 0);
                String oficial_cpf = "";

                for (String i : user_cpf) {
                    if (!i.equals(".") && !i.equals("-") && !i.equals(" ") && !i.equals("_")) {
                        oficial_cpf = oficial_cpf.concat(i);
                    }
                }

                setInput_cpf(oficial_cpf);
                setInput_password(password.getText());
                
                verifyAccess(getInput_cpf(), getInput_password());
                
                if (admin != null){
                    Admin.setLayoutDisplay();
                }
                else if(mec != null) {
                	Mechanic.setLayoutDisplay();
                }
                else if(salesman != null) {
                	Vendor.setLayoutDisplay();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Erro de Login.\nCPF ou senha incorreta!", "Oficina - Login", JOptionPane.ERROR_MESSAGE);
                    login();
                }
            }
        });
    }
    

    /** 
     * Verify the login data entered
     * 
     * @param cpf -> The CPF entered by the user
     * @param password -> The password entered by the user
     */
    private static void verifyAccess(String cpf, String password) {
    	if(cpf.equals(Driver.adm.getCpf()) && password.equals(Driver.adm.getPsswd()))
    		setAdmin(Driver.adm);
    	
    	else{
    		Mechanic aux;
    		
    		try {
    			aux = Driver.mechanics.get(cpf);
    		}
    		catch(Exception e){
    			aux = null;
    			System.out.println(e);
    		}
    		
    		if (aux != null && aux.getPsswd().equals(password))
    			setMechanic(aux);
    		
    		else {
    			Vendor vendor;
    			
    			try {
    				vendor = Driver.vendors.get(cpf);
    			}
    			catch(Exception e) {
    				vendor = null;
    				System.out.println(e);
    			}
    
    			if (vendor != null && vendor.getPsswd().equals(password))
    				setVendor(vendor);
    		}
    	}
 	
    }
    
    
    // Constructor
    public Login(){
    	admin = null;
    	mec = null;
    	salesman = null;

    	setFrame_login();
    }

    
    //Setters & Getters Users
    public static void setAdmin(Admin adm) {
    	admin = adm;
    }

    public static void setMechanic(Mechanic mechanic) {
    	mec = mechanic;
    }
    
    public static void setVendor(Vendor vendor) {
    	salesman = vendor;
    }
    
    public static Admin getAdmin() {
    	return admin;
    }
    
    public static Mechanic getMechanic() {
    	return mec;
    }
    
    public static Vendor getVendor() {
    	return salesman;
    }
    
    
    // Panel's setters & Getters
    public static JFrame getMainFrame_login() {
        return mainFrame_login;
    }

    public static void setMainFrame_login(JFrame login) {
        mainFrame_login = login;
    }

    public static JPanel getMainPanel_login() {
        return mainPanel_login;
    }

    public static void setMainPanel_login(JPanel login) {
        mainPanel_login = login;
    }

    public static JButton getMainButton_login() {
        return mainButton_login;
    }

    public static void setMainButton_login(JButton login) {
        mainButton_login = login;
    }

    public static String getInput_cpf() {
        return input_cpf;
    }

    public static void setInput_cpf(String cpf) {
        input_cpf = cpf;
    }

    public static String getInput_password() {
        return input_password;
    }

    public static void setInput_password(String input_psswd) {
        input_password = input_psswd;
    }

}
