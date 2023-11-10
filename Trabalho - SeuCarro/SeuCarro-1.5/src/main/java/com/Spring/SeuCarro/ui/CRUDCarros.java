package com.Spring.SeuCarro.ui;

import com.Spring.SeuCarro.dao.CarroDAO;
import com.Spring.SeuCarro.entity.Carro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class CRUDCarros {

    @Autowired
    private CarroDAO carroDAO;

    public void obterCarro(Carro carro) {
        carro.setMarca(JOptionPane.showInputDialog("Marca", carro.getMarca()));
        carro.setModelo(JOptionPane.showInputDialog("Modelo", carro.getModelo()));
        carro.setConfiguracao(JOptionPane.showInputDialog("Configuração", carro.getConfiguracao()));
        carro.setAnoFabricacao(Integer.parseInt(JOptionPane.showInputDialog("Ano de Fabricação", String.valueOf(carro.getAnoFabricacao()))));
        carro.setTipo_combustivel(JOptionPane.showInputDialog("Tipo de Combustível", carro.getTipo_combustivel()));
        carro.setCor(JOptionPane.showInputDialog("Cor", carro.getCor()));
        carro.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Preço", String.valueOf(carro.getPreco()))));
    }

    public void listaCarros(List<Carro> carros) {
        StringBuilder listagem = new StringBuilder();
        for (Carro carro : carros) {
            listagem.append(carro).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.toString().isEmpty() ? "Nenhum carro encontrado" : listagem.toString());
    }

    public void listaCarro(Carro carro) {
        JOptionPane.showMessageDialog(null, carro == null ? "Nenhum carro encontrado" : carro);
    }

    public void menu() {
        StringBuilder menu = new StringBuilder("Menu Carros\n")
                .append("1 - Inserir carro\n")
                .append("2 - Atualizar por id\n")
                .append("3 - Remover por id\n")
                .append("4 - Exibir por id\n")
                .append("5 - Exibir todos\n")
                .append("6 - Exibir carros por ano de fabricação\n")
                .append("7 - Exibir carros por preço máximo\n")
                .append("8 - Exibir carros por ano de fabricação entre\n")
                .append("9 - Menu anterior");

        char opcao = '0';

        do {
            try {
                Carro carro;
                int id;
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                switch (opcao) {
                    case '1':
                        carro = new Carro();
                        obterCarro(carro);
                        carroDAO.save(carro);
                        break;
                    case '2':
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do carro a ser atualizado"));
                        carro = carroDAO.findById(id).orElse(null);
                        if (carro != null) {
                            obterCarro(carro); // Permita ao usuário atualizar os detalhes do carro
                            carroDAO.save(carro);
                            JOptionPane.showMessageDialog(null, "Carro atualizado com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Carro não encontrado.");
                        }
                        break;
                    case '3':
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do carro a ser removido"));
                        carro = carroDAO.findById(id).orElse(null);
                        if (carro != null) {
                            carroDAO.delete(carro);
                        } else {
                            JOptionPane.showMessageDialog(null, "Carro não encontrado.");
                        }
                        break;
                    case '4':
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do carro a ser exibido"));
                        carro = carroDAO.findById(id).orElse(null);
                        if (carro != null) {
                            listaCarro(carro);
                        } else {
                            JOptionPane.showMessageDialog(null, "Carro não encontrado.");
                        }
                        break;
                    case '5':
                        listaCarros(carroDAO.findAll());
                        break;
                    case '6':
                        int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação"));
                        listaCarros(carroDAO.findByAnoFabricacao(ano));
                        break;
                    case '7':
                        double maxPreco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço máximo"));
                        listaCarros(carroDAO.findCarrosComPrecoMenorQue(maxPreco));
                        break;
                    case '8':
                        int anoInicial = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação inicial"));
                        int anoFinal = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação final"));
                        listaCarros(carroDAO.findByAnoFabricacaoBetween(anoInicial, anoFinal));
                        break;
                    case '9':
                        // Sair
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while (opcao != '9');
    }
}
