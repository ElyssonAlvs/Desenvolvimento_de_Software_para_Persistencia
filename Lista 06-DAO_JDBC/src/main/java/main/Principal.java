package main;

import dao.ProdutoDAO;
import dao.ProdutoJDBCDAO;
import entity.Produto;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class Principal {

    public static void main(String[] args) {
        ProdutoDAO baseProdutos = new ProdutoJDBCDAO();
        String menu = "Escolha uma opção:\n1 - Inserir\n2 - Atualizar por Código\n3 - Remover por Código\n4 - Exibir por Código\n5 - Exibir por ID\n6 - Exibir todos\n7 - Exibir todos que contem determinada descrição\n8 - Exibir produtos com preço menor ou igual ao máximo\n9 - Exibir produtos entre datas\n10 - Sair";
        String opcao = "0";
        do {
            try {
                Produto cl;
                int codigo;
                opcao = JOptionPane.showInputDialog(menu);
                switch (opcao) {
                    case "1":     // Inserir
                        cl = new Produto();
                        obterProduto(cl);
                        baseProdutos.save(cl);
                        JOptionPane.showMessageDialog(null, "Produto inserido com sucesso.");
                        break;
                    case "2": // Atualizar por Código
                        try {
                            codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Código do produto a ser alterado"));
                            Produto produtoParaAtualizar = baseProdutos.findByCodigo(codigo);

                            if (produtoParaAtualizar != null) {
                                obterProduto(produtoParaAtualizar);
                                baseProdutos.save(produtoParaAtualizar);
                                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado para atualização.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "O código do produto deve ser um número válido.");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro inesperado ao atualizar o produto: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case "3": // Remover por Código
                        try {
                            codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto a ser removido"));
                            Produto produtoParaRemover = baseProdutos.findByCodigo(codigo);

                            if (produtoParaRemover != null) {
                                baseProdutos.delete(produtoParaRemover.getCodigo());
                                JOptionPane.showMessageDialog(null, "Produto removido com sucesso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado para remoção.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "O código do produto deve ser um número válido.");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Erro ao remover o produto: " + e.getMessage());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro inesperado ao remover o produto: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case "4": // Exibir por CODIGO
                        try {
                            codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
                            Produto produtoExibido = baseProdutos.findByCodigo(codigo);

                            if (produtoExibido != null) {
                                listaProduto(produtoExibido);
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "O código do produto deve ser um número válido.");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;


                    case "5": // Exibir por id
                        try {
                            int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                            Produto produtoExibido = baseProdutos.find(id);

                            if (produtoExibido != null) {
                                listaProduto(produtoExibido);
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto com o ID especificado não encontrado.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "O ID do produto deve ser um número válido.");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case "6": // Exibir todos
                        try {
                            List<Produto> todosProdutos = baseProdutos.find();

                            if (!todosProdutos.isEmpty()) {
                                listaProdutos(todosProdutos);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case "7": // Exibir todos que contêm determinada descrição
                        try {
                            String descricao = JOptionPane.showInputDialog("Digite a descrição desejada");
                            List<Produto> produtosComDescricao = (List<Produto>) baseProdutos.findByDescricao(descricao);

                            if (produtosComDescricao != null && !produtosComDescricao.isEmpty()) {
                                listaProdutos(produtosComDescricao);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com a descrição especificada.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;

                    case "8": // Obter produtos por preço
                        double precoFiltrar = Double.parseDouble(JOptionPane.showInputDialog("Preço máximo:"));
                        List<Produto> produtosPorPreco = baseProdutos.findByPreco(precoFiltrar);
                        listaProdutos(produtosPorPreco);
                        break;
                    case "9": // Obter produtos por data de última entrada
                        String dataInicialStr = JOptionPane.showInputDialog("Data Inicial (AAAA-MM-DD):");
                        String dataFinalStr = JOptionPane.showInputDialog("Data Final (AAAA-MM-DD):");

                        try {
                            LocalDate dataInicial = LocalDate.parse(dataInicialStr);
                            LocalDate dataFinal = LocalDate.parse(dataFinalStr);

                            List<Produto> produtosPorData = baseProdutos.findByDataUltimaEntrada(dataInicial, dataFinal);
                            listaProdutos(produtosPorData);
                        } catch (DateTimeParseException e) {
                            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato AAAA-MM-DD.");
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
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while(!Objects.equals(opcao, "10"));
    }

    public static void obterProduto(Produto cl) {
        String input;

        input = JOptionPane.showInputDialog("Digite o Código");
        if (!input.isEmpty()) {
            cl.setCodigo(Integer.parseInt(input)); // Define o código do produto com o valor digitado
        }

        input = JOptionPane.showInputDialog("Digite a Descrição");
        if (!input.isEmpty()) {
            cl.setDescricao(input); // Define a descrição do produto com o valor digitado
        }

        input = JOptionPane.showInputDialog("Digite o Preço");
        if (!input.isEmpty()) {
            cl.setPreco(BigDecimal.valueOf(Double.parseDouble(input))); // Define o preço do produto com o valor digitado
        }

        input = JOptionPane.showInputDialog("Digite a Quantidade");
        if (!input.isEmpty()) {
            cl.setQuantidade(Integer.parseInt(input)); // Define a quantidade do produto com o valor digitado
        }

        input = JOptionPane.showInputDialog("Digite a Última Entrada");
        if (!input.isEmpty()) {
            cl.setUltimaEntrada(input); // Define a data da última entrada do produto com o valor digitado
        }
    }

    public static void listaProdutos(List<Produto> produtos) {
        // Inicializa um StringBuilder para construir uma lista de produtos
        StringBuilder listagem = new StringBuilder();

        for (Produto cl : produtos) {
            // Adiciona informações do produto atual ao StringBuilder
            listagem.append("ID: ").append(cl.getId()).append("\n"); // Adiciona o ID do produto à lista.
            listagem.append("Código: ").append(cl.getCodigo()).append("\n"); // Adiciona o código do produto à lista.
            listagem.append("Descrição: ").append(cl.getDescricao()).append("\n"); // Adiciona a descrição do produto à lista.
            listagem.append("Preço: ").append(cl.getPreco()).append("\n"); // Adiciona o preço do produto à lista.
            listagem.append("Quantidade: ").append(cl.getQuantidade()).append("\n"); // Adiciona a quantidade do produto à lista.
            listagem.append("Última Entrada: ").append(cl.getUltimaEntrada()).append("\n"); // Adiciona a data de última entrada do produto à lista.
            listagem.append("------------------------------\n"); // Adiciona uma linha separadora entre os produtos.
        }

        // Exibe a lista de produtos em uma caixa de diálogo, verificando se a lista não está vazia
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum produto encontrado" : listagem.toString());
    }

    public static void listaProduto(Produto cl) {
        if (cl == null) { // Verifica se o objeto Produto é nulo.
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado"); // Exibe uma mensagem caso o produto seja nulo.
        } else {
            StringBuilder mensagem = new StringBuilder(); // Cria um StringBuilder para construir a mensagem de exibição.
            mensagem.append("ID: ").append(cl.getId()).append("\n"); // Adiciona o ID do produto à mensagem.
            mensagem.append("Código: ").append(cl.getCodigo()).append("\n"); // Adiciona o código do produto à mensagem.
            mensagem.append("Descrição: ").append(cl.getDescricao()).append("\n"); // Adiciona a descrição do produto à mensagem.
            mensagem.append("Preço: ").append(cl.getPreco()).append("\n"); // Adiciona o preço do produto à mensagem.
            mensagem.append("Quantidade: ").append(cl.getQuantidade()).append("\n"); // Adiciona a quantidade do produto à mensagem.
            mensagem.append("Última Entrada: ").append(cl.getUltimaEntrada()).append("\n"); // Adiciona a data da última entrada do produto à mensagem.

            JOptionPane.showMessageDialog(null, mensagem.toString()); // Exibe a mensagem contendo os detalhes do produto.
        }
    }

}
