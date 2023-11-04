package com.Spring.SeuCarro.principal;

import com.Spring.SeuCarro.ui.CRUDCarros;
import com.Spring.SeuCarro.ui.CRUDClientes;
import com.Spring.SeuCarro.ui.CRUDVendas;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.JOptionPane;

@SpringBootApplication
@EntityScan("com.Spring.SeuCarro.entity")
@EnableJpaRepositories("com.Spring.SeuCarro.dao")
@ComponentScan("com.Spring.SeuCarro.ui")
public class Principal {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Principal.class).headless(false).run(args);

		CRUDCarros crudCarros = context.getBean(CRUDCarros.class);
		CRUDClientes crudClientes = context.getBean(CRUDClientes.class);
		CRUDVendas crudVendas = context.getBean(CRUDVendas.class);

		String menu = """
                Escolha uma opção:
                1 - Carro
                2 - Cliente
                3 - Venda
                4 - Sair""";

		String opcao;
		do {
			opcao = JOptionPane.showInputDialog(menu);
			if (opcao == null) { // Usuário clicou em Cancelar ou fechou a janela
				break;
			}
			switch (opcao) {
				case "1":
					crudCarros.run();
					break;
				case "2":
					crudClientes.run();
					break;
				case "3":
					crudVendas.run();
					break;
				case "4":
					// Sair
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção Inválida");
					break;
			}
		} while (!"4".equals(opcao)); // Continua até o usuário escolher "4 - Sair"
	}
}
