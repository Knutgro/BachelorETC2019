package com.etc.trader.repository;

import com.etc.trader.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);

    @Query(value = "select distinct company from Company company left join fetch company.regions",
        countQuery = "select count(distinct company) from Company company")
    Page<Company> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct company from Company company left join fetch company.regions")
    List<Company> findAllWithEagerRelationships();

    @Query("select company from Company company left join fetch company.regions where company.id =:id")
    Optional<Company> findOneWithEagerRelationships(@Param("id") Long id);

}
