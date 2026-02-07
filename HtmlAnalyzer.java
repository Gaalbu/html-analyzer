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
        Stack<String> pilhaTags = new Stack<>();
        int profundidadeMaxima = -1;
        int profundidadeAtual = -1;
        String textoMaisProfundo = "";

        try {
            BufferedReader conteudoUrl = new BufferedReader(new InputStreamReader(new URI(urlString).toURL().openStream()));
            String linhaAtual;
            while ((linhaAtual = conteudoUrl.readLine()) != null) {
                linhaAtual = linhaAtual.trim(); // s/indentação
                if (linhaAtual.isEmpty()) continue;
                
                if(linhaAtual.startsWith("<") ){ 
                    if(linhaAtual.startsWith("</")){ 
                        //fechamento
                        if (pilhaTags.isEmpty()){
                            System.out.println("malformed HTML");
                            return;
                        }

                    
                    String tagAberta = pilhaTags.pop();
                    if(!tagAberta.replace("<","").replace(">", "")
                            .equals(linhaAtual.replace("/", "").replace(">",""))){
                        System.out.println("malformedHTML");
                        return;
                    }
                    profundidadeAtual--;
                    }else{  
                        //tag abertura
                        pilhaTags.push(linhaAtual);
                        profundidadeAtual++;
                        profundidadeMaxima = profundidadeMaxima < profundidadeAtual ? profundidadeAtual : profundidadeMaxima;
                    }
                }else{
                    //aqui é o texto de fato
                    if (profundidadeAtual > profundidadeMaxima){
                        profundidadeMaxima = profundidadeAtual;
                        textoMaisProfundo = linhaAtual;
                    }else if (profundidadeAtual == profundidadeMaxima && textoMaisProfundo.isEmpty()){
                        textoMaisProfundo = linhaAtual;
                    }
                }
            }
            
            System.out.println(!pilhaTags.isEmpty() ? "malformedHTML" : textoMaisProfundo);

        } catch (ConnectException connectionError){
            System.err.println("URL connection error");

        }

    }

}