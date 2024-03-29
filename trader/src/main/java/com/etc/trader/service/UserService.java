package com.etc.trader.service;

import com.etc.trader.model.Role;
import com.etc.trader.model.RoleName;
import com.etc.trader.model.User;
import com.etc.trader.repository.CompanyRepository;
import com.etc.trader.repository.RoleRepository;
import com.etc.trader.repository.UserRepository;
import com.etc.trader.rest.errors.EmailAlreadyUsedException;
import com.etc.trader.rest.errors.LoginAlreadyUsedException;
import com.etc.trader.security.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Getter
@Setter
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final CompanyRepository companyRepository;

    /**
     * Funksjonen legger ny bruker til i databasen med
     * de data som er sendt fra REST controller.
     * Error haandtering for allerede brukt brukernavn eller email.
     *
     * @param userDTO: DTO for bruker objekt.
     * @param password: Passord som skal krypteres.
     */
    public void registerUser(UserDTO userDTO, String password) {
        userRepository.findOneByUsername(userDTO.getUsername().toLowerCase()).ifPresent(existingUser -> {

            throw new LoginAlreadyUsedException();

        });
        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {

            throw new EmailAlreadyUsedException();

        });

        User newUser = new User();
        // Krypterer passord
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(userDTO.getUsername().toLowerCase());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail().toLowerCase());
        newUser.setActivated(true);
        // Bruker blir medlem av bedrift
        newUser.setCompany(companyRepository.findCompanyById(userDTO.getCompany_id()));

        Set<String> strRoles = userDTO.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        newUser.setRoles(roles);
        // Ny bruker lagres til databasen
        userRepository.save(newUser);
    }


    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        User updatedUser = new User();
        updatedUser.setUsername(userDTO.getUsername().toLowerCase());
        updatedUser.setFirstName(userDTO.getFirstName());
        updatedUser.setLastName(userDTO.getLastName());
        updatedUser.setImage(userDTO.getImage());
        updatedUser.setFilename(userDTO.getFilename());

        return Optional.of(userRepository
                .findByUsername(userDTO.getUsername()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    user.setUsername(userDTO.getUsername().toLowerCase());
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setEmail(userDTO.getEmail().toLowerCase());
                    user.setImage(userDTO.getImage());
                    user.setFilename(userDTO.getFilename());

                    /*Set<Authority> managedAuthorities = user.getAuthorities();
                    managedAuthorities.clear();
                    userDTO.getAuthorities().stream()
                            .map(authorityRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(managedAuthorities::add);
                            */
                    return user;
                })
                .map(UserDTO::new);


    }

    public User save(User updatedUser) {
        User oldUser = userRepository.findOneById(updatedUser.getId());

        if (updatedUser.getFirstName() != null) {
            oldUser.setFirstName(updatedUser.getFirstName());
        }

        if (updatedUser.getLastName() != null) {
            oldUser.setLastName(updatedUser.getLastName());
        }

        if (updatedUser.getImage() != null) {
            oldUser.setImage(updatedUser.getImage());
        }

        if (updatedUser.getFilename() != null) {
            oldUser.setFilename(updatedUser.getFilename());
        }

        return userRepository.save(oldUser);
    }

    /**
     * Delete the company by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByUsernameNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByUsername(String username) {
        return userRepository.findOneWithRolesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithRoles(Long id) {
        return userRepository.findOneWithRolesById(id);
    }

    /*
    @Transactional(readOnly = true)
    public Optional<User> getUserWithRoles() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
    */

    public List<RoleName> getRoles() {
        return roleRepository.findAll().stream().map(Role::getName).collect(Collectors.toList());
    }

}
