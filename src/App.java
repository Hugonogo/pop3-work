import java.util.Scanner;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("WELCOME");

        // verificar se dgitou primeiro o user antes do pass
        boolean digitouUser = false;
        boolean logginAutorizado = false;
        boolean session = false;
        boolean pop = false;
        
        try (Scanner input = new Scanner(System.in)) {
            
            Server s = new Server(); // instancia da classe server;
            Client c1 = new Client(); // instancia do cliente
            String comand = ""; // variável para utilizar os comando
            


           s.caixaDeEntrada(1);
           s.list();
            while (true) {
                comand = input.nextLine();
                
                String[] comandSplit = comand.split(" ");
               
                // comando com mais de 1 argumento
                if (comandSplit.length > 1){
                    
                    switch (comandSplit[0]){
                        case "telnet":
                            if (!session) {
                                if (comandSplit[1].equals(s.endereco)) {
                                    if (comandSplit[2].equals(s.porta)) {
                                        pop = true;
                                        System.out.println("<POP3>");
                                    }
                                }
                            }
                            break;
                        case "user":
                            if (pop) {
                                if(!session){
                                    if(!digitouUser){
                                        c1.setUser(comandSplit[1]);
                                        System.out.println(s.user(c1));
                                        if (s.usuario.equals(comandSplit[1]))
                                            digitouUser = true;
                                    }else{
                                        System.out.println("Já existe um login sendo realizado");
                                    }
                                }else
                                    System.out.println("Você já ta logado no sistema");
                            }
                           
                            break;
                        case "pass":
                            if (!session){
                                if(digitouUser){
                                    c1.setPassword(comandSplit[1]);
                                    logginAutorizado = s.pass(c1);
                                    if (logginAutorizado){
                                        System.out.println("Loggin autorizado");
                                        session = true;
                                    }
                                    else
                                        System.out.println("Senha errada");
                                }else{
                                    System.out.println("Insira um usuário primeiro");
                                }
                            }else{
                                System.out.println("Você já ta logado no sistema");
                            }

                            break;
                        case "retr":
                            if (session) {
                                int n = Integer.parseInt(comandSplit[1]);
                                s.retr(n);
                            }
                            break;
                         /*
                         *
                         * adicione o restante dos comandos aqui
                         *
                         * ex:
                         * case "comando":
                         *      # logica
                         *      break;
                         * */
                        default:
                            System.out.println("Command not found");
                    }

                    /* //Verifica o Comando Telnet
                    if (comandSplit[0].equals("telnet") && (comandSplit[1].equals(s.endereco) && (comandSplit[2].equals(s.porta)))) {
                        System.out.println("<pop3 mail.com server>");
                        comand = input.nextLine();
                        // verificar comando user
                        if ("user".equals(comandSplit[0])) {

                            c1.setUser(comandSplit[1]);

                            if (c1.getUser().equals(s.usuario)){
                                System.out.println(s.user(c1));
                                comand = input.next();
                                comandSplit = comand.split(" ");
                            }
                            //verificar o comando pass
                            if ("pass".equals(comandSplit[0])) {
                                c1.setPassword(comandSplit[1]);
                                if (c1.getPassword().equals(s.senha)){
                                    System.out.println(s.pass(c1));
                                    while (true) {
                                        comand = input.nextLine();
                                        if ("list".equals(comandSplit[0])) {
                                            s.list();
                                        }
                                        if ("stat".equals(comandSplit[0])) {
                                            System.out.println(s.stat());
                                        }
                                        if ("top".equals(comandSplit[0])) {
                                            int n = Integer.parseInt(comandSplit[1]);
                                            int x = Integer.parseInt(comandSplit[2]);
                                            s.top(n, x);

                                        }
                                        if ("quit".equals(comand)) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }*/
                }       // comandos com 1 argumento
                else if(comandSplit.length == 1){
                    //verifique o comando quit
                    if ("quit".equals(comand)) {
                        System.out.println("bye!");
                        break;
                    }else if("stat".equals(comand)){
                        if(session){
                            System.out.println(s.stat()); 
                        }
                    }else if ("list". equals(comand)) {
                        if (session) {
                            s.list();
                        }
                    }else if ("telnet".equals(comand) || "user".equals(comand) ||
                            "pass".equals(comand) || "retr".equals(comand)){
                        System.out.println("Command with syntax error, please digit help!");
                    }
                    else{
                        System.out.println("Command not found");
                    }
                }
            }
        }
    }
}
