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
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.Document;
import javax.swing.text.MaskFormatter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.border.*;



public class Vendor extends User {
    
    private int id;

    // GUI variables
    private static JFrame mainFrame_vendor;
    private static JPanel mainPanel_vendor;
    private static JButton exit;
    

    public Vendor() {
    }

    public Vendor(int id, String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
        this.id = id;
    }

    
    
    /**
     * @return The vendor's id -> 3
     */
    public int getId() {
        return id;
    }

    /**
     * @param id -> the ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    
    
    /**
     * @return A String containing the relevant information of the vendor
     */
    public String toString(){
        return "------- Vendor -------\n" + super.toString();
    }

    
    // Layout
    
    /**
     * Set the frame's layout configuration, such as size, closing action, title.
     */
    public static void setLayoutDisplay() {
    	
    	Login.getMainFrame_login().dispose();	

    	mainFrame_vendor = new JFrame(); 
    	mainPanel_vendor = new JPanel();
                  
    	mainPanel_vendor.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
                  
    	mainFrame_vendor.add(mainPanel_vendor);
    	mainFrame_vendor.setTitle("Oficina - Vendedor");
    	mainFrame_vendor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame_vendor.setMinimumSize(new Dimension(550,550)); 
    	mainFrame_vendor.setSize(750,650); 
    	mainFrame_vendor.setLocationRelativeTo(null);
    	mainFrame_vendor.setVisible(true);

    	layoutDisplay();
    	
    }
    
