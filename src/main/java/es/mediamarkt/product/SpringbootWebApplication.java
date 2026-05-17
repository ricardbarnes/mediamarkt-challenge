package es.mediamarkt.product;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "es.mediamarkt")
@EnableJpaRepositories(basePackages = "es.mediamarkt")
@RequiredArgsConstructor
public class SpringbootWebApplication {

    private final MockedDataLoader mockedDataLoader;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        mockedDataLoader.loadMockedData();
    }

}
