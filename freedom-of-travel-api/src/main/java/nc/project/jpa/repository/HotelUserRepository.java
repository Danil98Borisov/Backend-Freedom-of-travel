package nc.project.jpa.repository;

import nc.project.jpa.entity.HotelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {

}
