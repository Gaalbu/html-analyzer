import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URI;
import java.net.URL;
import java.util.Stack;

public class HtmlAnalyzer {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) return; //url inválida
        String urlString = args[0];
        Stack<String> pilha = new Stack<>();
        int profundidadeMaxima = -1;
        String textoMaisProfundo = "";

        try {
            BufferedReader conteudoUrl = new BufferedReader(new InputStreamReader(new URI(urlString).toURL().openStream()));
            String linhaAtual;
            while ((linhaAtual = conteudoUrl.readLine()) != null) {
                linhaAtual = linhaAtual.trim(); // s/indentação
                if (linhaAtual.isEmpty()) continue;
                
                //tag de abertura
                if(linhaAtual.charAt(0) == '<'){ 
                
                    //fechamento
                    if(linhaAtual.charAt(1) == '/'){ 
                        
                    }
                }

            
            }
        } catch (ConnectException connectionError){
            System.err.println("URL connection error");

        }

       

    }

}