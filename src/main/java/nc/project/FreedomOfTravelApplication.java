package nc.project;

import lombok.RequiredArgsConstructor;
import nc.project.UploadBaseData.UploadBaseDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@RequiredArgsConstructor
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FreedomOfTravelApplication implements CommandLineRunner{
    final private UploadBaseDataService uploadBaseDataService;

    public static void main(String[] args) {

        SpringApplication.run(FreedomOfTravelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{

        uploadBaseDataService.uploadBaseData();



    }
}
