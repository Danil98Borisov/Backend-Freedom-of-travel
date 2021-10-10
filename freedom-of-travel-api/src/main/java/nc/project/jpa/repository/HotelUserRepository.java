package nc.project.jpa.repository;

import nc.project.jpa.entity.HotelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {

    @Query(value = "select * From hotel_user hu, hotel h, users u" +
            "        where h.id=hu.hotel_id" +
            "        and u.id=hu.managed_by_user" +
            "        and  u.email=:email", nativeQuery = true)
    Iterable<HotelUser> findHotelManagerByUser(@Param("email") String email);


    @Query(value = "select * From hotel_user hu, hotel h, users u" +
            "        where h.id=hu.hotel_id" +
            "        and u.id=hu.managed_by_user", nativeQuery = true)
    Iterable<HotelUser> findAllHotelManagerByUser();

    @Query(value = "select * From hotel_user hu, hotel h, users u" +
            "        where h.id=hu.hotel_id" +
            "        and u.id=hu.managed_by_user" +
            "        and h.id =:id", nativeQuery = true)
    Iterable<HotelUser> findUserEditHotel(@Param("id") Integer id);



}
