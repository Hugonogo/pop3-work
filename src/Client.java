package cliente;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {

    /* Variáveis que vou receber do serve */
    static ObjectInputStream in;
    static DataInputStream inmsg;
    static List<Email> caixaPostalLocal;
    static boolean isLogado = false; // padrão false até o server dizer que está 
    static boolean session = false;
    static boolean isUser = false;
    static boolean isQuit = false;
 
    static Socket cliente;
 
    private static void  initCliente(){
 
        try {
            cliente = new Socket("0.0.0.0", 1100);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }
 
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        
        Scanner firstCommand = new Scanner(System.in);

        while(!isQuit)
        {
            /* Comando telnet para fazer a conexão com o servidor  */
            String[] commandf = firstCommand.nextLine().split(" ");

            if( commandf.length > 2)
            {   
                if (commandf[0].equals("telnet"))
                {   /* valida o host corretamente */
                    if(commandf[1].equals("0.0.0.0"))
                    {   /* valida a porta correta */
                        if (commandf[2].equals("1100"))
                        {   /* Se a porta e o host estiverem corretos inicia a aplicação do cliente */
                            System.out.println("\n\n+OK pronto\n\n");

                            initCliente();
                            System.out.println("Bem-vindo\nFaça Login Para acessar os emails!\nPrimeiro insira o user e em seguida sua sennha!");
                        /*Pega a caixa postal do cliente antes de logar mas não exibe */

                        in = new ObjectInputStream(cliente.getInputStream());
                        caixaPostalLocal = (ArrayList<Email>) in.readObject();
                        
                        /* Entrada do usuario */
                        while(true)
                        {

                            String entrada = new Scanner(System.in).nextLine().toString();
                            String[] command = entrada.split(" ");
                            if (!session) {
                            if (!isLogado) {
                                if (isUser) {
                                    if (command[0].equals("pass") && command[1].equals("123@45")) 
                                    {
                                        inmsg = new DataInputStream(cliente.getInputStream());
                                            if (inmsg.readBoolean() == true) 
                                            {
                                                isLogado = true;
                                                System.out.println("Logado com sucesso");
                                                session  = true;
                                            }
                                    }else System.out.println("ERRO");
                                }
                            }
                            }
                            if (!session) {
                                if (!isLogado) {
                                if (!isUser) {
                                    if (command[0].equals("user") && command[1].equals("aluno")) {
                                        System.out.println("user Ok");
                                        isUser = true;
                                    }else System.out.println("ERRO");
                                }
                                    
                                }
                    
                            }
                            
                            if (command[0].equals("quit")) {
                            cliente.close();
                            isQuit = true;
                            System.out.println("Bye");
                            break;
                            }
                            
                            if(isLogado && entrada.equals("list"))
                            {

                                int cont = 1;
                                for(Email e : caixaPostalLocal)
                                    {
                                        System.out.println(cont+" "+e.getTamanho());
                                        cont++;
                                    }
                                System.out.println(".");
                            }
                            if (isLogado && command[0].equals("retr") && command.length > 1) {
                                try
                                {
                                    int n = Integer.parseInt(command[1]);
                                    n--;
                                    if (n < caixaPostalLocal.size()) {
                                        System.out.println(caixaPostalLocal.get(n).toString());    

                                    }else{
                                        n++;
                                        System.out.println("Email "+n+" não existe");
                                    }
                                }catch(Exception e)
                                {
                                    System.out.println("Argumento inválido!");
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
                        else
                        {
                            System.out.println("Porta inválida!");
                        } 
                    }
                    else
                    {
                        System.out.println("Host inválido!");
                    }
                }
                else
                {
                    System.out.println("Comando inválido ou indisponível no momento!");
                }
            }
            else
            {
                System.out.println("Inicie com o comando telnet corretamente!");
            }
        }
    
    }
}
