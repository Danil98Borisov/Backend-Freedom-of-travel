package nc.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.uploadBaseData.TestDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
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
