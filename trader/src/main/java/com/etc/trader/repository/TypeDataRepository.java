package com.etc.trader.repository;

import com.etc.trader.model.TypeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDataRepository extends JpaRepository<TypeData, Long> {

}
