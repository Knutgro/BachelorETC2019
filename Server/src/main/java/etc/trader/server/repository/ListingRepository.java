package etc.trader.server.repository;

import etc.trader.server.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListingRepository extends JpaRepository<Listing, Long> {


    @Override
    Optional<Listing> findById(Long id);
}
