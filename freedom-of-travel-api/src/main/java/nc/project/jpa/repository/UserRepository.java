package nc.project.jpa.repository;

import nc.project.jpa.entity.Reservation;
import nc.project.jpa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmail(String email);

    User findUserById(Long id);

    @Query(value = "select * from users where email = :email ", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);

    User findByVerificationCode(String code);

    @Query(value = "select * from users " +
            "where id not in (select user_id from user_roles where role_id = 3)", nativeQuery = true)
    List<User> findNonAdmins();

    @Query(value = "select * from users", nativeQuery = true)
    List<User> findAllPaginated(Pageable pageable);
}
