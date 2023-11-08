package com.Spring.SeuCarro.ui;

import com.Spring.SeuCarro.dao.ClienteDAO;
import com.Spring.SeuCarro.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.util.List;

@ComponentScan(basePackages = "com.Spring.SeuCarro")
@Component
@Slf4j
@Configuration
public class CRUDClientes implements CommandLineRunner {

    @Autowired
    private ClienteDAO clienteDAO;

    public void run(String... args) {
        MenuClientes menu = new MenuClientes();

        char opcao;
        do {
            opcao = menu.exibirMenu();

            try {
                switch (opcao) {
                    case '1':
                        inserirCliente();
                        break;
                    case '2':
                        atualizarClientePorCPF();
                        break;
                    case '3':
                        removerClientePorCPF();
                        break;
                    case '4':
                        exibirClientePorCPF();
                        break;
                    case '5':
                        exibirClientePorId();
                        break;
                    case '6':
                        exibirTodosClientes();
                        break;
                    case '7':
                        exibirClientesPorNome();
                        break;
                    case '8':
                        JOptionPane.showMessageDialog(null, "Saindo...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while (opcao != '8');
    }

    private void inserirCliente() {
        Cliente cliente = new Cliente();
        obterCliente(cliente);
        clienteDAO.save(cliente);
        JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso.");
    }

    private void atualizarClientePorCPF() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser alterado");
        Cliente cliente = clienteDAO.buscaPorCpf(cpf);

        if (cliente != null) {
            obterCliente(cliente);
            clienteDAO.save(cliente);
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }

    private void removerClientePorCPF() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser removido");
        Cliente cliente = clienteDAO.buscaPorCpf(cpf);

        if (cliente != null) {
            clienteDAO.delete(cliente);
            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }

    private void exibirClientePorCPF() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente");
        Cliente cliente = clienteDAO.buscaPorCpf(cpf);
        listaCliente(cliente);
    }

    private void exibirClientePorId() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente"));
        Cliente cliente = clienteDAO.findById(id).orElse(null);
        listaCliente(cliente);
    }

    private void exibirTodosClientes() {
        List<Cliente> clientes = clienteDAO.findAll();
        listaClientes(clientes);
    }

    private void exibirClientesPorNome() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente");
        List<Cliente> clientes = clienteDAO.findByNome(nome);
        listaClientes(clientes);
    }

    private void obterCliente(Cliente cliente) {
        cliente.setNome(JOptionPane.showInputDialog("Nome", cliente.getNome()));
        cliente.setLastName(JOptionPane.showInputDialog("Sobrenome", cliente.getLastName()));
        cliente.setCpf(JOptionPane.showInputDialog("CPF", cliente.getCpf()));
        cliente.setFone(JOptionPane.showInputDialog("Fone", cliente.getFone()));
    }

    private void listaClientes(List<Cliente> clientes) {
        StringBuilder listagem = new StringBuilder("Clientes:\n");
        for (Cliente cliente : clientes) {
            listagem.append(cliente).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.toString());
    }

    private void listaCliente(Cliente cliente) {
        JOptionPane.showMessageDialog(null, cliente != null ? cliente.toString() : "Cliente não encontrado");
    }

    private static class MenuClientes {
        public char exibirMenu() {
            String menu = """
                    Escolha uma opção:
                    1 - Inserir Cliente
                    2 - Atualizar Cliente por CPF
                    3 - Remover Cliente por CPF
                    4 - Exibir Cliente por CPF
                    5 - Exibir Cliente por ID
                    6 - Exibir Todos os Clientes
                    7 - Exibir Clientes por Nome
                    8 - Sair""";

            return JOptionPane.showInputDialog(menu).charAt(0);
        }
    }
}
