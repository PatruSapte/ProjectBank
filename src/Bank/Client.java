/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bank;

import java.util.*;
import java.io.*;
import javax.swing.DefaultListModel;
/**
 *
 * @author Mooncake
 */
public class Client implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
  
    String username, password, cnp;
    float sumEuro, sumRon;
    boolean isTracked;
    DefaultListModel<String> trackingData;
    public Client(String u, String p, String c,float se,float sr)
    {
        username = u;
        password = p;
        cnp = c;
        sumEuro = se;
        sumRon = sr;
        isTracked = false;
        trackingData = new DefaultListModel<String>();
    }
   
    
    public static List<Client> clientList = new ArrayList<Client>();
    public static Client connectedClient;
    public static void ReadClients()
    {
        try {
            FileInputStream fis = new FileInputStream("ClientList.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            clientList = (List<Client>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void AddNewClient(Client c){
  
       for(Client it : clientList)
       {
           if(it.username.equals(c.username))
           {
               Register.infoBox("Numele de utilizator este deja utilizat", "A aparut o problema la crearea contului");
               return;
           }
       }
        
        for(Client it : clientList)
       {
           if(it.cnp.equals(c.cnp))
           {
               Register.infoBox("CNP-ul este deja utilizat", "A aparut o problema la crearea contului");
               return;
           }
       }
         
        clientList.add(c);
        SaveList(); 
        Register.infoBox("Contul a fost creat", "Succes!");

       
    }
   
   public static void updateClient(Client c)
   {
       int i=0;

       while(i < clientList.size())
       {
           if(clientList.get(i).username.equals( c.username))
           {
               clientList.set(i, c);
               SaveList();
               return;
           }
           i++;
       }
       
   }
   public static void RemoveConnectedClient()
   {
       int i=0;

       while(i < clientList.size())
       {
           if(clientList.get(i).username.equals( connectedClient.username))
           {
               clientList.remove(i);
               SaveList();
               return;
           }
           i++;
       }
   }
   static void SaveList(){
         try {
            FileOutputStream fos = new FileOutputStream("ClientList.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(clientList);
            oos.close(); 
        } 
        catch(Exception ex) {
          ex.printStackTrace();
    }
    }
  
}
