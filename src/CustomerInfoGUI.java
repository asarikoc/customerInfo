
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdul
 */
class CustomerInfoGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    
    // customer.txt!!!!!!!!!
    private JTextArea sourceText;
    private JTextArea displayedText;
    
    // constructor creates window and GUI widgets.
    public CustomerInfoGUI() {
        super();
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("Customer Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        sourceText = new JTextArea(10,30);
        add(sourceText);
        
        displayedText = new JTextArea(10,30);
        add(displayedText);
        
        JButton addBtn = new JButton("Add Customer");
        JButton deleteBtn = new JButton("Delete Customer");
        JButton outputCustomerBtn = new JButton("Show Customer");
        JButton sortPrintBtn = new JButton("Show Customers Alphabetically");
        JButton sortReverseBtn = new JButton("Show Customers reverse-Alphabetically");
    
        addBtn.addActionListener(this);
        add(addBtn);
    
        deleteBtn.addActionListener(this);
        add(deleteBtn);
        
        outputCustomerBtn.addActionListener(this);
        add(outputCustomerBtn);
        
        sortPrintBtn.addActionListener(this);
        add(sortPrintBtn);
        
        sortReverseBtn.addActionListener(this);
        add(sortReverseBtn);
    }
    
    public void actionPerformed(ActionEvent e) {
        String adSoyad = null;
        String adres = null;
        DoublyLinkedList list = new DoublyLinkedList();
        DoublyLinkedList.Iterator i = list.iterator();
        FileWriter fr = null;
        try {
            File file = new File("customer.txt");
            Scanner sc = new Scanner(file);
            fr = new FileWriter(file,true);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split(",");
                adSoyad = words[0];
                adres = words[1];
                CustomerInfo customer = new CustomerInfo(adSoyad, adres);
                // Takes customers phone number from file
                for (int ind = 2; ind < words.length; ind++) {
                    customer.addPhoneNo(words[ind]);
                }
                i.insertInOrder(customer);
            }
        } catch (FileNotFoundException a) {
            System.out.println("Dosyada bulunmamaktadır.");
        } catch (IOException ex) { 
            Logger.getLogger(CustomerInfoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String buttonString = e.getActionCommand();
        
        // Add Customer button
        if (buttonString.equals("Add Customer")) {
            String src = sourceText.getText();
            Scanner scan = new Scanner(src);
            String line = scan.nextLine();
            String[] words = line.split(",");
            adSoyad = words[0];
            adres = words[1];
            CustomerInfo customer = new CustomerInfo(adSoyad, adres);
            for (int ind = 2; ind < words.length; ind++) {
                    customer.addPhoneNo(words[ind]);
            }try {
                fr.write("\n"+adSoyad+","+adres+","+customer.phoneNumbers.toString());
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(CustomerInfoGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            i.insertInOrder(customer);
        }
        // Delete Customer button
        else if(buttonString.equals("Delete Customer")){
            String src = sourceText.getText();
            Scanner scan = new Scanner(src);
            String nameSurname = scan.nextLine();
            i.restart();
            i.deleteSpecificCustomer(nameSurname);
            
        }
        // Show Customer button
        else if(buttonString.equals("Show Customer")){
            String src = sourceText.getText();
            Scanner scan = new Scanner(src);
            String nameSurname = scan.nextLine();
            System.out.println(i.containsCustomer(nameSurname));
        }
        // Show Customer Alphabetically button
        else if(buttonString.equals("Show Customers Alphabetically")){
            i.restart();
            i.printAlphabetical();
        }
        // Show Customer Reverse Alphabetically button
        else{
            i.restart();
            i.printNonAlphabetical();
        }
    }
    
    public static void main(String[] args) {
        CustomerInfoGUI piggy = new CustomerInfoGUI();
        piggy.setVisible(true);
    }
} //CustomerInfoGUI
