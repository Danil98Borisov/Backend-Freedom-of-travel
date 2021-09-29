package nc.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.service.TestDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class FreedomOfTravelApplication implements CommandLineRunner{

    final private TestDataLoader testDataLoader;

    public static void main(String[] args) {
        SpringApplication.run(FreedomOfTravelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        log.info("Test data loading started");
        testDataLoader.uploadTestData();
        log.info("Test data loaded successfully");
    }
}
