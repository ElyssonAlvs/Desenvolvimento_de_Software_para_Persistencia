package com.Spring.SeuCarro.ui;

import com.Spring.SeuCarro.dao.ClienteDAO;
import com.Spring.SeuCarro.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.List;

@Slf4j
@Component
public class CRUDClientes {

    @Autowired
    private ClienteDAO clienteDAO;

    public void obterCliente(Cliente cliente) {
        cliente.setNome(validaCampo("Nome", cliente.getNome()));
        cliente.setLastName(validaCampo("Last Name", cliente.getLastName()));
        cliente.setCpf(validaCampo("CPF", cliente.getCpf()));
        cliente.setFone(validaCampo("Fone", cliente.getFone()));
    }

    private String validaCampo(String label, String valorAtual) {
        String novoValor = JOptionPane.showInputDialog(label, valorAtual);
        while (novoValor == null || novoValor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido para " + label);
            novoValor = JOptionPane.showInputDialog(label, valorAtual);
        }
        return novoValor;
    }

    public void listaClientes(List<Cliente> clientes) {
        StringBuilder listagem = new StringBuilder();
        for (Cliente cl : clientes) {
            listagem.append(cl.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.isEmpty() ? "Nenhum cliente encontrado" : listagem);
    }

    public void listaCliente(Cliente cl) {
        JOptionPane.showMessageDialog(null, cl == null ? "Nenhum cliente encontrado" : cl.toString());
    }

    public void menu() {
        StringBuilder menu = new StringBuilder("Menu Clientes\n")
                .append("1 - Inserir\n")
                .append("2 - Atualizar por CPF\n")
                .append("3 - Remover por CPF\n")
                .append("4 - Exibir por CPF\n")
                .append("5 - Exibir todos\n")
                .append("6 - Encontrar clientes por nome\n")
                .append("7 - Voltar ao menu anterior\n");

        String opcao="";
        do {
            try {
                opcao = JOptionPane.showInputDialog(menu);

                switch (opcao) {
                    case "1": // Inserir
                        Cliente novoCliente = new Cliente();
                        obterCliente(novoCliente);
                        clienteDAO.save(novoCliente);
                        break;
                    case "2": // Atualizar por CPF
                        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser alterado");
                        Cliente clienteExistente = clienteDAO.buscaPorCpf(cpf);
                        if (clienteExistente != null) {
                            int escolha = JOptionPane.showConfirmDialog(
                                    null,
                                    "Deseja atualizar o cliente com CPF: " + cpf + "?",
                                    "Confirmação",
                                    JOptionPane.YES_NO_OPTION
                            );
                            if (escolha == JOptionPane.YES_OPTION) {
                                obterCliente(clienteExistente);
                                clienteDAO.save(clienteExistente);
                                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Operação de atualização cancelada.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado para atualização.");
                        }
                        break;

                    case "3": // Remover por CPF
                        cpf = JOptionPane.showInputDialog("CPF");
                        clienteExistente = clienteDAO.buscaPorCpf(cpf);
                        if (clienteExistente != null) {
                            clienteDAO.delete(clienteExistente);
                            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado para remoção.");
                        }
                        break;

                    case "4": // Exibir por CPF
                        cpf = JOptionPane.showInputDialog("CPF");
                        Cliente clientePorCpf = clienteDAO.buscaPorCpf(cpf);
                        listaCliente(clientePorCpf);
                        break;
                    case "5": // Exibir todos
                        listaClientes(clienteDAO.findAll());
                        break;
                    case "6": // Encontrar clientes por nome
                        String nomeConsultaNativa = JOptionPane.showInputDialog("Nome");
                        List<Cliente> clientesPorNomeNativa = clienteDAO.findByNome(nomeConsultaNativa);
                        listaClientes(clientesPorNomeNativa);
                        break;
                    case "7": // Voltar ao menu anterior
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um valor válido.");
            } catch (Exception e) {
                log.error("Erro durante a execução do menu", e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while (!opcao.equals("7"));
    }
}
