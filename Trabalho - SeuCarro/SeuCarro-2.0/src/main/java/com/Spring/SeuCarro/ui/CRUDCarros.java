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
        carro.setMarca(entradaUsuario("Marca", carro.getMarca()));
        carro.setModelo(entradaUsuario("Modelo", carro.getModelo()));
        carro.setConfiguracao(entradaUsuario("Configuração", carro.getConfiguracao()));
        carro.setAnoFabricacao(Integer.parseInt(entradaUsuario("Ano de Fabricação", String.valueOf(carro.getAnoFabricacao()))));
        carro.setTipo_combustivel(entradaUsuario("Tipo de Combustível", carro.getTipo_combustivel()));
        carro.setCor(entradaUsuario("Cor", carro.getCor()));
        carro.setPreco(Double.parseDouble(entradaUsuario("Preço", String.valueOf(carro.getPreco()))));
    }

    private String entradaUsuario(String mensagem, String valorPadrao) {
        return JOptionPane.showInputDialog(null, mensagem, valorPadrao);
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
        String menu = "Menu Carros\n" +
                "1 - Inserir carro\n" +
                "2 - Remover por Modelo\n" +
                "3 - Exibir por Modelo\n" +
                "4 - Atualizar por Modelo\n" +
                "5 - Exibir todos\n" +
                "6 - Exibir carros por ano de fabricação\n" +
                "7 - Exibir carros por preço máximo\n" +
                "8 - Exibir carros por ano de fabricação entre\n" +
                "9 - Menu anterior";

        char opcao = '0';

        do {
            try {
                Carro carro;
                String modelo;
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                switch (opcao) {
                    case '1' -> {
                        // Inserir carro
                        carro = new Carro();
                        obterCarro(carro);
                        carroDAO.save(carro);
                    }
                    case '2'-> {
                        // Remover por modelo
                            String modeloRemover = JOptionPane.showInputDialog("Digite o modelo do carro a ser removido");

                            // Buscar carro com o modelo especificado
                            Carro carroRemover = carroDAO.findByModelo(modeloRemover);

                            if (carroRemover != null) {
                                // Remover o carro encontrado
                                carroDAO.delete(carroRemover);
                                JOptionPane.showMessageDialog(null, "Carro removido com sucesso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Nenhum carro com o modelo especificado encontrado.");
                            }
                    }
                    case '3' -> {
                        // Exibir por Modelo
                        modelo = JOptionPane.showInputDialog("Digite o modelo do carro a ser exibido");
                        carro = carroDAO.findByModelo(modelo);
                        if (carro != null) {
                            listaCarro(carro);
                        } else {
                            JOptionPane.showMessageDialog(null, "Carro não encontrado.");
                        }
                    }
                    case '4' -> {
                        // Atualizar por Modelo
                        modelo = JOptionPane.showInputDialog("Digite o modelo do carro a ser atualizado");
                        carro = carroDAO.findByModelo(modelo);
                        if (carro != null) {
                            obterCarro(carro);
                            carroDAO.save(carro);
                        } else {
                            JOptionPane.showMessageDialog(null, "Carro não encontrado.");
                        }
                    }
                    case '5' -> {
                        // Exibir todos
                        listaCarros(carroDAO.findAll());
                    }
                    case '6' -> {
                        // Exibir carros por ano de fabricação
                        int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação"));
                        listaCarros(carroDAO.findByAnoFabricacao(ano));
                    }
                    case '7' -> {
                        // Exibir carros por preço máximo
                        double maxPreco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço máximo"));
                        listaCarros(carroDAO.findCarrosComPrecoMenorQue(maxPreco));
                    }
                    case '8' -> {
                        // Exibir carros por ano de fabricação entre
                        int anoInicial = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação inicial"));
                        int anoFinal = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação final"));
                        listaCarros(carroDAO.findByAnoFabricacaoBetween(anoInicial, anoFinal));
                    }
                    case '9' -> {
                        // Menu anterior
                        break;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Opção Inválida");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while (opcao != '9');
    }
}
