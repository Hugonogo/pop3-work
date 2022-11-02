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
    static DataInputStream inmsg;
    static List<Email> caixaPostalLocal;
    static boolean isLogado = false; // padrão false até o server dizer que está


    static Socket cliente;

    private static void  initCliente(){

        try {

            cliente = new Socket("0.0.0.0", 110);
            inmsg = new DataInputStream(cliente.getInputStream());
            if (inmsg.readBoolean() == true) {
                System.out.println("Flamengo");
            }


            //isLogado = in.readBoolean();

        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        initCliente();
        
       /* Entrada do usuario */
       while(true)
       {

        String entrada = new Scanner(System.in).nextLine().toString();
        if(true && entrada.equals("list"))
        {
            int cont = 1;
            for(Email e : caixaPostalLocal)
                {
                    System.out.println(cont+" "+e.tamanho);
                    cont++;
                }
            System.out.println(".");
        }
        if (true && entrada.equals("xxx")) {
            in = new ObjectInputStream(cliente.getInputStream());

            caixaPostalLocal = (ArrayList<Email>) in.readObject();
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
