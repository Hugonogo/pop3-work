package cliente;
import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Email;

public class ParteClient {

    /* Variáveis que vou receber do serve */
    static ObjectInputStream in;
    static List<Email> caixaPostalLocal;
    static boolean isLogado = false; // padrão false até o server dizer que está


    static Socket cliente;

    private static void  initCliente(){

        try {

            cliente = new Socket("0.0.0.0",3322);

            in = new ObjectInputStream(cliente.getInputStream());

            caixaPostalLocal = (ArrayList<Email>) in.readObject();

            //isLogado = in.readBoolean();

        }catch (IOException ex){
            System.out.println(ex);
        }catch( ClassNotFoundException e ){
           System.out.println(e);
        }
    }

    public static void main(String[] args)
    {
        initCliente();
        
       /* Entrada do usuario */
       while(true)
       {

        String entrada = new Scanner(System.in).nextLine().toString();
        if(true && entrada.equals("list"))
        {
            for(Email e : caixaPostalLocal)
                {
                    System.out.println(e.toString());
                }
       }
        
        try 
          {
              PrintStream saida = new PrintStream(cliente.getOutputStream());
              saida.println(entrada);
          } 
          catch (IOException ex) 
          {
              System.out.println(ex);
          }
       }
    
    }
    
}