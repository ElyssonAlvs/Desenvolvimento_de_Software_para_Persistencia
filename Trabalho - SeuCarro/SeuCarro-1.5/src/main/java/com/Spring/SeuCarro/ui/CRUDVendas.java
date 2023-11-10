package com.Spring.SeuCarro.ui;

import com.Spring.SeuCarro.dao.CarroDAO;
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
import java.util.stream.Collectors;

@Slf4j
@Component
public class CRUDVendas {
    @Autowired
    private VendaDAO vendaDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private CarroDAO carroDAO;

    private void listarVendas(List<Venda> vendas) {
        StringBuilder listaVendas = new StringBuilder("Lista de Vendas:\n");
        for (Venda venda : vendas) {
            listaVendas.append("ID: ").append(venda.getId())
                    .append(", Cliente: ").append(venda.getCliente().getNome())
                    .append(", Carro: ").append(venda.getCarro().getMarca())
                    .append(", Data: ").append(venda.getData()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaVendas.toString());
    }

    private void listarVenda(Venda venda ) {
        String vendaInfo = "Detalhes da Venda:\n" + "ID: " + venda.getId() +
                ", Cliente: " + venda.getCliente().getNome() +
                ", Carro: " + venda.getCarro().getMarca() +
                ", Data: " + venda.getData();
        JOptionPane.showMessageDialog(null, vendaInfo);
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
                    case '0':
                        // Exibir uma Venda
                        int idVendaParaExibir = Integer.parseInt(JOptionPane.showInputDialog("ID da Venda"));
                        Venda vendaParaExibir = vendaDAO.findById(idVendaParaExibir).orElse(null);
                        if (vendaParaExibir != null) {
                            listarVenda(vendaParaExibir);
                        } else {
                            JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o ID.");
                        }
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
        String modeloCarro = JOptionPane.showInputDialog("Modelo do Carro");
        String cpfCliente = JOptionPane.showInputDialog("CPF do Cliente");
        LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Data da Venda (AAAA-MM-DD)"));

        // Verifica se existe um carro com a marca e modelo especificados
        Carro carro = carroDAO.findByMarcaAndModelo(marcaCarro, modeloCarro);

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
        } else {
            JOptionPane.showMessageDialog(null, "Não existem carros da marca e modelo especificados.");
        }
    }




    private void atualizarVenda() {
        int idVenda = Integer.parseInt(JOptionPane.showInputDialog("ID da Venda"));
        Venda venda = vendaDAO.findById(idVenda).orElse(null);

        if (venda != null) {
            String marcaCarro = JOptionPane.showInputDialog("Nova Marca do Carro");
            String cpfCliente = JOptionPane.showInputDialog("Novo CPF do Cliente");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Nova Data da Venda (AAAA-MM-DD)"));

            Double novoPrecoCarro = Double.parseDouble(JOptionPane.showInputDialog("Novo Preço do Carro"));

            // Realize a consulta para encontrar vendas com preço maior que novoPrecoCarro
            List<Venda> vendas = vendaDAO.findVendasComPrecoMaiorQue(novoPrecoCarro);

            if (vendas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não existem vendas com preço maior que " + novoPrecoCarro);
            } else {
                Venda vendaSelecionada = escolherVenda(vendas);
                if (vendaSelecionada != null) {
                    Cliente cliente = buscaClientePorCpf(cpfCliente);
                    if (cliente != null) {
                        vendaSelecionada.getCarro().setMarca(marcaCarro); // Atualize a marca do carro na venda
                        vendaSelecionada.setCliente(cliente);
                        vendaSelecionada.setData(data);
                        vendaDAO.save(vendaSelecionada);
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

    private Venda escolherVenda(List<Venda> vendas) {
        // Crie um array de ‘strings’ para exibir as opções de vendas
        String[] opcoes = new String[vendas.size()];

        for (int i = 0; i < vendas.size(); i++) {
            Venda venda = vendas.get(i);
            opcoes[i] = "Venda ID: " + venda.getId() + " - Data: " + venda.getData() + " - Cliente: " + venda.getCliente().getNome();
        }

        // Exiba um diálogo para permitir que o usuário escolha uma venda
        String vendaEscolhida = (String) JOptionPane.showInputDialog(null, "Escolha uma venda:", "Escolher Venda",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        // Analise a escolha do usuário para encontrar a venda correspondente
        for (Venda venda : vendas) {
            String vendaDescricao = "Venda ID: " + venda.getId() + " - Data: " + venda.getData() + " - Cliente: " + venda.getCliente().getNome();
            if (vendaEscolhida.equals(vendaDescricao)) {
                return venda;
            }
        }

        // Se nenhuma venda correspondente for encontrada, retorne null
        return null;
    }


    private Carro escolherCarro(List<Carro> carros) {
        // Crie um array de 'Strings' para exibir as opções de carros
        String[] opcoes = new String[carros.size()];

        for (int i = 0; i < carros.size(); i++) {
            Carro carro = carros.get(i);
            opcoes[i] = carro.getMarca() + " " + carro.getModelo() + " (ID: " + carro.getId() + ")";
        }

        // Exiba um diálogo para permitir que o usuário escolha um carro
        String carroEscolhido = (String) JOptionPane.showInputDialog(null, "Escolha um carro:", "Escolher Carro",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        // Analise a escolha do usuário para encontrar o carro correspondente
        for (Carro carro : carros) {
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
