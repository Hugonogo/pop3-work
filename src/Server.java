import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Email;

 
public class Server {
/* Variável de saída do serve */
     static ObjectOutputStream out;
     static DataOutputStream saidamsg;

     /* Dados estáticos simulando um banco do serve*/
     static boolean isLogado = false;
     static boolean inseriuNome = false;
     static String nome = "aluno";
     static String senha = "123@45";
     static ArrayList<Email> caixaPostal = new ArrayList<>();
 
     /* add emails fakes */
     public static ArrayList loadEmailsForServe(){
         caixaPostal.add(new Email(
             "primeiro",
             "primeiro email",
             "hugo",
             "gustavo",
             "1234"
         ));
 
         caixaPostal.add(new Email(
             "segundo emails",
             "ual esse é um segundo email",
             "hugo",
             "gustavo",
             "2000"
         ));
 
         caixaPostal.add(new Email(
             "terceiro email",
             "ta recebendo meus e-mails hugo?",
             "hugo",
             "gustavo",
             "1000"
         ));
    return caixaPostal;
     }
 
     public static void main(String args[]){
 
         try {
 
             ServerSocket server = new ServerSocket(1100);                       
             System.out.println("Servidor iniciado na porta 1100");
 
             
             Socket cliente = server.accept();
             System.out.println("Cliente conectado do IP "+cliente.getInetAddress().getHostAddress());
             
             out = new ObjectOutputStream(cliente.getOutputStream());
             out.writeObject(loadEmailsForServe()); /* Simula o carregamento dos e-mails para o serve */
             caixaPostal.clear(); /* limpar do serve */

             saidamsg = new DataOutputStream(cliente.getOutputStream());
             saidamsg.writeBoolean(true);
             
             Scanner entrada = new Scanner(cliente.getInputStream());
             while(entrada.hasNextLine()){
 
                 String[] command = entrada.nextLine().split(" ");
                 
                 if( command.length > 1)
                     {
                         switch(command[0])
                             {
                                 case "user":
                                     if(command[1].equals(nome))
                                     {
                                         inseriuNome = true;
                                         System.out.println("Serve: User ok");
                                     }else
                                     {
                                         System.out.println("Serve: User incorreto");
                                     }
                                 break;
 
                                 case "pass":
                                     if(inseriuNome)
                                     {
                                         if(command[1].equals(senha))
                                         {
                                             System.out.println("Serve: Senha ok");
                                             System.out.println("Serve: Logado com sucesso");
                                             isLogado = true;
                                         }else
                                         {
                                             System.out.println("Serve: Senha incorreta");
                                         }
                                     }else
                                     {
                                         System.out.println("Serve: Insira user primeiro");
                                     }
                                 break;
                                 case "retr":
                                     if (isLogado) {
                                        System.out.println("Serve: Comando retr...");
                                     }else  System.out.println("Serve: Login não autorizado para o comando retr...");
                                 break;
                                 default:
                                 System.out.println("Serve: Comando desconhecido");
                             }
                     }
                     else
                     {
                        List<String> comandosConhecidos = new ArrayList<>();
                        comandosConhecidos.add("user");
                        comandosConhecidos.add("pass");
                        

                        if ( command[0].equals("list") ) 
                        {
                            if ( isLogado )
                            System.out.println("Serve: Comando list...");
                            else
                            System.out.println("Serve: Login não autorizado para o comando list...");
                        }
                        else if(command[0].equals("quit")){
                            System.out.println("Serve: comando quit...");
                            break;
                            
                        }
                        else if ( comandosConhecidos.contains(command[0]) )
                            System.out.println("Serve: Comando escrito de forma incorreta...");
                        else
                            System.out.println("Serve: Comando inválido");
                        
                     }
                                           
            }
             
             entrada.close();
             server.close();
             
         } catch (IOException ex) {
             System.out.println(ex);
         }
         
     }
}
