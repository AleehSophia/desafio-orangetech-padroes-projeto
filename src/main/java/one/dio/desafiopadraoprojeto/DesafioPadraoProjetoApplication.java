package one.dio.desafiopadraoprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot gerado pelo spring initializr.
 * Dependências selecionadas:
 * Spring Data JPA
 * Spring Web
 * H2 Database
 * OpenFeign
 *
 * @author aleehsophia
 */

@EnableFeignClients
@SpringBootApplication
public class DesafioPadraoProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPadraoProjetoApplication.class, args);
	}
}