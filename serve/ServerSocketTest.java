package serve;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Email;

 
public class ServerSocketTest {

    /* Variável de saída do serve */
    static ObjectOutputStream out;

    /* Dados estáticos simulando um banco do serve*/
    static boolean isLogado = false;
    static boolean inseriuNome = false;
    static String nome = "aluno";
    static String senha = "123@45";
    static ArrayList<Email> caixaPostal = new ArrayList<>();

    /* add emails fakes */
    public static void loadEmailsForServe(){
        caixaPostal.add(new Email(
            "primeiro",
            "primeiro email",
            "hugo",
            "gustavo"
        ));

        caixaPostal.add(new Email(
            "segundo emails",
            "ual esse é um segundo email",
            "hugo",
            "gustavo"
        ));

        caixaPostal.add(new Email(
            "terceiro email",
            "ta recebendo meus e-mails hugo?",
            "hugo",
            "gustavo"
        ));

    }

    public static void main(String args[]){

        loadEmailsForServe(); // Simula o carregamento dos e-mails para o serve

        try {

            ServerSocket server = new ServerSocket(3322);                       
            System.out.println("Servidor iniciado na porta 3322");

            
            Socket cliente = server.accept();
            System.out.println("Cliente conectado do IP "+cliente.getInetAddress().
                    getHostAddress());
            
            
            out = new ObjectOutputStream(cliente.getOutputStream());
            out.writeObject(caixaPostal);
            //out.writeBoolean(isLogado);
            
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
                                default:
                                    System.out.println("Serve: Comando desconhecido");
                            }
                    }else 
                    {   
                        if (command[0].equals("list"))
                        {   
                            if(isLogado)
                            {   
                                caixaPostal.clear(); // limpar do serve
                            }else
                            {
                                System.out.println("Serve: Comando precisa de um usuário logado");
                            }
                        }else
                        {
                            System.out.println("Serve: Comando inválido!");
                        }
                        
                    }
            }
            
            entrada.close();
            server.close();
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
}