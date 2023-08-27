import java.io.*;

public class EscreverESalvar {
    public static void main(String[] args) throws IOException{
        try{
            // pega do teclado, e guarda em uma buffer, para ser modificado depois
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // Cria uma StringBuilder
            StringBuilder texto = new StringBuilder();
            String linha;
            // Laço para ir escrevendo o que foi escrito no teclado para a StringBuilder
            // Enquanto a linha for diferente do buffer de leitura a cada próxima linha, e ignorando qualquer caso de String "OK", ele via escrever e gravar no texto
            while(!(linha = br.readLine()).equalsIgnoreCase("OK")){
                texto.append(linha).append((System.lineSeparator()));
            }
            // Onde salvar o texto digitado, em um .txt
            System.out.println("Onde quer salvar o texto digitado ? ");
            // Pega a resposta com a relação ao local que vai ser salvo o conteúdo
            String nomeArquivo = br.readLine();
            // vai tentar escrever oque foi digitado dento do arquivo informado
            // BufferedWrite bw, onde vai criar um novo escritor que vai criar uma caneta para escrever, o FileWriter
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))){
                bw.write(texto.toString());
                System.out.println("Texto salvo com sucesso!");
            }catch(IOException e){
                System.out.println("Erro ao salvar : " + e.getMessage());
            }
        }catch (IOException e){
            System.out.println("Erro : " + e.getMessage());
            e.getStackTrace();
        }
    }
}
