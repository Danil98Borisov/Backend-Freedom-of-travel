/*
package nc.project;

import lombok.RequiredArgsConstructor;
import nc.project.implement.TestDataLoaderImpl;
import nc.project.jpa.repository.*;
import nc.project.service.ApartmentPreviewService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@RequiredArgsConstructor
public class TestApplication {

    private final ApartmentRepository apartmentRepository;
    private final ImageApartmentRepository imageApartmentRepository;
    private final HotelRepository hotelRepository;
    private final ImageHotelRepository imageHotelRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TypeRepository typeRepository;

    @Bean
    public ApartmentPreviewService apartmentPreviewService() {
        return new ApartmentPreviewService(apartmentRepository, imageApartmentRepository);
    }

    @Bean
    public TestDataLoaderImpl testDataLoader() {
        return new TestDataLoaderImpl(hotelRepository, typeRepository, apartmentRepository, imageApartmentRepository,
                imageHotelRepository, reservationRepository,
                userRepository, roleRepository,  new BCryptPasswordEncoder());
    }

}
*/
