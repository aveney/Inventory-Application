package com.InventoryApplication.InventoryAuditApplication.loading;

import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.entities.User;
import com.InventoryApplication.InventoryAuditApplication.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadingUsers {

    private static final Logger log = LoggerFactory.getLogger(LoadingUsers.class);

    List<Entry> entryList = new ArrayList<>();

    @Bean
    CommandLineRunner initUserDB(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("Adriaan Veney", entryList)));
            log.info("Preloading " + repository.save(new User("Kyle Smith", entryList)));
        };
    }
}
