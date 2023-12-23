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

    private void listarVenda(Venda venda ) {
        String vendaInfo = "Detalhes da Venda:\n" + "ID: " + venda.getId() +
                ", Cliente: " + venda.getCliente().getNome() +
                ", Carro: " + venda.getCarro().getMarca() +
                ", Data: " + venda.getData();
        JOptionPane.showMessageDialog(null, vendaInfo);
    }

    public void menu() {
        // Método que representa o menu principal para interação com o usuário
        // Exibe opções como exibir vendas por marca, entre datas, criar, atualizar, remover, etc.
        // Usa um loop para manter o usuário interagindo até a opção '8' ser escolhida.

        StringBuilder menu = new StringBuilder("Menu Vendas\n")
                .append("1 - Exibir Vendas por Marca de Carro\n")
                .append("2 - Exibir Vendas entre Datas\n")
                .append("3 - Exibir Vendas por Modelo de Carro\n")
                .append("4 - Criar Venda\n")
                .append("5 - Atualizar Venda\n")
                .append("6 - Remover Venda\n")
                .append("7 - Exibir Todas as Vendas\n")
                .append("8 - Exibir por ID\n")
                .append("0 - Menu anterior");
        char opcao = '0';
        do {
            try {
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                switch (opcao) {
                    case '1': // Exibir Vendas por Marca de Carro
                        exibirVendasPorMarcaCarro();
                        break;
                    case '2': // Exibir Vendas entre Datas
                        exibirVendasEntreDatas();
                        break;
                    case '3': // Exibir Vendas por Modelo de Carro
                        exibirVendasPorModeloCarro();
                        break;
                    case '4': // Criar Venda
                        criarVenda();
                        break;
                    case '5': // Atualizar Venda
                        atualizarVenda();
                        break;
                    case '6': // Remover Venda
                        removerVenda();
                        break;
                    case '7': // Exibir Todas as Vendas
                        exibirTodasVendas();
                        break;
                    case '8':
                        // Exibir uma Venda
                        int idVendaParaExibir = Integer.parseInt(JOptionPane.showInputDialog("ID da Venda"));
                        Venda vendaParaExibir = vendaDAO.findById(idVendaParaExibir).orElse(null);
                        if (vendaParaExibir != null) {
                            listarVenda(vendaParaExibir);
                        } else {
                            JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o ID.");
                        }
                        break;
                    case '0': // Menu anterior
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
        // Método para exibir vendas filtradas por marca de carro
        // Usa o JOptionPane para obter a marca e chama o método do DAO para encontrar as vendas correspondentes.
        // Em seguida, chama o método para listar as vendas.

        String marca = JOptionPane.showInputDialog("Digite a marca do carro");
        List<Venda> vendas = vendaDAO.findByCarroMarca(marca);
        listarVendas(vendas);
    }

    private void exibirVendasEntreDatas() {
        // Método para exibir vendas filtradas por um intervalo de datas
        // Obtém as datas usando JOptionPane e chama o método do DAO para encontrar as vendas no intervalo.
        // Chama o método para listar as vendas.

        LocalDate dataInicio = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de início (AAAA-MM-DD)"));
        LocalDate dataFim = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de fim (AAAA-MM-DD)"));
        List<Venda> vendas = vendaDAO.findVendasEntreDatas(dataInicio, dataFim);
        listarVendas(vendas);
    }

    private void exibirVendasPorModeloCarro() {
        // Método para exibir vendas filtradas por modelo de carro
        String modelo = JOptionPane.showInputDialog("Digite o modelo do carro");
        List<Venda> vendas = vendaDAO.findByCarroModelo(modelo);
        listarVendas(vendas);
    }

    private void criarVenda() {
        // Método para criar uma nova venda
        // Verifica se o carro e cliente existem, então cria uma nova venda e a salva usando o DAO.

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
        // Método para atualizar uma venda existente
        // Obtém o ID da venda usando JOptionPane

        int idVenda = Integer.parseInt(JOptionPane.showInputDialog("ID da Venda"));
        Venda venda = vendaDAO.findById(idVenda).orElse(null);

        if (venda != null) {
            // Obtém novas informações (marca, CPF do cliente, data) e atualiza a venda correspondente usando o DAO.
            String marcaCarro = JOptionPane.showInputDialog("Nova Marca do Carro");
            String cpfCliente = JOptionPane.showInputDialog("Novo CPF do Cliente");
            LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Nova Data da Venda (AAAA-MM-DD)"));

            Cliente cliente = buscaClientePorCpf(cpfCliente);
            if (cliente != null) {
                // Atualize as informações da venda
                venda.getCarro().setMarca(marcaCarro);
                venda.setCliente(cliente);
                venda.setData(data);
                vendaDAO.save(venda);
                JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado. Verifique o CPF.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Venda não encontrada. Verifique o ID.");
        }
    }

    private Cliente buscaClientePorCpf(String cpfCliente) {
        // Método para buscar um cliente por CPF no DAO
        // Retorna o cliente se encontrado, senão exibe uma mensagem de erro e retorna null.
        Cliente cliente = clienteDAO.buscaPorCpf(cpfCliente);

        if (cliente != null) {
            return cliente;
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado. Verifique o CPF.");
            return null;
        }
    }
    

    private void removerVenda() {
        // Método para remover uma venda

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
