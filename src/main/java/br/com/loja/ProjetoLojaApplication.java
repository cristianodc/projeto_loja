package br.com.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "br.com.loja.model")
@ComponentScan(basePackages = {"br.*"})/*Varro todo o projeto em busca de anotações*/
@EnableJpaRepositories(basePackages = {"br.com.loja.repository"})
@EnableTransactionManagement
public class ProjetoLojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLojaApplication.class, args);
	}

}