    /**
     * Creates all the layout frontend and most of the button handlers
     */
    public static void layoutDisplay() {

    	// Layout
    	mainPanel_vendor.removeAll();

    	exit = new JButton("Sair");

    	JButton lookup_pending_order = new JButton("Ordens de servico pendentes");
    	JButton lookup_executed_order = new JButton("Ordens de servico executadas");
    	JButton approve_pending_order = new JButton("Aprovar ordem pendente");
    	JButton conclude_executed_order = new JButton("Fechar ordem executada");
    	JButton register_client = new JButton("Cadastrar Cliente");
    	JButton register_vehicle = new JButton("Cadastrar Veiculo");
    	JButton generate_service_order = new JButton("Gerar ordem");


    	GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints gridBagConstraints = new GridBagConstraints();

    	gridBagConstraints.ipady = 15;
    	gridBagConstraints.insets = new Insets(2,2,2,2);
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 0;
    	layout.setConstraints(lookup_pending_order, gridBagConstraints);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	layout.setConstraints(lookup_executed_order, gridBagConstraints);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	layout.setConstraints(approve_pending_order, gridBagConstraints);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 3;
    	layout.setConstraints(conclude_executed_order, gridBagConstraints);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 4;
    	layout.setConstraints(register_client, gridBagConstraints);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 5;
    	layout.setConstraints(register_vehicle, gridBagConstraints);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 6;
    	layout.setConstraints(generate_service_order, gridBagConstraints);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 7;
    	gridBagConstraints.insets = new Insets(20,2,2,2);
    	layout.setConstraints(exit, gridBagConstraints);

    	mainPanel_vendor.setLayout(layout);
    	mainPanel_vendor.add(lookup_pending_order);
    	mainPanel_vendor.add(lookup_executed_order);
    	mainPanel_vendor.add(approve_pending_order);
    	mainPanel_vendor.add(conclude_executed_order);
    	mainPanel_vendor.add(register_client);
    	mainPanel_vendor.add(register_vehicle);
    	mainPanel_vendor.add(generate_service_order);
    	mainPanel_vendor.add(exit);


    	// Handlers
    	lookup_pending_order.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			layoutPendingServiceOrder();
    		}
    	});

    	lookup_executed_order.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			layoutExecutedServiceOrder();
    		}
    	});

    	approve_pending_order.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				layoutApproveSO();
    		}
    	});

    	conclude_executed_order.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				layoutConcludeSO();
    		}
    	});

    	register_client.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			layoutRegisterClient();
    		}
    	});

    	register_vehicle.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			layoutRegisterVehicle();
    		}
    	});

    	generate_service_order.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				layoutCreateSO();
    		}
    	});

    	exit.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			mainFrame_vendor.dispose();
    			Login.setFrame_login();
    		}
    	});

    }

    
    
    /**
     * Creates a new frame and displays all pending service orders containing in the {@code Driver.pending_services} map
     */
    public static void layoutPendingServiceOrder() {

    	JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Ordens de servicos pendentes");
    	newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	newFrame.setSize(450,500);
    	newFrame.setResizable(false);
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
    	
    	// Table Body
    	int row = 2;
    	int column = 0;
    	for(Map.Entry<Integer, ServiceOrder> entry : Driver.pending_services.entrySet()) { 
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
			column = 0;

			row++;
			gridBagConstraints.gridx = column;
			gridBagConstraints.gridy = row;
			gridBagConstraints.gridwidth = 7;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			String temp = "ID | Motivo da ordem | cliente | veiculo";
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

    /**
     * Creates a new frame and displays all executed service orders, marked as executed by a mechanic in the {@code Driver.executed_services} map
     */
    public static void layoutExecutedServiceOrder() {
    	
    	JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Ordens de servicos executadas");
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
    	
    	// Table Body
    	int row = 2;
    	int column = 0;
    	for(Map.Entry<Integer, ServiceOrder> entry : Driver.executed_services.entrySet()) { 
    		if(entry.getValue().isApproved() && entry.getValue().isExecuted()) {
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
    			column = 0;
				
				row++;
				gridBagConstraints.gridx = column;
				gridBagConstraints.gridy = row;
				gridBagConstraints.gridwidth = 7;
				gridBagConstraints.fill = GridBagConstraints.BOTH;
				String temp = "ID | Motivo da ordem | cliente | veiculo";
				service_order_id = new JLabel("" + "-".repeat((2 * temp.length() - 2)));
				layout.setConstraints(service_order_id, gridBagConstraints);
				newPanel.add(service_order_id, gridBagConstraints);
				gridBagConstraints.gridwidth = 1;
    		}
    	}
    	
    	
    	// Layout
    	newPanel.setLayout(layout);
    	JScrollPane scrollLayout = new JScrollPane(newPanel);
    	scrollLayout.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	newFrame.add(scrollLayout, BorderLayout.CENTER);
    	
    }

    
    
    /**
     * Displays the frame responsible for registering a client, it overwrites all content in the {@code Vendor.mainFrame_vendor} 
     */
    public static void layoutRegisterClient() {
    	mainPanel_vendor.removeAll();

    	mainPanel_vendor.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	GridBagLayout layout = new GridBagLayout();

    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
    	gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    	JLabel client_name = new JLabel("Digite o nome do cliente");
    	JLabel client_cpf = new JLabel("Digite o CPF do cliente");

    	JFormattedTextField name = new JFormattedTextField();
    	JFormattedTextField cpf = new JFormattedTextField();


    	try {
    		MaskFormatter cpf_mask = new MaskFormatter("### . ### . ### - ##"); // Formats the text field of cpf variable
    		cpf_mask.setPlaceholderCharacter('_');
    		cpf_mask.install(cpf);

    	} catch (ParseException e) {
    		System.out.println(e);
    	}


    	name.setBorder(BorderFactory.createCompoundBorder(name.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
    	cpf.setBorder(BorderFactory.createCompoundBorder(cpf.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

    	gridBagConstraints.insets = new Insets(2,2,2,2);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 0;
    	layout.setConstraints(client_name, gridBagConstraints);
    	mainPanel_vendor.add(client_name);
    	gridBagConstraints.insets = new Insets(2,2,30,2);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	layout.setConstraints(name, gridBagConstraints);
    	mainPanel_vendor.add(name);

    	gridBagConstraints.insets = new Insets(2,2,2,2);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	layout.setConstraints(client_cpf, gridBagConstraints);
    	mainPanel_vendor.add(client_cpf);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 3;
    	layout.setConstraints(cpf, gridBagConstraints);
    	mainPanel_vendor.add(cpf);


    	gridBagConstraints.insets = new Insets(30,2,2,2);
    	JButton register_cliente = new JButton("Registrar");
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 4;
    	layout.setConstraints(register_cliente, gridBagConstraints);
    	mainPanel_vendor.add(register_cliente);

    	mainPanel_vendor.setLayout(layout);

    	mainFrame_vendor.add(mainPanel_vendor);
    	mainFrame_vendor.setTitle("Oficina - Registrar cliente");
    	mainFrame_vendor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame_vendor.setMinimumSize(new Dimension(400,300)); 
    	mainFrame_vendor.setSize(450,550); 
    	mainFrame_vendor.setLocationRelativeTo(null);
    	mainFrame_vendor.setVisible(true);

    	register_cliente.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {

    			String oficial_name = name.getText(); 
    			String[] user_cpf = cpf.getText().split("", 0);
    			String oficial_cpf = "";

    			for (String i : user_cpf) {
    				if (!i.equals(".") && !i.equals("-") && !i.equals(" ") && !i.equals("_")) {
    					oficial_cpf = oficial_cpf.concat(i);
    				}
    			}

    			if (oficial_cpf != "" && oficial_name != ""){
	    			Client client = new Client(oficial_name, oficial_cpf);
	
	    	        boolean aux = Driver.clients.containsKey(oficial_cpf);
	
	    	        if (aux == false){
	    	            Driver.clients.put(oficial_cpf, client);
	    	            
						mainFrame_vendor.dispose();
						setLayoutDisplay();
	
						JOptionPane.showMessageDialog(null, "Cliente Registrado com sucesso!", "Oficina - Registrar cliente", JOptionPane.INFORMATION_MESSAGE);
	
	    	        }
	    	        else {
						mainFrame_vendor.dispose();
						setLayoutDisplay();
	
						JOptionPane.showMessageDialog(null, "Cliente ja existente!", "Oficina - Registrar cliente", JOptionPane.ERROR_MESSAGE);
	
	    	        }
    			}
    			else {
					mainFrame_vendor.dispose();
					setLayoutDisplay();

					JOptionPane.showMessageDialog(null, "Dados insuficientes!", "Oficina - Registrar cliente", JOptionPane.ERROR_MESSAGE);
    			}
	    		
    		}
    	});

    }

    /**
     * Displays the frame responsible for registering a client's vehicle, it overwrites all content in the {@code Vendor.mainFrame_vendor} 
     */
    public static void layoutRegisterVehicle() {
    	mainPanel_vendor.removeAll();

    	mainPanel_vendor.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	GridBagLayout layout = new GridBagLayout();

    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
    	gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    	JLabel client_cpf = new JLabel("Digite o CPF do cliente");
    	JLabel vehicle_plate = new JLabel("Digite a placa do veiculo");
    	JLabel vehicle_km = new JLabel("Digite a kilometragem do veiculo");

    	JFormattedTextField plate = new JFormattedTextField();
    	JFormattedTextField cpf = new JFormattedTextField();
    	JFormattedTextField km = new JFormattedTextField();


    	try {
    		MaskFormatter plate_mask = new MaskFormatter("UUU - ####");
    		MaskFormatter cpf_mask = new MaskFormatter("### . ### . ### - ##");
    		plate_mask.setPlaceholderCharacter('_');
    		cpf_mask.setPlaceholderCharacter('_');
    		plate_mask.install(plate);
    		cpf_mask.install(cpf);

    	} catch (ParseException e) {
    		System.out.println(e);
    	}


    	plate.setBorder(BorderFactory.createCompoundBorder(plate.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
    	km.setBorder(BorderFactory.createCompoundBorder(km.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
    	cpf.setBorder(BorderFactory.createCompoundBorder(cpf.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

    	gridBagConstraints.insets = new Insets(2,2,2,2);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 0;
    	layout.setConstraints(client_cpf, gridBagConstraints);
    	mainPanel_vendor.add(client_cpf);
    	gridBagConstraints.insets = new Insets(2,2,30,2);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	layout.setConstraints(cpf, gridBagConstraints);
    	mainPanel_vendor.add(cpf);

    	gridBagConstraints.insets = new Insets(2,2,2,2);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	layout.setConstraints(vehicle_plate, gridBagConstraints);
    	mainPanel_vendor.add(vehicle_plate);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 3;
    	layout.setConstraints(plate, gridBagConstraints);
    	mainPanel_vendor.add(plate);    

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 4;
    	layout.setConstraints(vehicle_km, gridBagConstraints);
    	mainPanel_vendor.add(vehicle_km);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 5;
    	layout.setConstraints(km, gridBagConstraints);
    	mainPanel_vendor.add(km);


    	gridBagConstraints.insets = new Insets(30,2,2,2);
    	JButton register_vehicle = new JButton("Registrar");
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 6;
    	layout.setConstraints(register_vehicle, gridBagConstraints);
    	mainPanel_vendor.add(register_vehicle);

    	mainPanel_vendor.setLayout(layout);

    	mainFrame_vendor.add(mainPanel_vendor);
    	mainFrame_vendor.setTitle("Oficina - Registrar veiculo");
    	mainFrame_vendor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame_vendor.setMinimumSize(new Dimension(400,300)); 
    	mainFrame_vendor.setSize(450,550); 
    	mainFrame_vendor.setLocationRelativeTo(null);
    	mainFrame_vendor.setVisible(true);

    	register_vehicle.addActionListener(new ActionListener() {
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

    			boolean aux = Driver.clients.containsKey(oficial_cpf);

    			if (aux == false){
    				mainFrame_vendor.dispose();
    				setLayoutDisplay();

    				JOptionPane.showMessageDialog(null, "Cliente nao existente!", "Oficina - Registrar veiculo", JOptionPane.ERROR_MESSAGE);

    			}
    			else {
    				aux = Driver.clients.get(oficial_cpf).vehicles.containsKey(oficial_plate);

    				if (aux == false){
    					Vehicle vehicle = new Vehicle(oficial_plate, Double.parseDouble(km.getText()), oficial_cpf);

    					Driver.clients.get(oficial_cpf).push_vehicles(oficial_plate, vehicle);

    					mainFrame_vendor.dispose();
    					setLayoutDisplay();

    					JOptionPane.showMessageDialog(null, "Veiculo registrado com sucesso!", "Oficina - Registrar veiculo", JOptionPane.INFORMATION_MESSAGE);

    				}
    				else{
    					mainFrame_vendor.dispose();
    					setLayoutDisplay();

    					JOptionPane.showMessageDialog(null, "Veiculo ja existente!", "Oficina - Registrar veiculo", JOptionPane.ERROR_MESSAGE);
    				}

    			}

    		}
    	});

    }


    
    /**
     * Displays the frame responsible for registering a new serviceOrder, it overwrites all content in the {@code Vendor.mainFrame_vendor} 
     */
	public static void layoutCreateSO(){

		mainPanel_vendor.removeAll();

    	mainPanel_vendor.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	GridBagLayout layout = new GridBagLayout();

    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
    	gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    	JLabel client_cpf = new JLabel("Digite o CPF do cliente");
    	JLabel vehicle_plate = new JLabel("Digite a placa do veiculo");
    	JLabel motivo_label = new JLabel("Digite o motivo");

    	JFormattedTextField cpf = new JFormattedTextField();
    	JFormattedTextField plate = new JFormattedTextField();
		JTextArea motivo = new JTextArea();
		motivo.setLineWrap(true);
		motivo.setWrapStyleWord(true);
		
		try {
    		MaskFormatter plate_mask = new MaskFormatter("UUU - ####");
    		MaskFormatter cpf_mask = new MaskFormatter("### . ### . ### - ##");
    		plate_mask.setPlaceholderCharacter('_');
    		cpf_mask.setPlaceholderCharacter('_');
    		plate_mask.install(plate);
    		cpf_mask.install(cpf);

    	} catch (ParseException e) {
    		System.out.println(e);
    	}
		
		
		motivo.setBorder(BorderFactory.createCompoundBorder(motivo.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
		plate.setBorder(BorderFactory.createCompoundBorder(plate.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
		cpf.setBorder(BorderFactory.createCompoundBorder(cpf.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
		
		gridBagConstraints.insets = new Insets(2,2,2,2);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 0;
    	layout.setConstraints(client_cpf, gridBagConstraints);
    	mainPanel_vendor.add(client_cpf);
    	gridBagConstraints.insets = new Insets(2,2,30,2);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	layout.setConstraints(cpf, gridBagConstraints);
    	mainPanel_vendor.add(cpf);

    	gridBagConstraints.insets = new Insets(2,2,2,2);

    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	layout.setConstraints(vehicle_plate, gridBagConstraints);
    	mainPanel_vendor.add(vehicle_plate);
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 3;
    	layout.setConstraints(plate, gridBagConstraints);
    	mainPanel_vendor.add(plate);    
		
		
    	gridBagConstraints.insets = new Insets(30,2,2,2);
    	JButton register_so = new JButton("Registrar");
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 6;
    	layout.setConstraints(register_so, gridBagConstraints);
		
    	gridBagConstraints.insets = new Insets(2,2,2,2);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		layout.setConstraints(motivo_label, gridBagConstraints);
		mainPanel_vendor.add(motivo_label);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipady = 50;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		JScrollPane ajuste = new JScrollPane(motivo);
		ajuste.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		layout.setConstraints(ajuste, gridBagConstraints);
		mainPanel_vendor.add(ajuste);
		
		mainPanel_vendor.add(register_so);
		
    	mainPanel_vendor.setLayout(layout);

    	mainFrame_vendor.add(mainPanel_vendor);
    	mainFrame_vendor.setTitle("Oficina - Registrar ordem de servico");
    	mainFrame_vendor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame_vendor.setMinimumSize(new Dimension(400,300)); 
    	mainFrame_vendor.setSize(450,550); 
    	mainFrame_vendor.setLocationRelativeTo(null);
    	mainFrame_vendor.setVisible(true);

		register_so.addActionListener(new ActionListener() {
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

    			boolean aux = Driver.clients.containsKey(oficial_cpf);

    			if (aux == false){
    				mainFrame_vendor.dispose();
    				setLayoutDisplay();

    				JOptionPane.showMessageDialog(null, "Cliente nao existente!", "Oficina - Registrar ordem de servico", JOptionPane.ERROR_MESSAGE);

    			}
    			else {
    				aux = Driver.clients.get(oficial_cpf).vehicles.containsKey(oficial_plate);

    				if (aux == true){
						double kilometragem = Driver.clients.get(oficial_cpf).vehicles.get(oficial_plate).getKm();
						ServiceOrder temp = new ServiceOrder(motivo.getText(), oficial_plate, kilometragem, oficial_cpf);

						Driver.clients.get(oficial_cpf).vehicles.get(oficial_plate).push_historic(ServiceOrder.count - 1, temp);
						Driver.pushPendingServices(ServiceOrder.count - 1,  temp);

    					mainFrame_vendor.dispose();
    					setLayoutDisplay();

    					JOptionPane.showMessageDialog(null, "Ordem de servico registrada com sucesso!", "Oficina - Registrar ordem de servico", JOptionPane.INFORMATION_MESSAGE);

    				}
    				else{
    					mainFrame_vendor.dispose();
    					setLayoutDisplay();

    					JOptionPane.showMessageDialog(null, "Veiculo nao cadastrado!", "Oficina - Registrar ordem de servico", JOptionPane.ERROR_MESSAGE);
    				}
 
    			}

    		}
    	});

	}

    /**
     * Creates a new frame responsible for gathering all information necessary to approve a service order
     */
	public static void layoutApproveSO(){

		JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Aprovar ordem de servico");
    	newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	newFrame.setSize(250,350);
    	newFrame.setResizable(false);
    	newFrame.setLocationRelativeTo(null);
    	newFrame.setVisible(true);
    	
    	JLabel service_order_id;
    	JTextField order_id = new JTextField();
    	JButton ok = new JButton("Confirmar");
    	
        GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2,2,2,2);
    	
    	gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        service_order_id = new JLabel("ID da ordem de servico");
    	layout.setConstraints(service_order_id, gridBagConstraints);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    	order_id.setBorder(BorderFactory.createCompoundBorder(order_id.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
    	layout.setConstraints(order_id, gridBagConstraints);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	gridBagConstraints.ipady = 15;
    	gridBagConstraints.insets = new Insets(15,2,2,2);
    	layout.setConstraints(ok, gridBagConstraints);
    	
    	
    	
    	ok.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			ServiceOrder so;
    			try {
    				so = Driver.pending_services.get(Integer.valueOf(order_id.getText()));
    			}
    			catch(Exception exception) {
    				so = null;
    			}
    				
    			if (so != null) {	
					so.setApproved(true);
					Driver.popPendingServices(Integer.valueOf(order_id.getText()));
					Driver.pushExecutedServices(Integer.valueOf(order_id.getText()), so);
					JOptionPane.showMessageDialog(null, "Ordem de servico marcada como aprovada", "Oficina - Aprovar ordem de servico", JOptionPane.INFORMATION_MESSAGE);
					newFrame.dispose();
					mainFrame_vendor.dispose();
					setLayoutDisplay();
    				
    			}
    			else {
					JOptionPane.showMessageDialog(null, "Ordem de servico nao encontrada", "Oficina - Aprovar ordem de servico", JOptionPane.ERROR_MESSAGE);
					newFrame.dispose();
					mainFrame_vendor.dispose();
					setLayoutDisplay();
    			}
    		}
    	});
    	
    	
    	
    	// Layout
    	newPanel.add(service_order_id);
    	newPanel.add(order_id);
    	newPanel.add(ok);
    	newPanel.setLayout(layout);
		newFrame.add(newPanel, BorderLayout.CENTER);

	}

    /**
     * Creates a new frame responsible for gathering all information necessary to conclude a service order
     */
	public static void layoutConcludeSO(){

		JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Fechar ordem de servico");
    	newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	newFrame.setSize(250,350);
    	newFrame.setResizable(false);
    	newFrame.setLocationRelativeTo(null);
    	newFrame.setVisible(true);
    	
    	JLabel service_order_id;
    	JTextField order_id = new JTextField();
    	JButton ok = new JButton("Confirmar");
    	
        GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2,2,2,2);
    	
    	gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        service_order_id = new JLabel("ID da ordem de servico");
    	layout.setConstraints(service_order_id, gridBagConstraints);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    	order_id.setBorder(BorderFactory.createCompoundBorder(order_id.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
    	layout.setConstraints(order_id, gridBagConstraints);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	gridBagConstraints.ipady = 15;
    	gridBagConstraints.insets = new Insets(15,2,2,2);
    	layout.setConstraints(ok, gridBagConstraints);
    	
    	
    	
    	ok.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			ServiceOrder so;
    			
    			try{
    				so = Driver.executed_services.get(Integer.valueOf(order_id.getText()));
    			}
    			catch(Exception exception) {
    				so = null;
    			}
    			
    			if (so != null) {	
					if (so.isExecuted() == true){
						so.setConcluded(true);
						Driver.popExecutedServices(Integer.valueOf(order_id.getText()));
						Driver.pushConcludedServices(Integer.valueOf(order_id.getText()), so);
						JOptionPane.showMessageDialog(null, "Ordem de servico marcada como fechada", "Oficina - Fechar ordem de servico", JOptionPane.INFORMATION_MESSAGE);
						newFrame.dispose();
						mainFrame_vendor.dispose();
						setLayoutDisplay();
					}
					else{
						JOptionPane.showMessageDialog(null, "Ordem de servico nao encontrada", "Oficina - Fechar ordem de servico", JOptionPane.ERROR_MESSAGE);
						newFrame.dispose();
						mainFrame_vendor.dispose();
						setLayoutDisplay();
					}    				
    			}
    			else {
					JOptionPane.showMessageDialog(null, "Ordem de servico nao encontrada", "Oficina - Aprovar ordem de servico", JOptionPane.ERROR_MESSAGE);
					newFrame.dispose();
					mainFrame_vendor.dispose();
					setLayoutDisplay();
    			}
    		}
    	});
    	
    	
    	
    	// Layout
    	newPanel.add(service_order_id);
    	newPanel.add(order_id);
    	newPanel.add(ok);
    	newPanel.setLayout(layout);
		newFrame.add(newPanel, BorderLayout.CENTER);

	}
	
}