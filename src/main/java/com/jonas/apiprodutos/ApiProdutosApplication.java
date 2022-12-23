package com.jonas.apiprodutos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Api de Produtos", version = "3.0.1"),
		servers = {
				@Server(url = "http://localhost:8080")
		}

)
public class ApiProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProdutosApplication.class, args);
	}

}
