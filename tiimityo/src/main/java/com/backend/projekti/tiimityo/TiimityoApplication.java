package com.backend.projekti.tiimityo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backend.projekti.tiimityo.domain.Manufacturer;
import com.backend.projekti.tiimityo.domain.ManufacturerRepository;
import com.backend.projekti.tiimityo.domain.Product;
import com.backend.projekti.tiimityo.domain.ProductRepository;

@SpringBootApplication
public class TiimityoApplication {

	private static final Logger log = LoggerFactory.getLogger(TiimityoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TiimityoApplication.class, args);
	}

	@Bean
	public CommandLineRunner productDemo(ProductRepository prepository, ManufacturerRepository mrepository) {
		return(args) -> {
			log.info("saving a couple of manufacturers");
			Manufacturer rukka = new Manufacturer("Rukka");
			mrepository.save(rukka);
			Manufacturer pomppa = new Manufacturer("Pomppa");
			mrepository.save(pomppa);
			Manufacturer feelActive = new Manufacturer("Feel Active");
			mrepository.save(feelActive);

			log.info("saving a couple of products");
			prepository.save(new Product("Talvitakki", 53.90, "Vaate", "Violetti", "M", rukka));
			prepository.save(new Product("Sadetakki", 44.90, "Vaate", "Keltainen", "L", pomppa));
			prepository.save(new Product("Neule", 21.99, "Vaate", "Vihreä", "S", feelActive));
		};
	}

}
