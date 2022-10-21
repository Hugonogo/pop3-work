import java.util.Scanner;
public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            Server s = new Server(); // instancia da classe server
            Client c1 = new Client(); // instancia do cliente
            String log = ""; // variável para utilizar os comando
           

           
            while (true) {

                log = input.nextLine();
                String[] array = log.split(" ");
                //Verifica o Comando Telnet
                if (array[0].equals("telnet") & (array[1].equals(s.endereco)& (array[2].equals(s.porta)))) {
                    System.out.println("<pop3 mail.com server>");
                    log = input.nextLine();
                    // verificar comando user
                    if ("user".equals(array[0])) {
                    
                        c1.setUser(array[1]);
                        
                        if (c1.getUser().equals(s.usuario)){
                            System.out.println(s.user(c1));
                            log = input.next();
                            array = log.split(" ");
                            
                        }
                        //verificar o comando pass
                        if ("pass".equals(array[0])) {
                            c1.setPassword(array[1]);
                            if (c1.getPassword().equals(s.senha)){
                                System.out.println(s.pass(c1));
                                while (true) {
                                    log = input.nextLine();
                                    if ("list".equals(array[0])) {
                                        s.list();
                                    }
                                    if ("stat".equals(array[0])) {
                                        System.out.println(s.stat());
                                    }
                                    if ("top".equals(array[0])) {
                                        int n = Integer.parseInt(array[1]);
                                        int x = Integer.parseInt(array[2]);
                                        s.top(n, x);  
                        
                                    }     
                                    if ("quit".equals(log)) {
                                        break;
                                    }
                                }
                            
                                
                            }
                        } 

                    
                    }
                }
                //verifique o comando quit
                if ("quit".equals(log)) {
                    break;
                }
            }
        }
    }
}
