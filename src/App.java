import java.util.Scanner;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("BEM-VINDO");

        // verificar se dgitou primeiro o user antes do pass
        boolean digitouUser = false;
        boolean logginAutorizado = false;
        boolean session = false;
        boolean pop = false;
        String MSG_LOGIN = "Faça login antes de tentar acessar esse comando";
        
        try (Scanner input = new Scanner(System.in)) {
            
            Server s = new Server(); // instancia da classe server;
            Client c1 = new Client(); // instancia do cliente
            String comand = ""; // variável para utilizar os comando
            


            s.carregarEmails();
            while (true) {
                comand = input.nextLine();
                
                String[] comandSplit = comand.split(" ");
               
                // comando com mais de 1 argumento
                if (comandSplit.length > 1){
                    
                    switch (comandSplit[0]){
                        case "telnet":
                            if (!session) {
                                if(comandSplit.length == 3){
                                    if (comandSplit[1].equals(s.endereco)) {
                                        if (comandSplit[2].equals(s.porta)) {
                                            pop = true;
                                            System.out.println("<POP3> conectado");
                                        }else{
                                            System.out.println("Porta desconhecida pelo sistema");
                                        }
                                    }else {
                                        System.out.println("Endereço desconhecido pelo sistema");
                                    }
                                }else{
                                    System.out.println("Comando 'telnet' precisa ser passado" +
                                            " 2 argumentos, digite 'help' para ajuda");
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
                            }else{
                                System.out.println("Primeiro você precisa testar a conectividade" +
                                        " com uma porta de serviço com o comando 'telnet', digite" +
                                        " 'help' para mais informações");
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
                                try{
                                    int n = Integer.parseInt(comandSplit[1]);
                                    s.retr(n);
                                }
                                catch(Exception e){
                                    System.out.println("Recebeu um argumento inválido, digite " +
                                            "'help' para ajuda");
                                }
                            }else{
                                System.out.println(MSG_LOGIN);
                            }
                            break;
                         /*
                         *
                         * adicione o restante dos comandos aqui
                         *
                         * exemplo:
                         * case "comando":
                         *      # logica
                         *      break;
                         * */
                        default:
                            System.out.println("Comando não encontrado");
                    }
                }       // comandos com 1 argumento
                else if(comandSplit.length == 1){
                    //verifique o comando quit
                    if ("quit".equals(comand)) {
                        System.out.println("bye!");
                        break;
                    }else if("stat".equals(comand)){
                        if(session){
                            System.out.println(s.stat()); 
                        }else{
                            System.out.println(MSG_LOGIN);
                        }
                    }else if ("list". equals(comand)) {
                        if (session) {
                            s.list();
                        }else{
                            System.out.println(MSG_LOGIN);
                        }
                    }else if ("telnet".equals(comand) || "user".equals(comand) ||
                            "pass".equals(comand) || "retr".equals(comand)){
                        System.out.println("Comando '"+comand+"' com erro de sintaxe, digite 'help' para ajuda");
                    }else if("help".equals(comand)){
                        /*
                        * um dos comandos mais importantes para a nossa aplicação pesso delicadesa
                        * na hora de inseir novas informações, se possível, sempre revisar elas
                        * */
                        System.out.println(
                        "comando | argumentos | descrição\n"+
                        "telnet <serviço> <porta> -O comando telnet testa a conectividade" +
                                " com uma porta de serviço. Através disso, pode ser" +
                                " identificado se há algum bloqueio de rede na porta especificada.\n"+
                        "user <user> -O comando user inicia o login do usuário requerindo o nome dele," +
                                "o comando telnet é obrigatório antes.\n"+
                        "pass <pass> -O comando pass recebe a senha do usuário informado " +
                                "anteriormente pelo comando user.\n"+
                        "list -mostra o indice dos emails da caixa postal e o seu tamanho,"+
                                "o Loggin é obrigatório.\n"+
                        "stat -mostra a quantidade de emails na caixa postal, "+
                                "o loggin é obrigatório.\n"+
                        "retr <IndiceEmail> -retorna o email inteiro do indice."
                   

                        );
                    }
                    else{
                        System.out.println("Comando não encontrado");
                    }
                }
            }
        }
    }
}
