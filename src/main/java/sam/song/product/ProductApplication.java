package sam.song.product;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "sam.song.product.adapter.out")
@EnableJpaAuditing
public class ProductApplication {


  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }

}
