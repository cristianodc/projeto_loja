package br.com.loja_v_ment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "br.com.loja_v_ment.model")
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.loja_v_ment.repository"} )
@EnableTransactionManagement
public class LojaVirtualMentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualMentApplication.class, args);
	}

}
