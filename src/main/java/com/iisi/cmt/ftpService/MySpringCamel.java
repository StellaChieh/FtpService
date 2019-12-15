package com.iisi.cmt.ftpService;

import java.util.Arrays;

import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MySpringCamel implements Notified {

	private boolean printSpecificBean = false;
	private boolean printBean = false;

	private static ApplicationContext ctx;
	
	private static Logger logger= LoggerFactory.getLogger(MySpringCamel.class);
	
	@Override
	public void start() {
		ctx = SpringApplication.run(MySpringCamel.class);
		logger.info("Camel System have started.");
	}
	
	@Override
	public void stop() {
		logger.info("Camel System stopping...");
		((ConfigurableApplicationContext) ctx).close();
		logger.info("Camel System have stopped.");
		ctx = null;
	}
	
	@Override
	public void restart() {
		stop();
		start();
	}
	

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			if(printSpecificBean) {
				System.out.println(ctx.containsBean("Main"));
			}
			if(printBean) {
				System.out.println("====Let's inspect the beans provided by Spring Boot===");
				
				String[] beanNames = ctx.getBeanDefinitionNames();
				Arrays.sort(beanNames);
				for(String beanName : beanNames) {
					System.out.println(beanName);
				}
				CamelContext camel = ctx.getBean(CamelContext.class);
//				System.out.println(camel.getReloadStrategy());
			}
		};
	}
	
	public static void main(String[] args) {
		
//		ctx = SpringApplication.run(Main.class);
//	
//		SpringCamelContext camel = ctx.getBean(SpringCamelContext.class);
//		try {
//			camel.stop();
//			
//			// Change camel route monitor folder 
//			camel.setReloadStrategy(new FileWatcherReloadStrategy("config/camel"));
//			Thread.sleep(5000);
//			
//			// In order for Camel to stay up, we need to keep our main thread blocked.
//			CamelSpringBootApplicationController controller = (CamelSpringBootApplicationController)ctx.getBean("applicationController");
//			controller.run();
//			camel.start();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
   
}
