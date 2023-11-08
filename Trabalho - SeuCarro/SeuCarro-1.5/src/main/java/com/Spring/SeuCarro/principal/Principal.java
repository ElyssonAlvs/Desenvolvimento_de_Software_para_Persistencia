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
@Slf4j
public class Principal implements CommandLineRunner {

	@Autowired
	private CRUDCarros crudCarros;

	@Autowired
	private CRUDClientes crudClientes;

	@Autowired
	private CRUDVendas crudVendas;


	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MenuPrincipal.class);
		builder.headless(false).run(args);
	}

	public static void run(String... args) throws Exception {
		StringBuilder menu = new StringBuilder( """
                Escolha uma opção:
                1 - Carro
                2 - Cliente
                3 - Venda
                4 - Sair""");

		char opcao = '0';
		do {
			try {
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
					case '1':     // Clientes
						CRUDClientes.menu();
						break;
					case '2':     // Carros
						CRUDCarros.menu();
						break;
					case '3':     // Compras
						CRUDVendas.menu();
						break;
					case '4':     // Sair
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
					}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while(opcao != '4');
	}
}
