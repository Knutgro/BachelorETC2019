package etc.trader.server.repository;

import etc.trader.server.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String>{

    Optional<Authority> findByName(String name);
}