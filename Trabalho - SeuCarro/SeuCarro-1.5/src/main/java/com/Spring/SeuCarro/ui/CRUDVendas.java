package com.Spring.SeuCarro.ui;

import com.Spring.SeuCarro.dao.ClienteDAO;
import com.Spring.SeuCarro.dao.VendaDAO;
import com.Spring.SeuCarro.entity.Carro;
import com.Spring.SeuCarro.entity.Cliente;
import com.Spring.SeuCarro.entity.Venda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class CRUDVendas {
    @Autowired
    private VendaDAO vendaDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    public void obterVenda(Venda venda) {
        // Implemente a obtenção dos campos da venda conforme a sua necessidade
    }

    public void listarVendas(List<Venda> vendas) {
        // Implemente a exibição da lista de vendas
    }

    public void listarVenda(Venda venda) {
        // Implemente a exibição de uma única venda
    }

    public void menu() {
        StringBuilder menu = new StringBuilder("Menu Vendas\n")
                .append("1 - Exibir Vendas por Marca de Carro\n")
                .append("2 - Exibir Vendas entre Datas\n")
                .append("3 - Exibir Vendas com Preço Maior que\n")
                .append("4 - Exibir Vendas por Modelo de Carro\n")
                .append("5 - Criar Venda\n")
                .append("6 - Atualizar Venda\n")
                .append("7 - Remover Venda\n")
                .append("8 - Exibir Todas as Vendas\n")
                .append("9 - Menu anterior");
        char opcao = '0';
        do {
            try {
                Venda venda;
                int id;
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                switch (opcao) {
                    case '1': // Exibir Vendas por Marca de Carro
                        exibirVendasPorMarcaCarro();
                        break;
                    case '2': // Exibir Vendas entre Datas
                        exibirVendasEntreDatas();
                        break;
                    case '3': // Exibir Vendas com Preço Maior que
                        exibirVendasComPrecoMaiorQue();
                        break;
                    case '4': // Exibir Vendas por Modelo de Carro
                        exibirVendasPorModeloCarro();
                        break;
                    case '5': // Criar Venda
                        criarVenda();
                        break;
                    case '6': // Atualizar Venda
                        atualizarVenda();
                        break;
                    case '7': // Remover Venda
                        removerVenda();
                        break;
                    case '8': // Exibir Todas as Vendas
                        exibirTodasVendas();
                        break;
                    case '9': // Menu anterior
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while(opcao != '9');
    }

    private void exibirVendasPorMarcaCarro() {
        String marca = JOptionPane.showInputDialog("Digite a marca do carro");
        List<Venda> vendas = vendaDAO.findByCarroMarca(marca);
        listarVendas(vendas);
    }

    private void exibirVendasEntreDatas() {
        LocalDate dataInicio = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de início (AAAA-MM-DD)"));
        LocalDate dataFim = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de fim (AAAA-MM-DD)"));
        List<Venda> vendas = vendaDAO.findVendasEntreDatas(dataInicio, dataFim);
        listarVendas(vendas);
    }

    private void exibirVendasComPrecoMaiorQue() {
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço mínimo"));
        List<Venda> vendas = vendaDAO.findVendasComPrecoMaiorQue(preco);
        listarVendas(vendas);
    }

    private void exibirVendasPorModeloCarro() {
        String modelo = JOptionPane.showInputDialog("Digite o modelo do carro");
        List<Venda> vendas = vendaDAO.findByCarroModelo(modelo);
        listarVendas(vendas);
    }

    private void criarVenda() {
        String marcaCarro = JOptionPane.showInputDialog("Marca do Carro");
        String cpfCliente = JOptionPane.showInputDialog("CPF do Cliente");
        LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Data da Venda (AAAA-MM-DD)"));
        Double precoCarro = Double.parseDouble(JOptionPane.showInputDialog("Preço do Carro"));

        // Realize a consulta para encontrar carros com preço maior que precoCarro
        List<Venda> vendas = vendaDAO.findVendasComPrecoMaiorQue(precoCarro);

        if (vendas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem carros com preço maior que " + precoCarro);
        } else {
            Carro carro = escolherCarro(vendas);
            if (carro != null) {
                Cliente cliente = buscaClientePorCpf(cpfCliente);
                if (cliente != null) {
                    Venda novaVenda = new Venda();
                    novaVenda.setCarro(carro);
                    novaVenda.setCliente(cliente);
                    novaVenda.setData(data);
                    vendaDAO.save(novaVenda);
                    JOptionPane.showMessageDialog(null, "Venda criada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado. Verifique o CPF.");
                }
            }
        }
    }

    private void atualizarVenda() {
        int idVenda = Integer.parseInt(JOptionPane.showInputDialog("ID da Venda"));
        Venda venda = vendaDAO.findById(idVenda).orElse(null);

        if (venda != null) {
            String marcaCarro = JOptionPane.showInputDialog("Nova Marca do Carro");
            String cpfCliente = JOptionPane.showInputDialog("Novo CPF do Cliente");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Nova Data da Venda (AAAA-MM-DD)"));

            Double precoCarro = Double.parseDouble(JOptionPane.showInputDialog("Novo Preço do Carro"));

            // Realize a consulta para encontrar carros com preço maior que precoCarro
            List<Venda> vendas = vendaDAO.findVendasComPrecoMaiorQue(precoCarro);

            if (vendas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não existem carros com preço maior que " + precoCarro);
            } else {
                Carro carroSelecionado = escolherCarro(vendas);
                if (carroSelecionado != null) {
                    Cliente cliente = buscaClientePorCpf(cpfCliente);
                    if (cliente != null) {
                        venda.setCarro(carroSelecionado);
                        venda.setCliente(cliente);
                        venda.setData(data);
                        vendaDAO.save(venda);
                        JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado. Verifique o CPF.");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o ID.");
        }
    }

    private Carro escolherCarro(List<Venda> vendas) {
        // Crie um array de ‘Strings’ para exibir as opções de carros
        String[] opcoes = new String[vendas.size()];

        for (int i = 0; i < vendas.size(); i++) {
            Carro carro = vendas.get(i).getCarro();
            opcoes[i] = carro.getMarca() + " " + carro.getModelo() + " (ID: " + carro.getId() + ")";
        }

        // Exiba um diálogo para permitir que o usuário escolha um carro
        String carroEscolhido = (String) JOptionPane.showInputDialog(null, "Escolha um carro:", "Escolher Carro",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        // Analise a escolha do usuário para encontrar o carro correspondente
        for (Venda venda : vendas) {
            Carro carro = venda.getCarro();
            String carroDescricao = carro.getMarca() + " " + carro.getModelo() + " (ID: " + carro.getId() + ")";
            if (carroEscolhido.equals(carroDescricao)) {
                return carro;
            }
        }

        // Se nenhum carro correspondente for encontrado, retorne null
        return null;
    }

    private Cliente buscaClientePorCpf(String cpfCliente) {
        Cliente cliente = clienteDAO.buscaPorCpf(cpfCliente);

        if (cliente != null) {
            return cliente;
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado. Verifique o CPF.");
            return null;
        }
    }
    

    private void removerVenda() {
        int idVenda = Integer.parseInt(JOptionPane.showInputDialog("ID da Venda"));
        Venda venda = vendaDAO.findById(idVenda).orElse(null);

        if (venda != null) {
            vendaDAO.delete(venda);
            JOptionPane.showMessageDialog(null, "Venda removida com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o ID.");
        }
    }


    private void exibirTodasVendas() {
        List<Venda> vendas = vendaDAO.findAll();
        listarVendas(vendas);
    }
}
