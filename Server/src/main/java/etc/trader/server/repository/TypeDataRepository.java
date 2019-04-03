package etc.trader.server.repository;

import etc.trader.server.model.TypeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDataRepository extends JpaRepository<TypeData, Long> {

}
