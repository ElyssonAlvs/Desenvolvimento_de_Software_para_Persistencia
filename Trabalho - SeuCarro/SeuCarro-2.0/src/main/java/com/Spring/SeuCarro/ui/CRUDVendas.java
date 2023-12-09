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
                    .append(", Carro: marca- ").append(venda.getCarro().getMarca()).append(", modelo- ").append(venda.getCarro().getModelo())
                    .append(", Data: ").append(venda.getData()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaVendas.toString());
    }
/*
    private void listarVenda(Venda venda ) {
        String vendaInfo = "Detalhes da Venda:\n" + "ID: " + venda.getId() +
                ", Cliente: " + venda.getCliente().getNome() +
                ", Carro: " + venda.getCarro().getMarca() +
                ", Data: " + venda.getData();
        JOptionPane.showMessageDialog(null, vendaInfo);
    }
    */

    public void menu() {
        StringBuilder menu = new StringBuilder("Menu Vendas\n")
                .append("1 - Exibir Vendas por Marca de Carro\n")
                .append("2 - Exibir Vendas entre Datas\n")
                .append("3 - Exibir Vendas por Modelo de Carro\n")
                .append("4 - Criar Venda\n")
                .append("5 - Atualizar Venda pelo CPF do Cliente e Modelo do veículo\n")
                .append("6 - Remover Venda pelo CPF do Cliente\n")
                .append("7 - Exibir Todas as Vendas\n")
                .append("0 - Menu anterior");

        char opcao = '0';
        do {
            try {
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                switch (opcao) {
                    case '1':
                        exibirVendasPorMarcaCarro();
                        break;
                    case '2':
                        exibirVendasEntreDatas();
                        break;
                    case '3':
                        exibirVendasPorModeloCarro();
                        break;
                    case '4':
                        criarVenda();
                        break;
                    case '5':
                        atualizarVendaPorCPFModelo();
                        break;
                    case '6':
                        removerVenda();
                        break;
                    case '7':
                        exibirTodasVendas();
                        break;
                    case '0':
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while(opcao != '0');
    }

    private void exibirVendasPorMarcaCarro() {
        String marca = JOptionPane.showInputDialog("Digite a marca do carro");
        List<Venda> vendas = vendaDAO.findByCarroMarca(marca);
        listarVendas(vendas);
    }

    private void exibirVendasEntreDatas() {
        LocalDate dataInicio = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de início (AAAA-MM-DD)"));
        LocalDate dataFim = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de fim (AAAA-MM-DD)"));
        List<Venda> vendas = vendaDAO.findByDataBetween(dataInicio, dataFim);
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

private void atualizarVendaPorCPFModelo() {
        try {
            String cpfCliente = JOptionPane.showInputDialog("CPF do Cliente");
            String modeloCarro = JOptionPane.showInputDialog("Modelo do Carro");

            Cliente cliente = clienteDAO.buscaPorCpf(cpfCliente);
            Carro carro = carroDAO.findByModelo(modeloCarro);

            if (cliente != null && carro != null) {
                List<Venda> vendas = vendaDAO.findByClienteAndCarro(cliente, carro);
                if (!vendas.isEmpty()) {
                    Venda venda = vendas.get(0);
                    LocalDate newData = LocalDate.parse(JOptionPane.showInputDialog("Nova Data da Venda (AAAA-MM-DD)"));
                    venda.setData(newData);
                    vendaDAO.save(venda);
                    JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o CPF do Cliente e o Modelo do Carro.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cliente ou Carro não encontrado. Verifique o CPF do Cliente e o Modelo do Carro.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a venda: " + e.getMessage());
        }
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
        try {
            String cpfCliente = JOptionPane.showInputDialog("Digite o CPF do Cliente");
            String modeloCarro = JOptionPane.showInputDialog("Digite o Modelo do Carro");

            Cliente cliente = clienteDAO.buscaPorCpf(cpfCliente);
            Carro carro = carroDAO.findByModelo(modeloCarro);

            if (cliente != null && carro != null) {
                List<Venda> vendas = vendaDAO.findByClienteAndCarro(cliente, carro);
                if (!vendas.isEmpty()) {
                    Venda venda = vendas.get(0);
                    vendaDAO.delete(venda);
                    JOptionPane.showMessageDialog(null, "Venda removida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o CPF do Cliente e o Modelo do Carro.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cliente ou Carro não encontrado. Verifique o CPF do Cliente e o Modelo do Carro.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover a venda: " + e.getMessage());
        }
    }


    private void exibirTodasVendas() {
        List<Venda> vendas = vendaDAO.findAll();
        listarVendas(vendas);
    }
}
