package org.SeuCarro;

import org.SeuCarro.Entity.Carro;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Carro> carros = CsvDataLer.lerDados();
            while (true) {
                exibirMenu();

                try {
                    int escolha = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    processarEscolha(escolha, scanner, carros);

                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Certifique-se de inserir um número.");
                    scanner.nextLine(); // Limpar o buffer do scanner
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao criar o Scanner: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("SeuCarro:");
        System.out.println("1. Inserir entidade no arquivo CSV");
        System.out.println("2. Mostrar quantidade de entidades no arquivo CSV");
        System.out.println("3. Converter dados do arquivo CSV para JSON e XML");
        System.out.println("4. Compactar arquivo CSV para ZIP");
        System.out.println("5. Calcular hash SHA256 do arquivo CSV");
        System.out.println("6. Exibir tabela de carros");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void processarEscolha(int escolha, Scanner scanner, List<Carro> carros) {
        switch (escolha) {
            case 1:
                inserirEntidade(scanner, carros);
                break;
            case 2:
                mostrarQuantidadeEntidades();
                break;
            case 3:
                converterParaJsonEXml(carros);
                break;
            case 4:
                compactarParaZip();
                break;
            case 5:
                calcularHashSHA256();
                break;
            case 6:
                exibirTabela(carros);
                break;
            case 7:
                encerrarAplicacao();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void inserirEntidade(Scanner scanner, List<Carro> carros) {
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
        System.out.print("Preço do veículo: ");

        // Substitui vírgula por ponto e formata com Locale.US
        double preco = Double.parseDouble(scanner.next().replace(",", ".").replace(".", "").replace(",", "."));

        Carro carro = new Carro(idCarro, modelo, marca, configuracao, anoFabricacao, tipoCombustivel, preco);
        CsvData.inserirCarro(carro);

        // Atualiza a lista de carros após a inserção
        carros = CsvDataLer.lerDados();
    }

    private static void mostrarQuantidadeEntidades() {
        try {
            int quantidadeEntidades = CsvDataContador.contarEntidades();
            System.out.println("Quantidade de entidades no arquivo CSV: " + quantidadeEntidades);
        } catch (Exception e) {
            System.out.println("Erro ao contar entidades: " + e.getMessage());
        }
    }

    private static void converterParaJsonEXml(List<Carro> carros) {
        JsonData.converterParaJson(carros);
        XmlData.converterParaXml(carros);
        System.out.println("Conversão concluída: carros.json e carros.xml foram criados.");
    }

    private static void compactarParaZip() {
        ZipData.converterParaZip();
        System.out.println("Compactação concluída: arquivo CSV compactado para ZIP.");
    }

    private static void calcularHashSHA256() {
        String hash = HashCalculator.calcularSHA256Hash();
        System.out.println("Hash SHA-256 do arquivo CSV: " + hash);
    }

    private static void encerrarAplicacao() {
        System.out.println("Encerrando a aplicação.");
        System.exit(0);
    }

    private static void exibirTabela(List<Carro> carros) {
        if (carros.isEmpty()) {
            System.out.println("Nenhum carro disponível.");
            return;
        }

        System.out.println("Tabela de Carros:");
        System.out.println("+------+-----------------+------------+------------------------+------+-----------------+------------+");
        System.out.println("|  ID  |     Modelo      |   Marca    |      Configuração      | Ano  | Tipo Combustível|   Preço    |");
        System.out.println("+------+-----------------+------------+------------------------+------+-----------------+------------+");

        for (Carro carro : carros) {
            System.out.printf("| %-5d | %-15s | %-10s | %-20s | %-4d | %-15s | %-10s |\n",
                    carro.getId(),
                    carro.getModelo(),
                    carro.getMarca(),
                    carro.getConfiguracao(),
                    carro.getAnoFabricacao(),
                    carro.getTipoCombustivel(),
                    String.format(Locale.US, "%.2f", carro.getPreco())); // Formata com ponto
        }

        System.out.println("+------+-----------------+------------+------------------------+------+-----------------+------------+");
    }
}