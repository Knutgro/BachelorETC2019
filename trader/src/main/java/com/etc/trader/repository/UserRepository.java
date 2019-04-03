package com.etc.trader.repository;

import com.etc.trader.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findOneByUsername(String username);


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesById(Long id);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByUsername(String login);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByEmail(String email);

    Page<User> findAllByUsernameNot(Pageable pageable, String username);

    Optional<User> findAllByCompany_id(Long id);
}
