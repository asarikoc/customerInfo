
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdul
 */
public class DoublyLinkedList {
    
    // inner Node class
    private class Node{
    private CustomerInfo customerData;
    private Node previous;
    private Node next;
    
    public Node(CustomerInfo customerData){
        this(null,customerData,null);
    }
    
    public Node(Node previous, CustomerInfo customerData, Node next){
        this.previous = previous;
        this.customerData = customerData;
        this.next = next;        
    }
    }
    
    Node head;
    Node tail;
    
    public DoublyLinkedList() {
        head = tail = null;
    }

    // inner Iterator class
    public class Iterator{
        private Node position;
        
        public Iterator(){
            position = head;
        }
        
        public void restart() { 
            position = head;
        }
        
        public CustomerInfo next(){
            if (!hasNext( ))
                throw new NoSuchElementException( );
            CustomerInfo customer = position.customerData;
            position = position.next;
            return customer;
        }
        
        public boolean hasNext(){
            return (position != null);
        }
        
        public void insertInOrder(CustomerInfo customer){
            String soyad = soyadAl(customer.getAdSoyad());
            if (isEmpty())
                head = tail = new Node(customer);
            else if (soyadAl(head.customerData.getAdSoyad()).compareToIgnoreCase(soyad)>0)
                DoublyLinkedList.this.addToStart(customer);
            else if (soyadAl(tail.customerData.getAdSoyad()).compareToIgnoreCase(soyad)<=0)
                tail = tail.next = new Node(tail,customer,null);
            else{
                Node positionNode = head.next;
                Node previousNode = head;
            // find insert position
            while(positionNode != null && (soyadAl(positionNode.customerData.getAdSoyad()).compareToIgnoreCase(soyad)<=0)){
                previousNode = positionNode;
                positionNode = positionNode.next;
            }
            Node temp = new Node(positionNode.previous,customer,positionNode);
            positionNode.previous.next = temp;
            positionNode.previous = temp;
            }
    } // end of insert in order
        
        public String containsCustomer(String adSoyad){
            Node position = head;
            String nameAtPosition;
            while (position != null ) {
                nameAtPosition = position.customerData.getAdSoyad();
                if (nameAtPosition.equalsIgnoreCase(adSoyad))
                    return position.customerData.toString(); 
                position = position.next;
            }
            return "Aradığınız ad soyada ait bir müşretimiz bulunmamaktadır.";
        }// end of contains
        
        public void deleteSpecificCustomer(String adSoyad) {
            Node position = head;
            String nameAtPosition;
            while (position != null ) {
                nameAtPosition = position.customerData.getAdSoyad();
                if (nameAtPosition.equalsIgnoreCase(adSoyad)){
                    if (position == head) {
                        position.next.previous = null;
                    } else if (position.next == null) {
                        position.previous.next = null;
                    }
                    else{
                        position.previous.next = position.next;
                        position.next.previous = position.previous;
                    }
                }
                position = position.next;
            }
        }
        
        public void printAlphabetical(){
            Node position = head;
            while ( position!=null){
                System.out.println(position.customerData.toString() + " ");
                position = position.next;
            }
            System.out.println();
        }
        
        public void printNonAlphabetical(){
            Node position = tail;
            while ( position != null){
                System.out.println(position.customerData.toString() + " ");
                position = position.previous;
            }
            System.out.println();
        }        
    }// end of Iterator inner class
    
    public String soyadAl(String Adsoyad){
        return (Adsoyad.substring(Adsoyad.lastIndexOf(" ")+1));
    }
    
    public void addToStart(CustomerInfo customer) {
        Node newHead = new Node(null,customer,head);
        if(head != null) {
            head.previous = newHead;
    }
        head = newHead;
    }
        
    public Iterator iterator(){
        return new Iterator();
    }
    
    public boolean isEmpty() {
        return head == null;
    }

    }

