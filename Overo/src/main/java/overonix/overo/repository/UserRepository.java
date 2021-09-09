package overonix.overo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import overonix.overo.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
