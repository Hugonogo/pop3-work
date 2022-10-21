public class Server {
    public String usuario = "aluno";
    public String senha = "123456";
    public String endereco = "pop.mail.com";
    public String porta = "110";

    public int n = 3;
    public String[] lista = new String[n];
    
   
    
    //Comando de login
    public String user(Client pass){

        if (pass.getUser().equals(this.usuario)) {
            return "+OK usário reconhecido";
        }else{
            return "-ERR usuário não reconhecido";
        }
        
    }
    public String pass(Client Pass){
        if (Pass.getPassword().equals(this.senha)) {
            return "Loggin user";
        }else{
            return "Senha errada";
        }
       
    }
    // Mostra o número de emails dp usuário
    public int stat(){
        return n;
    }
    //Recebe como parâmetro o indice do email e a quantidade de linhas que deveraão ser mostradas
    public void top(int n, int x){
        int num = 0;
        this.lista[0] = "Assunto: oi, como vai?\nOi Hugo, bom dia.\nAté Logo.";
        this.lista[1] = "Assunto: bom dia\n Olá, tudo bem.";
        this.lista[2] = "Assunto: Promoção do shopOnline\nOlá, aluno\nA ShopOnline está com ótimas promoções\nvenha conferir!!!";
        n = n - 1;
        String[] array = this.lista[n].split("\n");
        if (x == 0) {
            System.out.println(this.lista[n]); 
        }
        else{
            for (int i = 0; i < array.length; i++) {
                num++;
            }
            if (x > num) {
                System.out.println("-Err excedeu o numero de linhas");
            }
            else if(x > 0){
                System.out.println(array[0]);
                for (int i = 1; i <= x; i++) {
                    System.out.println(array[i]);
                }
            }
        }
       
        
    }

    public void list(){
        this.lista[0] = "Assunto: oi, como vai?\nOi Hugo, bom dia.\nAté Logo.";
        this.lista[1] = "Assunto: bom dia\n Olá, tudo bem.";
        this.lista[2] = "Assunto: Promoção do shopOnline\nOlá, aluno\nA ShopOnline está com ótimas promoções\nvenha conferir!!!";
       
        for (int i = 0; i < this.lista.length; i++) {
            String[] array = this.lista[i].split("\n");
            System.out.println(""+ (i+1) +" "+array[0]);

        }
        System.out.println(".");


    }

    




}
