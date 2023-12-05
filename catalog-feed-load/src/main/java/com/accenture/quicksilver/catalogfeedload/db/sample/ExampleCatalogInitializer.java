package com.accenture.quicksilver.catalogfeedload.db.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.quicksilver.catalogfeedload.db.CatalogEntry;
import com.accenture.quicksilver.catalogfeedload.db.CatalogRepository;

@Configuration
public class ExampleCatalogInitializer {
    private static final Logger log = LoggerFactory.getLogger(ExampleCatalogInitializer.class);

  @Bean
  CommandLineRunner initDatabase(CatalogRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new CatalogEntry("Apple", .75)));
      log.info("Preloading " + repository.save(new CatalogEntry( "Banana", 10.00)));
    };
  }
    
}
