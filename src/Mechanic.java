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



public class Mechanic extends User{
    
    private int id;
    
    // GUI variables
    private static JFrame mainFrame_mec;
    private static JPanel mainPanel_mec;
    private static JButton exit;

    public Mechanic() {
    }

    public Mechanic(int id, String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
        this.id = id;
    }
    
    
    
    /**
     * @return The vendor's id -> 2
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
        return "------- Mechanic -------\n" + super.toString();
    }

    
    // Layout
    
    /**
     * Set the frame's layout configuration, such as size, closing action, title.
     */
    public static void setLayoutDisplay() {
    	
    	Login.getMainFrame_login().dispose();	

    	mainFrame_mec = new JFrame(); 
    	mainPanel_mec = new JPanel();

    	mainPanel_mec.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

    	mainFrame_mec.add(mainPanel_mec);
    	mainFrame_mec.setTitle("Oficina - Mecanico");
    	mainFrame_mec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame_mec.setMinimumSize(new Dimension(550,550)); 
    	mainFrame_mec.setSize(750,650); 
    	mainFrame_mec.setLocationRelativeTo(null);
    	mainFrame_mec.setVisible(true);

    	layoutDisplay();
    	
    }
    
    /**
     * Creates all the layout frontend and most of the button handlers
     */
    public static void layoutDisplay() {
    	
		// Layout
    	mainPanel_mec.removeAll();
    	
    	exit = new JButton("Sair");
    	
    	JButton lookup_service_order = new JButton("Ordens de servico");
    	JButton lookup_services = new JButton("Servicos disponiveis");
    	JButton lookup_auto_parts = new JButton("Pecas disponiveis");
    	JButton register_service_in_so = new JButton("Cadastrar servico executado");
    	JButton register_autopart_in_so = new JButton("Cadastrar peca utilizada");
    	JButton execute_service_order = new JButton("Executar ordem de servico");
    	
    	GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
    	
    	gridBagConstraints.ipady = 15;
    	gridBagConstraints.insets = new Insets(2,2,2,2);
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    	
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(lookup_service_order, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(register_service_in_so, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(register_autopart_in_so, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(lookup_services, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(lookup_auto_parts, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        layout.setConstraints(execute_service_order, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(20,2,2,2);
        layout.setConstraints(exit, gridBagConstraints);
        
        mainPanel_mec.setLayout(layout);
        mainPanel_mec.add(lookup_service_order);
        mainPanel_mec.add(register_service_in_so);
        mainPanel_mec.add(register_autopart_in_so);
        mainPanel_mec.add(lookup_services);
        mainPanel_mec.add(lookup_auto_parts);
        mainPanel_mec.add(execute_service_order);
        mainPanel_mec.add(exit);
    	
        
        // Handlers
        lookup_service_order.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		layoutServiceOrder();
        	}
        });
        
        register_service_in_so.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				mainFrame_mec.dispose();
        		layoutRegisterService();
        	}
        });
        
        register_autopart_in_so.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		layoutRegisterAutoPart();
        	}
        });
        
        lookup_services.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		layoutServices();
        	}
        });
        
        lookup_auto_parts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		layoutParts();
        	}
        });
        
        
        execute_service_order.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		layoutExecuteServiceOrder();
        	}
        });
        
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainFrame_mec.dispose();
        		Login.setFrame_login();
        	}
        });
        
	}

    
    
    /**
     * Creates a new frame responsible for gathering all relevant information to mark a service order as executed
     */
    public static void layoutExecuteServiceOrder() {

    	JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Executar ordem de servico");
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
    				so = Driver.executed_services.get(Integer.valueOf(order_id.getText()));
    			}
    			catch(Exception exception){
    				so = null;
    			}
    			
    			if (so != null) {	
    				so.setExecuted(true);
					JOptionPane.showMessageDialog(null, "Ordem de servico marcada como executada", "Oficina - Executar ordem de servico", JOptionPane.INFORMATION_MESSAGE);					
					newFrame.dispose();
					mainFrame_mec.dispose();
					setLayoutDisplay();
    				
    			}
    			else {
					JOptionPane.showMessageDialog(null, "Ordem de servico nao encontrada", "Oficina - Executar ordem de servico", JOptionPane.ERROR_MESSAGE);
					newFrame.dispose();
					mainFrame_mec.dispose();
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
     * Creates a new frame and displays all available automobile parts
     */
	public static void layoutParts() {
    	
    	JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Pecas disponiveis");
    	newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	newFrame.setSize(350,500);
    	newFrame.setResizable(false);
    	newFrame.setLocationRelativeTo(null);
    	newFrame.setVisible(true);
    	
    	JLabel part_name;
    	JLabel part_price;
   
        GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5,5,5,5);
    	
    	// Table Header
    	gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        part_name = new JLabel("Nome da peca");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	part_name = new JLabel("---------------");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	part_name = new JLabel("");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 1;
    	gridBagConstraints.gridy = 0;
    	part_name = new JLabel("|");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 1;
    	gridBagConstraints.gridy = 1;
    	part_name = new JLabel("-");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 1;
    	gridBagConstraints.gridy = 2;
    	part_name = new JLabel("");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 2;
    	gridBagConstraints.gridy = 0;
    	part_name = new JLabel("Preco da peca");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 2;
    	gridBagConstraints.gridy = 1;
    	part_name = new JLabel("----------------");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	gridBagConstraints.gridx = 2;
    	gridBagConstraints.gridy = 2;
    	part_name = new JLabel("");
    	layout.setConstraints(part_name, gridBagConstraints);
    	newPanel.add(part_name);
    	
    	
    	// Table Body
    	int row = 2;
    	int column = 0;
    	for(Map.Entry<String, AutoPart> entry : Driver.available_automobile_parts.entrySet()) {
    		row++;
        	gridBagConstraints.gridx = column;
        	column++;
        	gridBagConstraints.gridy = row;
    		part_name = new JLabel(entry.getKey());
    		layout.setConstraints(part_name, gridBagConstraints);
        	newPanel.add(part_name);
        	
        	gridBagConstraints.gridx = column;
        	column++;
        	gridBagConstraints.gridy = row;
        	part_name = new JLabel("|");
    		layout.setConstraints(part_name, gridBagConstraints);
        	newPanel.add(part_name);
        	
        	gridBagConstraints.gridx = column;
        	column++;
        	gridBagConstraints.gridy = row;
        	part_price= new JLabel(String.valueOf(entry.getValue().getPart_price()));
        	layout.setConstraints(part_price, gridBagConstraints);
        	newPanel.add(part_price);
        	column = 0;
    	}
    	
    	
    	// Layout
    	newPanel.setLayout(layout);
    	JScrollPane scrollLayout = new JScrollPane(newPanel);
    	scrollLayout.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		newFrame.add(scrollLayout, BorderLayout.CENTER);
		
	}
    
    /**
     * Creates a new frame and displays all available services
     */
	public static void layoutServices() {
    	
    	JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Servicos disponiveis");
    	newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	newFrame.setSize(350,500);
    	newFrame.setResizable(false);
    	newFrame.setLocationRelativeTo(null);
    	newFrame.setVisible(true);
    	
    	JLabel service_name;
    	JLabel service_price;
   
        GridBagLayout layout = new GridBagLayout();
    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5,5,5,5);
    	
    	// Table Header
    	gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        service_name = new JLabel("Nome do servico");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 1;
    	service_name = new JLabel("---------------");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 0;
    	gridBagConstraints.gridy = 2;
    	service_name = new JLabel("");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 1;
    	gridBagConstraints.gridy = 0;
    	service_name = new JLabel("|");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 1;
    	gridBagConstraints.gridy = 1;
    	service_name = new JLabel("-");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 1;
    	gridBagConstraints.gridy = 2;
    	service_name = new JLabel("");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 2;
    	gridBagConstraints.gridy = 0;
    	service_name = new JLabel("Preco do servico");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 2;
    	gridBagConstraints.gridy = 1;
    	service_name = new JLabel("----------------");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	gridBagConstraints.gridx = 2;
    	gridBagConstraints.gridy = 2;
    	service_name = new JLabel("");
    	layout.setConstraints(service_name, gridBagConstraints);
    	newPanel.add(service_name);
    	
    	
    	// Table Body
    	int row = 2;
    	int column = 0;
    	for(Map.Entry<String, Service> entry : Driver.available_services.entrySet()) {
    		row++;
        	gridBagConstraints.gridx = column;
        	column++;
        	gridBagConstraints.gridy = row;
    		service_name = new JLabel(entry.getKey());
    		layout.setConstraints(service_name, gridBagConstraints);
        	newPanel.add(service_name);
        	
        	gridBagConstraints.gridx = column;
        	column++;
        	gridBagConstraints.gridy = row;
        	service_name = new JLabel("|");
    		layout.setConstraints(service_name, gridBagConstraints);
        	newPanel.add(service_name);
        	
        	gridBagConstraints.gridx = column;
        	column++;
        	gridBagConstraints.gridy = row;
        	service_price= new JLabel(String.valueOf(entry.getValue().getService_price()));
        	layout.setConstraints(service_price, gridBagConstraints);
        	newPanel.add(service_price);
        	column = 0;
    	}
    	
    	
    	// Layout
    	
    	newPanel.setLayout(layout);
    	JScrollPane scrollLayout = new JScrollPane(newPanel);
    	scrollLayout.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		newFrame.add(scrollLayout, BorderLayout.CENTER);
		
	}
	
	
    
    /**
     * Displays the frame responsible for registering an automobile part used in a service order
     */
	public static void layoutRegisterAutoPart() {
		
		mainPanel_mec.removeAll();

		mainPanel_mec.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		GridBagLayout layout = new GridBagLayout();

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel id_label = new JLabel("Digite o ID da ordem de servico");
        JLabel peca_label = new JLabel("Digite o nome da peca");
        JLabel qntd_label = new JLabel("Digite a quantidade de pecas");

        JFormattedTextField id = new JFormattedTextField();
		JFormattedTextField peca = new JFormattedTextField();
		JFormattedTextField qntd = new JFormattedTextField();
		
		id.setBorder(BorderFactory.createCompoundBorder(id.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
		peca.setBorder(BorderFactory.createCompoundBorder(peca.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
		qntd.setBorder(BorderFactory.createCompoundBorder(qntd.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

		gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(id_label, gridBagConstraints);
        mainPanel_mec.add(id_label);
        gridBagConstraints.insets = new Insets(2,2,30,2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(id, gridBagConstraints);
        mainPanel_mec.add(id);

		gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(peca_label, gridBagConstraints);
        mainPanel_mec.add(peca_label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(peca, gridBagConstraints);
		mainPanel_mec.add(peca);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		layout.setConstraints(qntd_label, gridBagConstraints);
		mainPanel_mec.add(qntd_label);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		layout.setConstraints(qntd, gridBagConstraints);
		mainPanel_mec.add(qntd);
		
		gridBagConstraints.insets = new Insets(30,2,2,2);
        JButton adicionar_peca = new JButton("Adicionar");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        layout.setConstraints(adicionar_peca, gridBagConstraints);
		mainPanel_mec.add(adicionar_peca);
		
		mainPanel_mec.setLayout(layout);

		mainFrame_mec.add(mainPanel_mec);
		mainFrame_mec.setTitle("Oficina - Adicionar pecas");
		mainFrame_mec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame_mec.setMinimumSize(new Dimension(400,300)); 
		mainFrame_mec.setSize(450,550); 
		mainFrame_mec.setLocationRelativeTo(null);
		mainFrame_mec.setVisible(true);
		
		adicionar_peca.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

				String aux = id.getText();
				int value_id = -1;
				
				try {
					value_id = Integer.parseInt(aux);
				}
				catch(Exception exception) {
					value_id = -1;
				}
				
				String value_peca = peca.getText();

				ServiceOrder so = Driver.executed_services.get(value_id);

				if (so != null){
					
					if (so.isApproved() && !so.isExecuted()){
						if (Driver.available_automobile_parts.containsKey(value_peca) == true){
							
							AutoPart temp = new AutoPart();
							temp.setPart_name(Driver.available_automobile_parts.get(value_peca).getPart_name());
							temp.setPart_price(Driver.available_automobile_parts.get(value_peca).getPart_price());
							temp.setQuantity(Integer.parseInt(qntd.getText()));;
							so.setCost(so.getCost() + (temp.getPart_price() * temp.getQuantity()));
							Driver.executed_services.get(value_id).push_automobile_parts(value_peca, temp);

							mainFrame_mec.dispose();
							setLayoutDisplay();
		
							JOptionPane.showMessageDialog(null, "Peca adicionada com sucesso!", "Oficina - Adicionar peca", JOptionPane.INFORMATION_MESSAGE);
		
						}
						else{
							JOptionPane.showMessageDialog(null, "Peca nao encontrada", "Oficina - Adicionar peca", JOptionPane.ERROR_MESSAGE);
							mainFrame_mec.dispose();
							setLayoutDisplay();
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Ordem de servico nao encontrada", "Oficina - Adicionar peca", JOptionPane.ERROR_MESSAGE);
					mainFrame_mec.dispose();
					setLayoutDisplay();
				}
			}
		});

	}

    /**
     * Displays the frame responsible for registering a service executed in a service order
     */
	public static void layoutRegisterService() {
		
		mainPanel_mec.removeAll();

		mainPanel_mec.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		GridBagLayout layout = new GridBagLayout();

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2,2,2,2); // In case the is more than 1 button - top - left - bottom - right
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel id_label = new JLabel("Digite o ID da ordem de servico");
        JLabel servico_label = new JLabel("Digite o nome do servico");

        JFormattedTextField id = new JFormattedTextField();
		JFormattedTextField servico = new JFormattedTextField();
		
		id.setBorder(BorderFactory.createCompoundBorder(id.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component
		servico.setBorder(BorderFactory.createCompoundBorder(servico.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Sets padding to the component

		gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(id_label, gridBagConstraints);
        mainPanel_mec.add(id_label);
        gridBagConstraints.insets = new Insets(2,2,30,2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(id, gridBagConstraints);
        mainPanel_mec.add(id);

		gridBagConstraints.insets = new Insets(2,2,2,2);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(servico_label, gridBagConstraints);
        mainPanel_mec.add(servico_label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(servico, gridBagConstraints);
		mainPanel_mec.add(servico);
		
		gridBagConstraints.insets = new Insets(30,2,2,2);
        JButton adicionar_servicos = new JButton("Adicionar");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(adicionar_servicos, gridBagConstraints);
		mainPanel_mec.add(adicionar_servicos);
		
		mainPanel_mec.setLayout(layout);

		mainFrame_mec.add(mainPanel_mec);
		mainFrame_mec.setTitle("Oficina - Adicionar servicos");
		mainFrame_mec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame_mec.setMinimumSize(new Dimension(400,300)); 
		mainFrame_mec.setSize(450,350); 
		mainFrame_mec.setLocationRelativeTo(null);
		mainFrame_mec.setVisible(true);
		
		adicionar_servicos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

				String aux = id.getText();
				int value_id = -1;
				
				try {
					value_id = Integer.parseInt(aux);
				}
				catch(Exception exception) {
					value_id = -1;
				}
				
				String value_servico = servico.getText();

				ServiceOrder so = Driver.executed_services.get(value_id);

				if (so != null){
					
					if (so.isApproved() && !so.isExecuted()){
						if (Driver.available_services.containsKey(value_servico) == true){

							Service temp = new Service();
							temp.setService_name(Driver.available_services.get(value_servico).getService_name());
							temp.setService_price(Driver.available_services.get(value_servico).getService_price());
							so.setCost(so.getCost() + temp.getService_price());
							Driver.executed_services.get(value_id).push_services(value_servico, temp);

							mainFrame_mec.dispose();
							setLayoutDisplay();
		
							JOptionPane.showMessageDialog(null, "Servico adicionado com sucesso!", "Oficina - Adicionar servico", JOptionPane.INFORMATION_MESSAGE);
		
						}
						else{
							JOptionPane.showMessageDialog(null, "Servico nao encontrado", "Oficina - Adicionar servico", JOptionPane.ERROR_MESSAGE);
							mainFrame_mec.dispose();
							setLayoutDisplay();
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Ordem de servico nao encontrada", "Oficina - Adicionar servico", JOptionPane.ERROR_MESSAGE);
					mainFrame_mec.dispose();
					setLayoutDisplay();
				}
			}
		});
	}
	

    /**
     * Creates a new frame responsible for displaying all approved service orders and yet not marked as executed or concluded
     */
	public static void layoutServiceOrder() {

    	JFrame newFrame = new JFrame();
    	JPanel newPanel = new JPanel();
    	
    	newPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    	newFrame.setTitle("Oficina - Ordens de servicos aprovadas");
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
    	for(Map.Entry<Integer, ServiceOrder> entry : Driver.executed_services.entrySet()) {
            if(entry.getValue().isApproved() && !entry.getValue().isExecuted()){
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

}
