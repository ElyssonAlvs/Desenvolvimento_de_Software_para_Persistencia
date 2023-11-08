package com.Spring.SeuCarro.ui;

import com.Spring.SeuCarro.dao.ClienteDAO;
import com.Spring.SeuCarro.dao.VendaDAO;
import com.Spring.SeuCarro.entity.Carro;
import com.Spring.SeuCarro.entity.Cliente;
import com.Spring.SeuCarro.entity.Venda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

@ComponentScan(basePackages = "com.Spring.SeuCarro")
@Component
@Slf4j
@Configuration
public class CRUDVendas implements CommandLineRunner {

    @Autowired
    private VendaDAO vendaDAO;
    @Autowired
    private ClienteDAO clienteDAO;

    public void run(String... args) {
        MenuVendas menu = new MenuVendas();

        char opcao;
        do {
            opcao = menu.exibirMenu();

            try {
                switch (opcao) {
                    case '1':
                        exibirVendasPorMarcaCarro();
                        break;
                    case '2':
                        exibirVendasEntreDatas();
                        break;
                    case '3':
                        exibirVendasComPrecoMaiorQue();
                        break;
                    case '4':
                        exibirVendasPorModeloCarro();
                        break;
                    case '5':
                        criarVenda();
                        break;
                    case '6':
                        atualizarVenda();
                        break;
                    case '7':
                        removerVenda();
                        break;
                    case '8':
                        exibirTodasVendas();
                        break;
                    case '9':
                        JOptionPane.showMessageDialog(null, "Saindo...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while (opcao != '9');
    }

    private void exibirVendasPorMarcaCarro() {
        String marca = JOptionPane.showInputDialog("Digite a marca do carro");
        List<Venda> vendas = vendaDAO.findByCarroMarca(marca);
        listaVendas(vendas);
    }

    private void exibirVendasEntreDatas() {
        LocalDate dataInicio = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de início (AAAA-MM-DD)"));
        LocalDate dataFim = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de fim (AAAA-MM-DD)"));
        List<Venda> vendas = vendaDAO.findVendasEntreDatas(dataInicio, dataFim);
        listaVendas(vendas);
    }

    private void exibirVendasComPrecoMaiorQue() {
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço mínimo"));
        List<Venda> vendas = vendaDAO.findVendasComPrecoMaiorQue(preco);
        listaVendas(vendas);
    }

    private void exibirVendasPorModeloCarro() {
        String modelo = JOptionPane.showInputDialog("Digite o modelo do carro");
        List<Venda> vendas = vendaDAO.findByCarroModelo(modelo);
        listaVendas(vendas);
    }

    private void criarVenda() {
        String marcaCarro = JOptionPane.showInputDialog("Marca do Carro");
        String cpfCliente = JOptionPane.showInputDialog("CPF do Cliente");
        LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Data da Venda (AAAA-MM-DD)"));
        Double precoCarro = Double.parseDouble(JOptionPane.showInputDialog("Preço do Carro"));

        List<Venda> vendas = vendaDAO.findVendasComPrecoMaiorQue(precoCarro);

        if (vendas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem carros com preço maior que " + precoCarro);
        } else {
            Carro carro = escolherCarro(vendas);
            if (carro != null) {
                Cliente cliente = buscarClientePorCpf(cpfCliente);
                if (cliente != null) {
                    Venda novaVenda = new Venda();
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
            List<Venda> vendas = vendaDAO.findVendasComPrecoMaiorQue(precoCarro);

            if (vendas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não existem carros com preço maior que " + precoCarro);
            } else {
                Carro carroSelecionado = escolherCarro(vendas);
                if (carroSelecionado != null) {
                    Cliente cliente = buscarClientePorCpf(cpfCliente);
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
        String[] opcoes = new String[vendas.size()];

        for (int i = 0; i < vendas.size(); i++) {
            opcoes[i] = vendas.get(i).getCarro().getMarca() + " " + vendas.get(i).getCarro().getModelo();
        }

        String carroEscolhido = (String) JOptionPane.showInputDialog(null, "Escolha um carro:", "Escolher Carro",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        for (Venda venda : vendas) {
            if (carroEscolhido.equals(venda.getCarro().getMarca() + " " + venda.getCarro().getModelo())) {
                return venda.getCarro();
            }
        }

        return null;
    }

    private Cliente buscarClientePorCpf(String cpf) {

        return clienteDAO.buscaPorCpf(cpf);
    }


    private void removerVenda() {
        int idVenda = Integer.parseInt(JOptionPane.showInputDialog("ID da Venda"));
        Venda venda = vendaDAO.findById(idVenda).orElse(null);

        if (venda != null) {
            vendaDAO.delete(venda);
            JOptionPane.showMessageDialog(null, "Venda removida com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o ID.");
        }    }

    private void exibirTodasVendas() {
        List<Venda> vendas = vendaDAO.findAll();
        listaVendas(vendas);
    }

    private void listaVendas(List<Venda> vendas) {
        StringBuilder listagem = new StringBuilder("Vendas:\n");
        for (Venda venda : vendas) {
            listagem.append(venda).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.toString());
    }

    private static class MenuVendas {
        public char exibirMenu() {
            String menu = """
                    Escolha uma opção:
                    1 - Exibir Vendas por Marca de Carro
                    2 - Exibir Vendas entre Datas
                    3 - Exibir Vendas com Preço Maior que
                    4 - Exibir Vendas por Modelo de Carro
                    5 - Criar Venda
                    6 - Atualizar Venda
                    7 - Remover Venda
                    8 - Exibir Todas as Vendas
                    9 - Sair""";

            return JOptionPane.showInputDialog(menu).charAt(0);
        }
    }
}
