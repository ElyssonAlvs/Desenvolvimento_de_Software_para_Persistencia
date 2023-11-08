package com.Spring.SeuCarro.ui;

import com.Spring.SeuCarro.dao.ClienteDAO;
import com.Spring.SeuCarro.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class CRUDClientes {
    @Autowired
    private ClienteDAO clienteDAO;

    public void obterCliente(Cliente cl) {
        String nome = JOptionPane.showInputDialog("Nome", cl.getNome());
        String lastName = JOptionPane.showInputDialog("Last Name", cl.getLastName());
        String cpf = JOptionPane.showInputDialog("CPF", cl.getCpf());
        String fone = JOptionPane.showInputDialog("Fone", cl.getFone());

        cl.setNome(nome);
        cl.setLastName(lastName);
        cl.setCpf(cpf);
        cl.setFone(fone);
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
                .append("5 - Exibir por id\n")
                .append("6 - Exibir todos\n")
                .append("7 - Exibir todos que contém determinado nome\n")
                .append("8 - Encontrar clientes com telefone\n")
                .append("9 - Encontrar clientes por nome (Consulta Nativa)\n")
                .append("0 - Voltar ao menu anterior\n");

        String opcao = "0";
        do {
            try {
                Cliente cl;
                String cpf;
                opcao = String.valueOf(JOptionPane.showInputDialog(menu).charAt(0));
                switch (opcao) {
                    case "1": // Inserir
                        cl = new Cliente();
                        obterCliente(cl);
                        clienteDAO.save(cl);
                        break;
                    case "2": // Atualizar por CPF
                        cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser alterado");
                        cl = clienteDAO.buscaPorCpf(cpf);
                        if (cl != null) {
                            obterCliente(cl);
                            clienteDAO.save(cl);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois o cliente não foi encontrado.");
                        }
                        break;
                    case "3": // Remover por CPF
                        cpf = JOptionPane.showInputDialog("CPF");
                        cl = clienteDAO.buscaPorCpf(cpf);
                        if (cl != null) {
                            clienteDAO.delete(cl);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o cliente não foi encontrado.");
                        }
                        break;
                    case "4": // Exibir por CPF
                        cpf = JOptionPane.showInputDialog("CPF");
                        cl = clienteDAO.buscaPorCpf(cpf);
                        listaCliente(cl);
                        break;
                    case "5": // Exibir por id
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                        cl = clienteDAO.findById(id).orElse(null);
                        listaCliente(cl);
                        break;
                    case "6": // Exibir todos
                        listaClientes(clienteDAO.findAll());
                        break;
                    case "7": // Exibir todos que contém determinado nome
                        String nome = JOptionPane.showInputDialog("Nome");
                        listaClientes(clienteDAO.findByLastNameIgnoreCase(nome));
                        break;
                    case "8": // Encontrar clientes com telefone
                        List<Cliente> clientesComTelefone = clienteDAO.findClientesComTelefone();
                        listaClientes(clientesComTelefone);
                        break;
                    case "9": // Encontrar clientes por nome (Consulta Nativa)
                        String nomeConsultaNativa = JOptionPane.showInputDialog("Nome");
                        List<Cliente> clientesPorNomeNativa = clienteDAO.findByNome(nomeConsultaNativa);
                        listaClientes(clientesPorNomeNativa);
                        break;
                    case "0": // Voltar ao menu anterior
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while (!opcao.equals("0")); // Verifica se a opção é "0" para voltar ao menu anterior
    }

}
