import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            Server s = new Server(); // instancia da classe server
            Client c1 = new Client(); // instancia do cliente
            String log = null; // variável para utilizar os comandos
            int n = 0;
            while (true) {
                log = input.nextLine();
                // verificar comando user
                if ("user".equals(log)) {
                    
                    log = input.next();
                    c1.setUser(log);
                    
                    if (c1.getUser().equals(s.usuario)){
                        System.out.println(s.user(c1));
                        log = input.next();
                    }
                    //verificar o comando pass
                    if ("pass".equals(log)) {
                        log = input.next();
                        c1.setPassword(log);
                        if (c1.getPassword().equals(s.senha)){
                            System.out.println(s.pass(c1));
                            while (true) {
                                log = input.nextLine();
                                if ("stat".equals(log)) {
                                    System.out.println(s.stat());
                                }
                                if ("top".equals(log)) {
                                    n = input.nextInt();
                                    System.out.println(s.top(n));  
                    
                                }     
                                if ("quit".equals(log)) {
                                    break;
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
