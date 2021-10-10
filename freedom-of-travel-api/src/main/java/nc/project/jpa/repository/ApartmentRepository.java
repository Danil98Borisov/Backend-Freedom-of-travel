package nc.project.jpa.repository;

import nc.project.jpa.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "SELECT * FROM apartment apart, hotel h WHERE h.id = apart.hotel_id and h.id=:id", nativeQuery = true)
    List<Apartment> getApartmentInHotel(@Param("id") Long id);

    @Query(value = "select * from apartment where id = :id ", nativeQuery = true)
    Apartment findApartmentById(@Param("id") Long id);

    @Query(value = "select * from apartment where apartment_type_id = :id ", nativeQuery = true)
    Apartment findApartmentByApartmentTypeId(@Param("id") Long id);
}