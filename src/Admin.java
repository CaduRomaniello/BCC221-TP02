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


public class Admin extends User{
    private int id;
    
    // GUI variables
    private static JFrame mainFrame_admin;
    private static JPanel mainPanel_admin;
    private static JButton sair;

    public Admin() {
    }

    public Admin(int id){
        this.id = id;
    }

    

    /**
     * @return the admin's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id -> sets the admin's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return A String to print admin data on the console.
     */
    public String toString(){
        return "------- Sys Admin -------\n" + super.toString();
    }

    
    // Layout
    
    /**
     * Set the frame's layout configuration, such as size, closing action, title.
     */
	public static void setLayoutDisplay() {
		
		Login.getMainFrame_login().dispose();	
		
		mainFrame_admin = new JFrame(); 
        mainPanel_admin = new JPanel();

		mainPanel_admin.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

		mainFrame_admin.add(mainPanel_admin);
		mainFrame_admin.setTitle("Oficina - Admin");
		mainFrame_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame_admin.setMinimumSize(new Dimension(400,500)); 
		mainFrame_admin.setSize(450,550); 
		mainFrame_admin.setLocationRelativeTo(null);
        mainFrame_admin.setVisible(true);
        
        layoutDisplay();
        
    }
    
    
    /**
     * Creates all the layout frontend and most of the button handlers
     */
    public static void layoutDisplay(){
    	
    	// Layout
    	mainPanel_admin.removeAll();
    	
        sair = new JButton("Sair");
        
        JButton mecanicos = new JButton("Editar mecanicos");
        JButton vendedores = new JButton("Editar vendedores");
        JButton display_vehicle_history = new JButton("Mostrar histotico de um veiculo");
        
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.ipady = 15; // height of the button -> padding
        gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(mecanicos, gridBagConstraints); // Adds the constraint created above
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(vendedores, gridBagConstraints); // Adds the constraint created above
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(display_vehicle_history, gridBagConstraints); // Adds the constraint created above

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(20,2,2,2);
        layout.setConstraints(sair, gridBagConstraints); // Adds the constraint created above

        mainPanel_admin.setLayout(layout);
        mainPanel_admin.add(mecanicos);
        mainPanel_admin.add(vendedores);
        mainPanel_admin.add(display_vehicle_history);
        mainPanel_admin.add(sair);
        
        
        //Handlers        
        mecanicos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainFrame_admin.dispose();
        		alterMechanics();
        	}
        });
        
        vendedores.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainFrame_admin.dispose();
        		alterVendors();
        	}
        });
        
        display_vehicle_history.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		displayVehicleRepairHistory();
        	}
        });
        
        sair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainFrame_admin.dispose();
        		Login.setFrame_login();
        	}
        });

    }

    
    
    /**
     * Displays the frame responsible for gathering the relevant mechanic's information in order to alter the desired one
     */
    public static void alterMechanics() {
    	
    	mainFrame_admin = new JFrame(); 
        mainPanel_admin = new JPanel();

		mainPanel_admin.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel cpf_label = new JLabel("Digite o CPF do mecanico");
        JLabel email_label = new JLabel("Digite o novo email");
        JLabel senha_label = new JLabel("Digite a nova senha");

        JFormattedTextField cpf = new JFormattedTextField();
        JPasswordField password = new JPasswordField();
        JTextField email = new JTextField();

        try {
            MaskFormatter cpf_mask = new MaskFormatter("### . ### . ### - ##"); // Formats the cpf variable's text field
            cpf_mask.setPlaceholderCharacter('_');
            cpf_mask.install(cpf);

        } catch (ParseException e) {
            System.out.println(e);
        }

        cpf.setBorder(BorderFactory.createCompoundBorder(cpf.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
        password.setBorder(BorderFactory.createCompoundBorder(password.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
        email.setBorder(BorderFactory.createCompoundBorder(email.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

        gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(cpf_label, gridBagConstraints);
        mainPanel_admin.add(cpf_label);
        gridBagConstraints.insets = new Insets(2,2,30,2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(cpf, gridBagConstraints);
        mainPanel_admin.add(cpf);
        
        gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(email_label, gridBagConstraints);
        mainPanel_admin.add(email_label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(email, gridBagConstraints);
        mainPanel_admin.add(email);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(senha_label, gridBagConstraints);
        mainPanel_admin.add(senha_label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        layout.setConstraints(password, gridBagConstraints);
        mainPanel_admin.add(password);
        
        gridBagConstraints.insets = new Insets(30,2,2,2);
        JButton editar_mecanico = new JButton("Confirmar");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        layout.setConstraints(editar_mecanico, gridBagConstraints);
        mainPanel_admin.add(editar_mecanico);

        mainPanel_admin.setLayout(layout);

		mainFrame_admin.add(mainPanel_admin);
		mainFrame_admin.setTitle("Oficina - Admin");
		mainFrame_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame_admin.setMinimumSize(new Dimension(400,300)); 
		mainFrame_admin.setSize(450,350); 
		mainFrame_admin.setLocationRelativeTo(null);
        mainFrame_admin.setVisible(true);

        editar_mecanico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                
        		String[] user_cpf = cpf.getText().split("", 0);
                String oficial_cpf = "";

                for (String i : user_cpf) {
                    if (!i.equals(".") && !i.equals("-") && !i.equals(" ") && !i.equals("_")) {
                        oficial_cpf = oficial_cpf.concat(i);
                    }
                }

                boolean alterou = editarMecanico(oficial_cpf, email.getText(), password.getText());
                if (alterou == true){
                    mainFrame_admin.dispose();
                    setLayoutDisplay();
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!", "Oficina - Alterar mecanico", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    mainFrame_admin.dispose();
                    setLayoutDisplay();
                    JOptionPane.showMessageDialog(null, "Erro ao alterar dados de mecanico!\nCPF nao cadastrado", "Oficina - Alterar mecanico", JOptionPane.ERROR_MESSAGE);

                }

        	}
        });
        
    }
    
    /**
     * Displays the frame responsible for gathering the relevant mechanic's information in order to alter the desired one
     */
    public static void alterVendors() {
    	
    	mainFrame_admin = new JFrame(); 
        mainPanel_admin = new JPanel();

		mainPanel_admin.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel cpf_label = new JLabel("Digite o CPF do vendedor");
        JLabel email_label = new JLabel("Digite o novo email");
        JLabel senha_label = new JLabel("Digite a nova senha");

        JFormattedTextField cpf = new JFormattedTextField();
        JPasswordField password = new JPasswordField();
        JTextField email = new JTextField();

        try {
            MaskFormatter cpf_mask = new MaskFormatter("### . ### . ### - ##"); // Formats the text field of cpf variable
            cpf_mask.setPlaceholderCharacter('_');
            cpf_mask.install(cpf);

        } catch (ParseException e) {
            System.out.println(e);
        }

        cpf.setBorder(BorderFactory.createCompoundBorder(cpf.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
        password.setBorder(BorderFactory.createCompoundBorder(password.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
        email.setBorder(BorderFactory.createCompoundBorder(email.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

        gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(cpf_label, gridBagConstraints);
        mainPanel_admin.add(cpf_label);
        gridBagConstraints.insets = new Insets(2,2,30,2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(cpf, gridBagConstraints);
        mainPanel_admin.add(cpf);
        
        gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(email_label, gridBagConstraints);
        mainPanel_admin.add(email_label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(email, gridBagConstraints);
        mainPanel_admin.add(email);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(senha_label, gridBagConstraints);
        mainPanel_admin.add(senha_label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        layout.setConstraints(password, gridBagConstraints);
        mainPanel_admin.add(password);
        
        gridBagConstraints.insets = new Insets(30,2,2,2);
        JButton editar_vendedor = new JButton("Confirmar");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        layout.setConstraints(editar_vendedor, gridBagConstraints);
        mainPanel_admin.add(editar_vendedor);

        mainPanel_admin.setLayout(layout);

		mainFrame_admin.add(mainPanel_admin);
		mainFrame_admin.setTitle("Oficina - Admin");
		mainFrame_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame_admin.setMinimumSize(new Dimension(400,300)); 
		mainFrame_admin.setSize(450,350); 
		mainFrame_admin.setLocationRelativeTo(null);
        mainFrame_admin.setVisible(true);

        editar_vendedor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                
        		String[] user_cpf = cpf.getText().split("", 0);
                String oficial_cpf = "";

                for (String i : user_cpf) {
                    if (!i.equals(".") && !i.equals("-") && !i.equals(" ") && !i.equals("_")) {
                        oficial_cpf = oficial_cpf.concat(i);
                    }
                }

                boolean alterou = editarVendedor(oficial_cpf, email.getText(), password.getText());

                if (alterou == true){
                    mainFrame_admin.dispose();
                    setLayoutDisplay();
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!", "Oficina - Alterar vendedor", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    mainFrame_admin.dispose();
                    setLayoutDisplay();
                    JOptionPane.showMessageDialog(null, "Erro ao alterar dados de vendedor!\nCPF nao cadastrado", "Oficina - Alterar vendedor", JOptionPane.ERROR_MESSAGE);

                }

        	}
        });
        
    }

    
    /**
     * Analyzes if either the mechanic exists or not. In case the CPF data entered is correct, the email and password will both be updated
     * 
     * @param cpf -> The mechanic's CPF
     * @param email -> The mechanic's new email
     * @param senha -> The mechanic's new password
     * 
     * @return true -> if changes were made
     *
     */
    public static boolean editarMecanico(String cpf, String email, String senha){

        Mechanic aux = Driver.mechanics.get(cpf);

        if (aux == null){
            return false;
        }

        aux.setEmail(email);
        aux.setPsswd(senha);
        return true;

    }

    /**
     * Analyzes if either the vendor exists or not. In case the CPF data entered is correct, the email and password will both be updated
     * 
     * @param cpf -> The vendor's CPF
     * @param email -> The vendor's new email
     * @param senha -> The vendor's new password
     * 
     * @return true -> if changes were made
     *
     */
    public static boolean editarVendedor(String cpf, String email, String senha){

        Vendor aux = Driver.vendors.get(cpf);

        if (aux == null){
            return false;
        }

        aux.setEmail(email);
        aux.setPsswd(senha);
        return true;

    }
 
    
    /**
     * Displays the frame responsible for gathering all relevant information to show a vehicle's repair history.
     * 
     * It overwrites all {@Admin.mainFrame_admin} contents
     */
    public static void displayVehicleRepairHistory() {

    	mainPanel_admin.removeAll();

    	mainPanel_admin.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	GridBagLayout layout = new GridBagLayout();

    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
    	gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    	JLabel client_cpf = new JLabel("Digite o CPF do cliente");
    	JLabel vehicle_plate = new JLabel("Digite a placa do veiculo");

    	JFormattedTextField cpf = new JFormattedTextField();
    	JFormattedTextField plate = new JFormattedTextField();

    	try {
    		MaskFormatter plate_mask = new MaskFormatter("UUU - ####"); // Formats the text field of cpf variable
    		MaskFormatter cpf_mask = new MaskFormatter("### . ### . ### - ##"); // Formats the text field of cpf variable
    		plate_mask.setPlaceholderCharacter('_');
    		cpf_mask.setPlaceholderCharacter('_');
    		plate_mask.install(plate);
    		cpf_mask.install(cpf);

    	} catch (ParseException e) {
    		System.out.println(e);
    	}


    	plate.setBorder(BorderFactory.createCompoundBorder(plate.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
    	cpf.setBorder(BorderFactory.createCompoundBorder(cpf.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

    	gridBagConstraints.insets = new Insets(2,2,2,2);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 0;
    	layout.setConstraints(client_cpf, gridBagConstraints);
    	mainPanel_admin.add(client_cpf);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	layout.setConstraints(cpf, gridBagConstraints);
    	mainPanel_admin.add(cpf);


    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	layout.setConstraints(vehicle_plate, gridBagConstraints);
    	mainPanel_admin.add(vehicle_plate);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 3;
    	layout.setConstraints(plate, gridBagConstraints);
    	mainPanel_admin.add(plate);  

    	gridBagConstraints.insets = new Insets(30,2,2,2);
    	JButton show = new JButton("Mostrar");
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 4;
    	layout.setConstraints(show, gridBagConstraints);
    	mainPanel_admin.add(show);

    	mainPanel_admin.setLayout(layout);
    	mainFrame_admin.add(mainPanel_admin);
    	mainFrame_admin.setTitle("Oficina - Mostrar historico");
    	mainFrame_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame_admin.setMinimumSize(new Dimension(400,300)); 
    	mainFrame_admin.setSize(450,550); 
    	mainFrame_admin.setLocationRelativeTo(null);
    	mainFrame_admin.setVisible(true);

    	show.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String oficial_plate = ""; 
    			String[] vehicle_plate = plate.getText().split("", 0);
    			String[] user_cpf = cpf.getText().split("", 0);
    			String oficial_cpf = "";

    			for (String i : user_cpf) {
    				if (!i.equals(".") && !i.equals("-") && !i.equals(" ") && !i.equals("_")) {
    					oficial_cpf = oficial_cpf.concat(i);
    				}
    			}

    			for (String i : vehicle_plate) {
    				if (!i.equals("-") && !i.equals(" ") && !i.equals("_")) {
    					oficial_plate = oficial_plate.concat(i);
    				}
    			}

    			mainFrame_admin.dispose();
    			setLayoutDisplay();
    			layoutDisplayHistoric(oficial_plate, oficial_cpf);
    		}
    	});

    }
    
    
    /**
     * Display in a new frame the vehicle's repair history and costs of each service order registered in its plate
     * 
     * @param oficial_plate -> The vehicle's plate
     * @param oficial_cpf -> The client's CPF
     */
    public static void layoutDisplayHistoric(String oficial_plate, String oficial_cpf){

        boolean clientExists = Driver.clients.containsKey(oficial_cpf);
        if (clientExists == false){

            mainFrame_admin.dispose();
    		setLayoutDisplay();

    		JOptionPane.showMessageDialog(null, "Cliente nao existente!", "Oficina - Mostrar historico", JOptionPane.ERROR_MESSAGE);

        }
        else{

            boolean vehicleExists = Driver.clients.get(oficial_cpf).vehicles.containsKey(oficial_plate);

            if (vehicleExists == false){
                mainFrame_admin.dispose();
    		    setLayoutDisplay();

    		    JOptionPane.showMessageDialog(null, "Veiculo nao existente!", "Oficina - Mostrar historico", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JFrame newFrame = new JFrame();
                JPanel newPanel = new JPanel();
                
                newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
                newFrame.setTitle("Oficina - Mostrar historico");
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                newFrame.setSize(450,500);
                newFrame.setLocationRelativeTo(null);
                newFrame.setVisible(true);
                
                JLabel service_order_id;
                JTextArea service_order_motive = new JTextArea();
                service_order_motive.setOpaque(false);
                service_order_motive.setBackground(new Color(0,0,0,0));
                service_order_motive.setLineWrap(true);
                service_order_motive.setWrapStyleWord(true);
                service_order_motive.setEditable(false);
                JLabel service_order_client;
                JLabel service_order_vehicle;
                
                GridBagLayout layout = new GridBagLayout();
                GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.insets = new Insets(5,5,5,5);
                
                // Table Header
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                service_order_id = new JLabel("ID");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                service_order_id = new JLabel("--");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 2;
                service_order_id = new JLabel("");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 0;
                service_order_id = new JLabel("|");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 1;
                service_order_id = new JLabel("-");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 2;
                service_order_id = new JLabel("");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 2;
                gridBagConstraints.gridy = 0;
                service_order_id = new JLabel("Motivo da ordem");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 2;
                gridBagConstraints.gridy = 1;
                service_order_id = new JLabel("---------------");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 2;
                gridBagConstraints.gridy = 2;
                service_order_id = new JLabel("");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 3;
                gridBagConstraints.gridy = 0;
                service_order_id = new JLabel("|");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 3;
                gridBagConstraints.gridy = 1;
                service_order_id = new JLabel("-");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 3;
                gridBagConstraints.gridy = 2;
                service_order_id = new JLabel("");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 4;
                gridBagConstraints.gridy = 0;
                service_order_client = new JLabel("Cliente");
                layout.setConstraints(service_order_client, gridBagConstraints);
                newPanel.add(service_order_client);
                
                gridBagConstraints.gridx = 4;
                gridBagConstraints.gridy = 1;
                service_order_client = new JLabel("-------");
                layout.setConstraints(service_order_client, gridBagConstraints);
                newPanel.add(service_order_client);
                
                gridBagConstraints.gridx = 4;
                gridBagConstraints.gridy = 2;
                service_order_client = new JLabel("");
                layout.setConstraints(service_order_client, gridBagConstraints);
                newPanel.add(service_order_client);
                
                gridBagConstraints.gridx = 5;
                gridBagConstraints.gridy = 0;
                service_order_id = new JLabel("|");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 5;
                gridBagConstraints.gridy = 1;
                service_order_id = new JLabel("-");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 5;
                gridBagConstraints.gridy = 2;
                service_order_id = new JLabel("");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 6;
                gridBagConstraints.gridy = 0;
                service_order_vehicle = new JLabel("Veiculo");
                layout.setConstraints(service_order_vehicle, gridBagConstraints);
                newPanel.add(service_order_vehicle);
                
                gridBagConstraints.gridx = 6;
                gridBagConstraints.gridy = 1;
                service_order_vehicle = new JLabel("-------");
                layout.setConstraints(service_order_vehicle, gridBagConstraints);
                newPanel.add(service_order_vehicle);
                
                gridBagConstraints.gridx = 6;
                gridBagConstraints.gridy = 2;
                service_order_vehicle = new JLabel("");
                layout.setConstraints(service_order_vehicle, gridBagConstraints);
                newPanel.add(service_order_vehicle);
                
                
                gridBagConstraints.gridx = 7;
                gridBagConstraints.gridy = 0;
                service_order_id = new JLabel("|");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 7;
                gridBagConstraints.gridy = 1;
                service_order_id = new JLabel("-");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 7;
                gridBagConstraints.gridy = 2;
                service_order_id = new JLabel("");
                layout.setConstraints(service_order_id, gridBagConstraints);
                newPanel.add(service_order_id);
                
                gridBagConstraints.gridx = 8;
                gridBagConstraints.gridy = 0;
                service_order_vehicle = new JLabel("Custo Total");
                layout.setConstraints(service_order_vehicle, gridBagConstraints);
                newPanel.add(service_order_vehicle);
                
                gridBagConstraints.gridx = 8;
                gridBagConstraints.gridy = 1;
                service_order_vehicle = new JLabel("-------");
                layout.setConstraints(service_order_vehicle, gridBagConstraints);
                newPanel.add(service_order_vehicle);
                
                gridBagConstraints.gridx = 8;
                gridBagConstraints.gridy = 2;
                service_order_vehicle = new JLabel("");
                layout.setConstraints(service_order_vehicle, gridBagConstraints);
                newPanel.add(service_order_vehicle);
                
                // Table Body
                int row = 2;
                int column = 0;
                
                for(Map.Entry<Integer, ServiceOrder> entry : Driver.clients.get(oficial_cpf).vehicles.get(oficial_plate).getHistoric().entrySet()) { 
                    row++;
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_id = new JLabel(String.valueOf(entry.getKey()));
                    layout.setConstraints(service_order_id, gridBagConstraints);
                    newPanel.add(service_order_id);
    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_id = new JLabel("|");
                    layout.setConstraints(service_order_id, gridBagConstraints);
                    newPanel.add(service_order_id);
    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_motive= new JTextArea(String.valueOf(entry.getValue().getMotivo()));
                    service_order_motive.setOpaque(false);
                    service_order_motive.setBackground(new Color(0,0,0,0));
                    service_order_motive.setLineWrap(true);
                    service_order_motive.setWrapStyleWord(true);
                    service_order_motive.setEditable(false);
                    layout.setConstraints(service_order_motive, gridBagConstraints);
                    newPanel.add(service_order_motive);
    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_id = new JLabel("|");
                    layout.setConstraints(service_order_id, gridBagConstraints);
                    newPanel.add(service_order_id);
    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_client= new JLabel(String.valueOf(entry.getValue().getOwner_cpf()));
                    layout.setConstraints(service_order_client, gridBagConstraints);
                    newPanel.add(service_order_client);
    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_id = new JLabel("|");
                    layout.setConstraints(service_order_id, gridBagConstraints);
                    newPanel.add(service_order_id);
    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_vehicle= new JLabel(String.valueOf(entry.getValue().getPlaca()));
                    layout.setConstraints(service_order_vehicle, gridBagConstraints);
                    newPanel.add(service_order_vehicle);
                    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_id = new JLabel("|");
                    layout.setConstraints(service_order_id, gridBagConstraints);
                    newPanel.add(service_order_id);
                    
                    gridBagConstraints.gridx = column;
                    column++;
                    gridBagConstraints.gridy = row;
                    service_order_vehicle= new JLabel(String.valueOf(entry.getValue().getCost()));
                    layout.setConstraints(service_order_vehicle, gridBagConstraints);
                    newPanel.add(service_order_vehicle);
                    column = 0;
                    
                    row++;
                    gridBagConstraints.gridx = column;
                    gridBagConstraints.gridy = row;
                    gridBagConstraints.gridwidth = 9;
                    gridBagConstraints.fill = GridBagConstraints.BOTH;
                    String temp = "ID | Motivo da ordem | cliente | veiculo | Custo Total";
                    service_order_id = new JLabel("" + "-".repeat((2 * temp.length() - 2)));
                    layout.setConstraints(service_order_id, gridBagConstraints);
                    newPanel.add(service_order_id, gridBagConstraints);
                    gridBagConstraints.gridwidth = 1;
                }
                
                
                // Layout
                newPanel.setLayout(layout);
                JScrollPane scrollLayout = new JScrollPane(newPanel);
                scrollLayout.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                newFrame.add(scrollLayout, BorderLayout.CENTER);
            }

        }


    }
       
}
