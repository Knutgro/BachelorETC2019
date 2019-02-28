package etc.trader.Server.repository;

import etc.trader.Server.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListingRepository extends JpaRepository<Listing, Long> {


    @Override
    Optional<Listing> findById(Long id);
}
