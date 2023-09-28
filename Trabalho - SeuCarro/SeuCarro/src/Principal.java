import java.io.*;
import java.util.List;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("SeuCarro:");
            System.out.println("1. Inserir entidade no arquivo CSV");
            System.out.println("2. Mostrar quantidade de entidades no arquivo CSV");
            System.out.println("3. Converter dados do arquivo CSV para JSON e XML");
            System.out.println("4. Compactar arquivo CSV para ZIP");
            System.out.println("5. Calcular hash SHA256 do arquivo CSV");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            try {
                switch (escolha) {
                    case 1:
                        // Chamar a classe CsvData para inserir dados
                        // Primeiro, você precisa criar um objeto Carro com os seus dados
                        System.out.println("Digite os detalhes do carro:");
                        System.out.print("ID: ");
                        int idCarro = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha
                        System.out.print("Modelo: ");
                        String modelo = scanner.nextLine();
                        System.out.print("Marca: ");
                        String marca = scanner.nextLine();
                        System.out.print("Configuração: ");
                        String configuracao = scanner.nextLine();
                        System.out.print("Ano de Fabricação: ");
                        int anoFabricacao = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha
                        System.out.print("Tipo de Combustível: ");
                        String tipoCombustivel = scanner.nextLine();

                        // Crie um objeto Carro com os dados fornecidos
                        Carro carro = new Carro(idCarro, modelo, marca, configuracao, anoFabricacao, tipoCombustivel);

                        // Chame o método para inserir o carro no arquivo CSV
                        CsvData.inserirCarro(carro);
                        break;
                    case 2:
                        // Chamar a classe CsvData para contar entidades
                        try {
                            int quantidadeEntidades = CsvDataContador.contarEntidades();
                            System.out.println("Quantidade de entidades no arquivo CSV: " + quantidadeEntidades);
                        } catch (IOException e) {
                            System.out.println("Erro ao contar entidades: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        // Chamar as classes JsonData e XmlData para conversão
                        try {
                            // Lê os dados do arquivo CSV
                            List<Carro> carros = CsvDataLer.lerDados();

                            // Chama a classe JsonData para converter para JSON
                            JsonData.converterParaJson(carros, "carros.json");

                            // Chama a classe XmlData para converter para XML
                            XmlData.converterParaXml(carros, "carros.xml");

                            System.out.println("Conversão concluída: carros.json e carros.xml foram criados.");
                        } catch (IOException e) {
                            System.out.println("Erro ao realizar a conversão: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        // Chamar a classe ZipData para compactação
                        try {
                            ZipData.converterParaZip();
                            System.out.println("Compactação concluída: arquivo CSV compactado para ZIP.");
                        } catch (IOException e) {
                            System.out.println("Erro ao compactar o arquivo CSV: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        // Chamar a classe HashCalculator para calcular hash
                        try {
                            String hash = HashCalculator.calcularSHA256Hash();
                            System.out.println("Hash SHA-256 do arquivo CSV: " + hash);
                        } catch (IOException e) {
                            System.out.println("Erro ao calcular o hash: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        // Sair da aplicação
                        System.out.println("Encerrando a aplicação.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
