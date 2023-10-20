package main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import dao.ProdutoDAO;
import entity.Produto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "dao")
@Slf4j
public class Principal implements CommandLineRunner {

    @Autowired
    private ProdutoDAO baseProdutos;


    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Principal.class);
        builder.headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        String menu = "Escolha uma opção:\n1 - Inserir\n2 - Atualizar por Código\n3 - Remover por Código\n4 - Exibir por Código\n5 - Exibir por Id\n6 - Exibir todos\n7 - Exibir todos com determinada descrição\n8 - Exibir por preço\n9 - Exibir por data de última entrada\n10 - Sair";
        String opcao = "0";
        do {
            try {
                Produto cl;
                int codigo;
                opcao = JOptionPane.showInputDialog(menu);
                switch (opcao) {
                    case "1": // Inserir
                        cl = new Produto();
                        obterProduto(cl);
                        baseProdutos.save(cl);
                        JOptionPane.showMessageDialog(null, "Produto inserido com sucesso.");
                        break;
                    case "2": // Atualizar por Código
                        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Código do produto a ser alterado"));
                        cl = baseProdutos.findByCodigo(codigo);
                        if (cl != null) {
                            obterProduto(cl);
                            baseProdutos.save(cl);
                            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Produto não encontrado para atualização.");
                        }
                        break;
                    case "3": // Remover por Código
                        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Código do produto a ser removido"));
                        cl = baseProdutos.findByCodigo(codigo);
                        if (cl != null) {
                            baseProdutos.delete(codigo);
                            JOptionPane.showMessageDialog(null, "Produto removido com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Produto não encontrado para remoção.");
                        }
                        break;
                    case "4": // Exibir por Código
                        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Código do produto"));
                        cl = baseProdutos.findByCodigo(codigo);
                        if (cl != null) {
                            listaProduto(cl);
                        } else {
                            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                        }
                        break;
                    case "5": // Exibir por Id
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                        cl = baseProdutos.find(id);
                        if (cl != null) {
                            listaProduto(cl);
                        } else {
                            JOptionPane.showMessageDialog(null, "Produto com o ID especificado não encontrado.");
                        }
                        break;
                    case "6": // Exibir todos
                        List<Produto> todosProdutos = baseProdutos.find();
                        if (!todosProdutos.isEmpty()) {
                            listaProdutos(todosProdutos);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado.");
                        }
                        break;
                    case "7": // Exibir todos com determinada descrição
                        String descricao = JOptionPane.showInputDialog("Digite a descrição desejada");
                        List<Produto> produtosComDescricao = baseProdutos.findByDescricao(descricao);
                        if (produtosComDescricao != null && !produtosComDescricao.isEmpty()) {
                            listaProdutos(produtosComDescricao);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com a descrição especificada.");
                        }
                        break;
                    case "8": // Exibir por preço
                        double precoFiltrar = Double.parseDouble(JOptionPane.showInputDialog("Preço máximo:"));
                        List<Produto> produtosPorPreco = baseProdutos.findByPreco(precoFiltrar);
                        listaProdutos(produtosPorPreco);
                        break;
                    case "9": // Exibir por data de última entrada
                        String dataInicialStr = JOptionPane.showInputDialog("Data Inicial (AAAA-MM-DD):");
                        String dataFinalStr = JOptionPane.showInputDialog("Data Final (AAAA-MM-DD):");
                        try {
                            LocalDate dataInicial = LocalDate.parse(dataInicialStr);
                            LocalDate dataFinal = LocalDate.parse(dataFinalStr);
                            List<Produto> produtosPorData = baseProdutos.findByDataUltimaEntrada(dataInicial, dataFinal);
                            listaProdutos(produtosPorData);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
                        }
                        break;
                    case "10": // Sair
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (NumberFormatException e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Entrada inválida: " + e.getMessage());
            } catch (Exception e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while (!Objects.equals(opcao, "10"));
    }

    public static void obterProduto(Produto cl) {
        String input;
        input = JOptionPane.showInputDialog("Digite o Código");
        if (input != null && !input.isEmpty()) {
            cl.setCodigo(Integer.parseInt(input));
        }
        input = JOptionPane.showInputDialog("Digite a Descrição");
        if (input != null) {
            cl.setDescricao(input);
        }
        input = JOptionPane.showInputDialog("Digite o Preço");
        if (input != null && !input.isEmpty()) {
            cl.setPreco(BigDecimal.valueOf(Double.parseDouble(input)));
        }
        input = JOptionPane.showInputDialog("Digite a Quantidade");
        if (input != null && !input.isEmpty()) {
            cl.setQuantidade(Integer.parseInt(input));
        }
        input = JOptionPane.showInputDialog("Digite a Última Entrada");
        if (input != null) {
            cl.setUltimaEntrada(input);
        }
    }

    public static void listaProdutos(List<Produto> produtos) {
        // Inicializa um StringBuilder para construir a lista de produtos
        StringBuilder listagem = new StringBuilder();

        for (Produto cl : produtos) {
            // Adiciona informações do produto atual ao StringBuilder
            listagem.append("ID: ").append(cl.getId()).append("\n");
            listagem.append("Código: ").append(cl.getCodigo()).append("\n");
            listagem.append("Descrição: ").append(cl.getDescricao()).append("\n");
            listagem.append("Preço: ").append(cl.getPreco()).append("\n");
            listagem.append("Quantidade: ").append(cl.getQuantidade()).append("\n");
            listagem.append("Última Entrada: ").append(cl.getUltimaEntrada()).append("\n");
            listagem.append("------------------------------\n");
        }

        // Cria uma área de texto rolável com o conteúdo da lista
        JTextArea textArea = new JTextArea(20, 40); // Número de linhas e colunas visíveis
        textArea.setText(listagem.toString()); // Define o texto na área de texto
        textArea.setEditable(false); // Torna a área de texto somente leitura

        // Adiciona a área de texto a um painel de rolagem
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Exibe a lista de produtos em uma caixa de diálogo com barra de rolagem
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Produtos", JOptionPane.PLAIN_MESSAGE);
    }

    public static void listaProduto(Produto cl) {
        if (cl == null) {
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado");
        } else {
            String mensagem = "ID: " + cl.getId() + "\n" +
                    "Código: " + cl.getCodigo() + "\n" +
                    "Descrição: " + cl.getDescricao() + "\n" +
                    "Preço: " + cl.getPreco() + "\n" +
                    "Quantidade: " + cl.getQuantidade() + "\n" +
                    "Última Entrada: " + cl.getUltimaEntrada() + "\n";
            JOptionPane.showMessageDialog(null, mensagem);
        }
    }
}
