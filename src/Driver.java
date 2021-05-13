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

/*
 * PS: Este programa foi feito como um trabalho para o curso de Programação Orientada a Objetos.
 * 
 * Esta entrega possui uma interface gráfica feita em Java, e apesar de simular um ambiente real de gerenciamento
 * de uma oficina, algumas verificações que devem ser feitas em um software real
 * não foram feitas, como por exemplo verificação de e-mail, CPF e de redefinição de senhas.
 * 
 * 
 * Objetivos principais do projeto:
 *  - Criar um sistema de Login (para administrador, vendedores e mecânicos) e Logout;
 *  - Permitir ao administrador editar dados dos vendedores e dos mecânicos;
 *  - Permitir aos vendedores cadastrar clientes, veiculos e gerar ordens de serviços;
 *  - Permitir ao mecânico estabelecer um ordem de serviço aprovada como executada, e cadastrar serviços executados e peças utilizadas.
 * 
 * 
 * O UML relativo ao projeto se encontra no PDF 'POO - TP2.pdf' 
 * 
 * 
 * Alunos: 
 * - Vinicius Gabriel Angelozzi Verona de Resende | 19.1.4005;
 * - Carlos Eduardo Gonzaga Romaniello de Souza   | 19.1.4003.
 * 
*/
public class Driver{
	
    // System controller variables
	public static Admin adm;
    public static Map<String, Mechanic> mechanics = new HashMap<>();
    public static Map<String, Vendor> vendors = new HashMap<>();
    public static Map<String, Client> clients = new HashMap<>();
    public static Map<Integer, ServiceOrder> pending_services = new HashMap<>();
    public static Map<Integer, ServiceOrder> concluded_services = new HashMap<>();
    public static Map<Integer, ServiceOrder> executed_services = new HashMap<>();
    public static Map<String, Service> available_services = new HashMap<>();
    public static Map<String, AutoPart> available_automobile_parts = new HashMap<>();

    public static void main(String[] args){
        Driver aux = new Driver();
        aux.oficina();
    }

    public void oficina(){
    	
    	// Creates an administrator to be used in the system
        adm = new Admin();
        adm.setName("Pedro");
        adm.setCpf("12534347999");
        adm.setEmail("pedro_silva@hotmail.com");
        adm.setPsswd("tp02-poo");
       
        // Creates mechanics and vendors to be used in the system
        mechanics.put("12345678900", new Mechanic(2, "Joao", "12345678900", "joao@hotmail.com", "abc123"));
        mechanics.put("12345678911", new Mechanic(2, "Carlos", "12345678911", "carlos@hotmail.com", "abc123"));
        vendors.put("12345678922", new Vendor(3, "Paulo", "12345678922", "paulo@hotmail.com", "abc123"));
        vendors.put("12345678933", new Vendor(3, "Tiago", "12345678933", "Tiago@hotmail.com", "abc123"));

        // Creates available services to be used in the system
        available_services.put("Calibragem", new Service("Calibragem", 100.00));
        available_services.put("Troca de Oleo", new Service("Troca de Oleo", 150.00));
        available_services.put("Troca de Pneu", new Service("Troca de Pneu", 75.99));
        available_services.put("Limpeza", new Service("Limpeza", 50.00));
        available_services.put("Balanceamento", new Service("Balanceamento", 94.99));
        available_services.put("Reparacao", new Service("Reparacao", 210.00));
        
        // Creates available automobile parts to be used in the system
        available_automobile_parts.put("Parafuso", new AutoPart("Parafuso", 0, 0.5));
        available_automobile_parts.put("Pneu", new AutoPart("Pneu", 0, 100.00));
        available_automobile_parts.put("Parabrisa", new AutoPart("Parabrisa", 0, 314.99));
        available_automobile_parts.put("Banco", new AutoPart("Banco", 0, 450.00));
        available_automobile_parts.put("Caixa de Câmbio", new AutoPart("Caixa de Câmbio", 0, 804.75));
        available_automobile_parts.put("Rolamento", new AutoPart("Rolamento", 0, 20.00));
        
        Login login = new Login();
    }

    // Push & pop mechanics
    public static void pushMechanics(String key, Mechanic mechanic){

        boolean aux = mechanics.containsKey(key);

        if (aux == false){
            mechanics.put(key, mechanic);
        }

    }
    public static void popMechanics(String key){

        boolean aux = mechanics.containsKey(key);

        if (aux == true){
            mechanics.remove(key);
        }

    }

    // Push & pop vendors
    public static void pushVendors(String key, Vendor vendor){

        boolean aux = vendors.containsKey(key);

        if (aux == false){
            vendors.put(key, vendor);
        }

    }
    public static void popVendors(String key){
        
        boolean aux = vendors.containsKey(key);

        if (aux == true){
            vendors.remove(key);
        }

    }

    // Push & pop clients
    public static void pushClients(String key, Client client){

        boolean aux = clients.containsKey(key);

        if (aux == false){
            clients.put(key, client);
        }

    }
    public static void popClients(String key){
        
        boolean aux = clients.containsKey(key);

        if (aux == true){
            clients.remove(key);
        }

    }

    // Push & pop pending_services
    public static void pushPendingServices(Integer key, ServiceOrder pending){

        boolean aux = pending_services.containsKey(key);

        if (aux == false){
            pending_services.put(key, pending);
        }

    }
    public static void popPendingServices(Integer key){
        
        boolean aux = pending_services.containsKey(key);

        if (aux == true){
            pending_services.remove(key);
        }

    }

    // Push & pop concluded_services
    public static void pushConcludedServices(Integer key, ServiceOrder concluded){

        boolean aux = concluded_services.containsKey(key);

        if (aux == false){
            concluded_services.put(key, concluded);
        }

    }
    public static void popConcludedServices(Integer key){
        
        boolean aux = concluded_services.containsKey(key);

        if (aux == true){
           concluded_services.remove(key);
        }

    }

    // Push & pop executed_services
    public static void pushExecutedServices(Integer key, ServiceOrder executed){

        boolean aux = executed_services.containsKey(key);

        if (aux == false){
            executed_services.put(key, executed);
        }

    }
    public static void popExecutedServices(Integer key){
        
        boolean aux = executed_services.containsKey(key);

        if (aux == true){
           executed_services.remove(key);
        }

    }

    // Push & pop available_services
    public static void pushServices(String key, Service service){

        boolean aux = available_services.containsKey(key);

        if (aux == false){
            available_services.put(key, service);
        }

    }
    public static void popServices(String key){
        
        boolean aux = available_services.containsKey(key);

        if (aux == true){
           available_services.remove(key);
        }

    }

    // Push & pop available_automobile_parts
    public static void pushAutomobileParts(String key, AutoPart auto_part){

        boolean aux = available_automobile_parts.containsKey(key);

        if (aux == false){
            available_automobile_parts.put(key, auto_part);
        }

    }
    public static void popAutomobileParts(String key){
        
        boolean aux = available_automobile_parts.containsKey(key);

        if (aux == true){
           available_automobile_parts.remove(key);
        }

    }

}