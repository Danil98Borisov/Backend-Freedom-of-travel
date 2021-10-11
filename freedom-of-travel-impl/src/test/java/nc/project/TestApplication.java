package nc.project;

import lombok.RequiredArgsConstructor;
import nc.project.implement.TestDataLoaderImpl;
import nc.project.jpa.repository.*;
import nc.project.service.HotelPreviewService;
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
    private final ApartmentTypeRepository apartmentTypeRepository;
    private final HotelUserRepository hotelUserRepository;

    @Bean
    public HotelPreviewService hotelPreviewService() {
        return new HotelPreviewService(hotelRepository, imageHotelRepository);
    }

    @Bean
    public TestDataLoaderImpl testDataLoader() {
        return new TestDataLoaderImpl(hotelRepository, apartmentTypeRepository, apartmentRepository, imageApartmentRepository,
                imageHotelRepository, reservationRepository,
                userRepository, roleRepository, hotelUserRepository, new BCryptPasswordEncoder());
    }

}