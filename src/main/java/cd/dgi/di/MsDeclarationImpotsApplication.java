package cd.dgi.di;

import cd.dgi.di.configurations.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableConfigurationProperties(RsaKeys.class)
@EnableTransactionManagement
@SpringBootApplication
public class MsDeclarationImpotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDeclarationImpotsApplication.class, args);
	}

}
