/*
Crie uma aplicação em Java que recebe via linha de comando :
    (1) o nome de um arquivo compactado a ser criado.
    (2) Uma pasta.
    (3) Compactar todos os arquivos e subpastas em um arquivo compactado com extensão zip.
 */
import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactarZIP {

    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Use: java CompactarZip <arquivo_destino.zip> <pasta_origem>");
        }
        // Obtém o nome do arquivo compactado e o diretório de origem a partir dos argumentos da linha de comando
        String zipFileName = args[0];
        String sourceFolder = args[1];
        try {
            // Chama a função para compactar a pasta e seus conteúdos
            zipDirectory(sourceFolder, zipFileName);
            // Exibe uma mensagem de conclusão bem-sucedida
            System.out.println("Compactacao concluida com sucesso");
        } catch (IOException e) {
            // Imprime mensagem de erro em caso de falha na compactação
            System.err.println("Erro ao compactar a pasta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Criar um arquivo ZIP a partir de uma pasta de origem e chama a função zipFolder para realizar a compactação.
    public static void zipDirectory(String sourceFolder, String zipFileName) throws IOException{
        // Cria objetos File para representar a pasta de origem e o arquivo ZIP de destino
        File sourceFolderFile = new File(sourceFolder);
        File zipFile = new File(zipFileName);

        try(FileOutputStream fos = new FileOutputStream(zipFile);
            // Cria um objeto ZipOutputStream para escrever no arquivo ZIP
            ZipOutputStream zos = new ZipOutputStream(fos)){

            // Chama a função para compactar a pasta e seus conteúdos
            zipFolder(sourceFolderFile, sourceFolderFile.getName(), zos);
        }catch (IOException e){
            // Imprime mensagem de erro, se ocorrer algum problema durante a compactação
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Compactar uma pasta e seus conteúdos em um arquivo ZIP
    public static void zipFolder(File folder, String parentFolder, ZipOutputStream zos) throws IOException{
        // Itera sobre os arquivos e subpastas dentro da pasta 'folder'
        for(File file : Objects.requireNonNull(folder.listFiles())){
            if(file.isDirectory()){
                // Se o 'file' for um diretório, chama recursivamente a função para compactar a subpasta
                zipFolder(file, parentFolder + "/" + file.getName(), zos);
                continue; // pula para o próximo arquivo
            }

            try(FileInputStream fis = new FileInputStream(file)){
                // Obtém o nome da entrada (arquivo) no arquivo ZIP
                String entryName = parentFolder + "/" + file.getName();
                // Cria uma entrada ZipEntry para representar o arquivo no arquivo ZIP
                ZipEntry zipEntry = new ZipEntry(entryName);
                // Prepara o 'zos' para escrever o conteúdo deste arquivo ZIP
                zos.putNextEntry(zipEntry);

                // Lê e escreve o conteúdo do arquivo para o arquivo ZIP em blocos de 1024 bytes
                byte[] buffer = new byte[1024];
                int len;
                while((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
            }catch (IOException e){
                // Imprime mensagem de erro se ocorrer algum problema durante a compactação de um arquivo
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
