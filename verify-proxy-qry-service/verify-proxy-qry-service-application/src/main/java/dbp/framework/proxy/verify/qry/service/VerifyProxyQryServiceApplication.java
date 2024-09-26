package dbp.framework.proxy.verify.qry.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {
		"dbp.framework.proxy.verify.qry"
})
public class VerifyProxyQryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerifyProxyQryServiceApplication.class, args);

	}
}
