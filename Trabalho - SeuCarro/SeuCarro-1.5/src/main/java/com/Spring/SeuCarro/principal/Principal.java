package com.Spring.SeuCarro.principal;

import com.Spring.SeuCarro.ui.Carro.CRUDCarros;
import com.Spring.SeuCarro.ui.Cliente.CRUDClientes;
import com.Spring.SeuCarro.ui.Venda.CRUDVendas;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Principal {

	public static void main(String[] args) {
		CRUDCarros crudCarros = new CRUDCarros();
		CRUDClientes crudClientes = new CRUDClientes();
		CRUDVendas crudVendas = new CRUDVendas();

		char opcao;
		do {
			opcao = exibirMenuPrincipal();

			try {
				switch (opcao) {
					case '1':
						crudCarros.run();
						break;
					case '2':
						crudClientes.run();
						break;
					case '3':
						crudVendas.run();
						break;
					case '4':
						System.out.println("Saindo...");
						break;
					default:
						System.out.println("Opção Inválida");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro: " + e.getMessage());
			}

		} while (opcao != '4');
	}

	private static char exibirMenuPrincipal() {
		String menu = """
                Escolha uma opção:
                1 - Operações com Carros
                2 - Operações com Clientes
                3 - Operações com Vendas
                4 - Sair""";

		return exibirMenu(menu);
	}

	private static char exibirMenu(String menu) {
		System.out.println(menu);

		// Leitura da opção do usuário
		try {
			int opcao = Integer.parseInt(System.console().readLine());
			if (opcao >= 1 && opcao <= 4) {
				return (char) ('0' + opcao);
			} else {
				System.out.println("Opção Inválida");
				return exibirMenu(menu);
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção Inválida");
			return exibirMenu(menu);
		}
	}
}
