package com.Spring.SeuCarro.ui.Carro;

import com.Spring.SeuCarro.dao.CarroDAO;
import com.Spring.SeuCarro.entity.Carro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
@Slf4j
public class CRUDCarros implements CommandLineRunner {

    @Autowired
    private CarroDAO carroDAO;

    public void run(String... args) {
        MenuCarros menu = new MenuCarros();

        char opcao;
        do {
            opcao = menu.exibirMenu();

            try {
                switch (opcao) {
                    case '1':
                        inserirCarro();
                        break;
                    case '2':
                        atualizarCarroPorMarca();
                        break;
                    case '3':
                        removerCarroPorId();
                        break;
                    case '4':
                        exibirCarroPorId();
                        break;
                    case '5':
                        exibirTodosCarros();
                        break;
                    case '6':
                        exibirCarrosPorAnoFabricacao();
                        break;
                    case '7':
                        exibirCarrosPorPrecoMaximo();
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

    private void inserirCarro() {
        Carro carro = new Carro();
        obterCarro(carro);
        carroDAO.save(carro);
        JOptionPane.showMessageDialog(null, "Carro inserido com sucesso.");
    }

    private void atualizarCarroPorMarca() {
        String marca = JOptionPane.showInputDialog("Digite a marca do carro a ser alterado");
        List<Carro> carros = carroDAO.findByMarca(marca);

        if (!carros.isEmpty()) {
            Carro carro = escolherCarroDaLista(carros);
            assert carro != null;
            obterCarro(carro);
            carroDAO.save(carro);
            JOptionPane.showMessageDialog(null, "Carro atualizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum carro encontrado com a marca especificada.");
        }
    }

    private void removerCarroPorId() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do carro a ser removido"));
        Carro carro = carroDAO.findById(id).orElse(null);

        if (carro != null) {
            carroDAO.delete(carro);
            JOptionPane.showMessageDialog(null, "Carro removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Carro não encontrado.");
        }
    }

    private void exibirCarroPorId() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do carro"));
        Carro carro = carroDAO.findById(id).orElse(null);
        listaCarro(carro);
    }

    private void exibirTodosCarros() {
        List<Carro> carros = carroDAO.findAll();
        listaCarros(carros);
    }

    private void exibirCarrosPorAnoFabricacao() {
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de fabricação"));
        List<Carro> carros = carroDAO.findByAnoFabricacao(ano);
        listaCarros(carros);
    }

    private void exibirCarrosPorPrecoMaximo() {
        double maxPreco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço máximo"));
        List<Carro> carros = carroDAO.findCarrosComPrecoMenorQue(maxPreco);
        listaCarros(carros);
    }

    private Carro escolherCarroDaLista(List<Carro> carros) {
        String[] options = carros.stream().map(carro -> carro.getId() + " - " + carro.getMarca() + " " + carro.getModelo()).toArray(String[]::new);
        int choice = JOptionPane.showOptionDialog(null, "Escolha o carro a ser atualizado:", "Selecionar Carro", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice != -1) {
            return carros.get(choice);
        }

        return null;
    }

    private void obterCarro(Carro carro) {
        carro.setMarca(JOptionPane.showInputDialog("Marca", carro.getMarca()));
        carro.setModelo(JOptionPane.showInputDialog("Modelo", carro.getModelo()));
        carro.setConfiguracao(JOptionPane.showInputDialog("Configuração", carro.getConfiguracao()));
        carro.setAno_fabricacao(Integer.parseInt(JOptionPane.showInputDialog("Ano de Fabricação", String.valueOf(carro.getAno_fabricacao()))));
        carro.setTipo_combustivel(JOptionPane.showInputDialog("Tipo de Combustível", carro.getTipo_combustivel()));
        carro.setCor(JOptionPane.showInputDialog("Cor", carro.getCor()));
        carro.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Preço", String.valueOf(carro.getPreco()))));
    }

    private void listaCarros(List<Carro> carros) {
        StringBuilder listagem = new StringBuilder("Carros:\n");
        for (Carro carro : carros) {
            listagem.append(carro).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.toString());
    }

    private void listaCarro(Carro carro) {
        JOptionPane.showMessageDialog(null, carro != null ? carro.toString() : "Carro não encontrado");
    }

    private static class MenuCarros {
        public char exibirMenu() {
            String menu = """
                    Escolha uma opção:
                    1 - Inserir Carro
                    2 - Atualizar Carro por Marca
                    3 - Remover Carro por ID
                    4 - Exibir Carro por ID
                    5 - Exibir Todos os Carros
                    6 - Exibir Carros por Ano de Fabricação
                    7 - Exibir Carros por Preço Máximo
                    8 - Sair""";

            return JOptionPane.showInputDialog(menu).charAt(0);
        }
    }
}
