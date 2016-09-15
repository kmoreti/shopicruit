package shopicruit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Products products = null;
            int i = 1;
            double total = 0;
            do {
                products = restTemplate.getForObject("http://shopicruit.myshopify.com/products.json?page=" + i, Products.class);
                for (Product product : products.getProducts()) {
                    if (product.getProduct_type().toLowerCase().equals("clock") || product.getProduct_type().toLowerCase().equals("watch")) {
                        for (Variant v : product.getVariants()) {
                            total = total + v.getPrice();
                        }
                        log.info(product.toString());
                    }
                }
                i++;
            } while (products.getProducts().length > 0);
            String sTotal = new DecimalFormat("###,###.##").format(total);
            log.info("It will cost $" + sTotal + " to buy all the clocks and watches");
        };
    }

}
