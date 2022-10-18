public class Server {
    public String usuario = "aluno";
    public String senha = "123456";
    public int n = 2;
    public String[] lista = new String[n];
    public void enter(String cmm1){

    }
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

    public int stat(){
        return n;
    }

    public String top(int n){
        this.lista[0] = "Assunto: oi, como vai?\nOi Hugo, bom dia.\nAté Logo.";
        this.lista[1] = "Assunto: bom dia\n Olá, tudo bem.";
        n = n - 1;
        
        return this.lista[n];
    }

    




}
