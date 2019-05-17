/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  


/**
 *
 * @author Lenovo
 */
public class connect {
     Connection koneksi;
    public Connection getConnection(){
        try{
                koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/emoney_itera","root","");
                
        } catch (SQLException e){
                        System.out.println("Tidak dapat menyambungkan ke database");
                        }
                return koneksi;
    }
    
    private String pin,nama, id, kantin;
    private Date a;
    private BigDecimal balance;
    
     void updateData(Connection Connect, String query){
        try {
            Statement stat = Connect.createStatement();
            ResultSet hasil = stat.executeQuery(query);
            
            while(hasil.next()){
                pin = hasil.getString("pin");
                balance = hasil.getBigDecimal("saldo");
                nama = hasil.getString("nama_depan") + hasil.getString("nama_belakang");
            }
            
        } catch (SQLException ex){
            System.out.println("Gagal update query!");
        }
    }
     
     
     void updateSaldo(String id, String bills, Connection Connect, String query){
         
        try {
            Statement stat = Connect.createStatement();
            ResultSet hasil = stat.executeQuery(query);
            
            while(hasil.next()){
                pin = hasil.getString("pin");
                balance = hasil.getBigDecimal("saldo");
                nama = hasil.getString("nama_depan") + hasil.getString("nama_belakang");
            }
            
        } catch (SQLException ex){
            System.out.println("Gagal update query!");
        }
    
     }
     
        void KaryawanData(Connection Connect, String query){
        try {
            Statement stat = Connect.createStatement();
            ResultSet hasil = stat.executeQuery(query);
            
            while(hasil.next()){
                pin = hasil.getString("password");
                id = hasil.getString("id");
                kantin = hasil.getString("id_kantin");
                nama = hasil.getString("nama");
            }
            
        } catch (SQLException ex){
            System.out.println("Gagal update query!");
        }
    }
    
     void getLastTransaction(Connection Connect, String query){
          try {
            Statement statt = Connect.createStatement();
            ResultSet hasils = statt.executeQuery(query);
              //System.out.println(hasils.getString("tgl_transaksi"));
            while(hasils.next()){
                a = hasils.getDate("max");
//                a = hasils.getDate("tgl_transaksi");
//                System.out.println(a);
            }
            
        } catch (SQLException ex){
            System.out.println("Gagal update query!");
        }
     }
     
     
     public String returnpin(){
         return pin;
     }
     public String returnnama(){
         return nama;
     }
     public String returnkantin(){
         return kantin;
     }
     public BigDecimal returnsaldo(){
         return balance;
     }
     
     public Date returnlast(){
         return a;
     }
}
