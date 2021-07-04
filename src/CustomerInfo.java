
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author abdul
 */
public class CustomerInfo {

    private String adSoyad;
    private String adres;
    ArrayList<String> phoneNumbers = new ArrayList<String>();

    public CustomerInfo() {
        adSoyad = null;
        adres = null;
    }

    public CustomerInfo(String adSoyad, String adres) {
        this.adSoyad = adSoyad;
        this.adres = adres;
        
    }

    public void addPhoneNo(String phoneNo) {
        phoneNumbers.add(phoneNo);
    }

    /**
     * @return the adSoyad
     */
    public String getAdSoyad() {
        return adSoyad;
    }

    /**
     * @param adSoyad the adSoyad to set
     */
    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    /**
     * @return the adres
     */
    public String getAdres() {
        return adres;
    }

    /**
     * @param adres the adres to set
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }
    
    public String toString() {
        return("Müşterinin adı ve soyadı: "+adSoyad+
               "\nMüşterinin adresi: "+adres+
                "\nMüşterinin telefon numaraları: "+phoneNumbers+"\n");
    }
    
}
