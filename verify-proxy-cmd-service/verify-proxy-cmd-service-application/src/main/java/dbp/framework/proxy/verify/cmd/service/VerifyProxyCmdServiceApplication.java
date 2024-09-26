package dbp.framework.proxy.verify.cmd.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = { 
		"dbp.framework.proxy.verify.cmd.service"})
public class VerifyProxyCmdServiceApplication {
	public static void main(String[] args) {
        SpringApplication.run(VerifyProxyCmdServiceApplication.class,args);
    }
}