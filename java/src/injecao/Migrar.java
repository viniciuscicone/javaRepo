package injecao;

import java.util.List;


public class Migrar {

    public static void main(String[] args) {

        new ServicoMigracaoUsuario(new ClssLerFonteA(),new ClssEscreverBanco()).migrarBanco();
        new ServicoMigracaoUsuario(new ClssLerFonteA(),new ClssEscreverBanco()).migrarAWS();
    }
}


class ServicoMigracaoUsuario {

    interPonteLer<Usuario> ler;
    ponteEscrever<Usuario> escrever;

    public ServicoMigracaoUsuario(interPonteLer<Usuario> ler,ponteEscrever<Usuario> escrever) {
        this.ler = ler;
        this.escrever = escrever;
    }
    public ServicoMigracaoUsuario() {

    }


    void migrarBanco() {
         List<Usuario> user = ler.lerArquivo();
            escrever.escreverBanco(user);

    }

    void migrarAWS() {
        List<Usuario> user = ler.lerBancoDados();
        escrever.escreverNuvemAWS(user);
    }

}




record Usuario(String username, String email) {

}

interface interPonteLer<T> {
    List<T> lerBancoDados();
    List<T> lerArquivo();
}

class ClssLerFonteA implements interPonteLer<Usuario> {
    public List<Usuario> lerBancoDados(){

        Usuario user = new Usuario("vinicius", "emailbom");
        System.out.println("Lendo usuario de um banco de dados" + user.email() + "__ nome" + user.username());
        return List.of(new Usuario("vinicius", "emailbom"));
    }

    public List<Usuario> lerArquivo(){

        Usuario user = new Usuario("vinicius", "emailbom");
        System.out.println("Lendo usuario de um aquivo" + user.email() + "__ nome" + user.username());
        return List.of(new Usuario("jonas", "emailaquigoogle"));
    }
}



class ClssEscreverBanco implements ponteEscrever<Usuario> {
    public void escreverBanco(List<Usuario> Usuario){
        System.out.println("Escrevendo usuario no banco de dados");
        System.out.println(Usuario);
    };

    public void escreverNuvemAWS(List<Usuario> Usuario){
        System.out.println("Escrevendo usuario em cloud");
        System.out.println(Usuario);
    };
}



interface ponteEscrever<T> {
    void escreverBanco(List<T> itens);
    void escreverNuvemAWS(List<T> itens);
}