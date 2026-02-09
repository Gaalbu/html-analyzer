import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URI;
import java.util.Stack;

public class HtmlAnalyzer {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) return; //url inválida
        String urlString = args[0];
        Stack<String> pilhaTags = new Stack<>();
        int profundidadeMaxima = 0;
        int profundidadeAtual = 0;
        String textoMaisProfundo = "";

        try (BufferedReader conteudoUrl = new BufferedReader(new InputStreamReader(new URI(urlString).toURL().openStream()))){
            String linhaAtual;
            while ((linhaAtual = conteudoUrl.readLine()) != null) {
                linhaAtual = linhaAtual.trim(); // s/indentação
                if (linhaAtual.isEmpty()) continue;
                
                if(linhaAtual.startsWith("<") ){ 
                    if(linhaAtual.startsWith("</")){ 
                        //compara c uma tag de abertura anterior
                        if (pilhaTags.isEmpty()){
                            System.out.println("malformed HTML");
                            return;
                        }
                    
                    String tagAberta = pilhaTags.pop();
                    String nomeTagFechamento = linhaAtual.substring(2, linhaAtual.length() - 1).trim();

                    if(!tagAberta.equals(nomeTagFechamento)){
                        System.out.println("malformed HTML");
                        return;
                    }

                    profundidadeAtual--;
                    }else{  
                        //adiciona tag
                        String conteudoTag = linhaAtual.substring(1, linhaAtual.length()-1);
                        String nomePuro = conteudoTag.split(" ")[0]; // to tratando aqui para não pegar atributos de tag, ex: style type
                        pilhaTags.push(nomePuro);
                        profundidadeAtual++;
                    }
                }else{
                    //aqui é o texto de fato
                    if (profundidadeAtual > profundidadeMaxima){
                        profundidadeMaxima = profundidadeAtual;
                        textoMaisProfundo = linhaAtual;
                    }
                }
            }
            
            System.out.println(!(pilhaTags.isEmpty()) ? "malformed HTML" : textoMaisProfundo);

        } catch (ConnectException connectionError){
            System.err.println("URL connection error");

        } catch (IOException ioExceptionError){ //Erro mais abrangente de leitura
            System.err.println("URL connection error");
        } catch (Exception e){ //Erro de parsing some aqui, virando malformed
            System.out.println("malformed HTML");
        }

    }

}