package com.etc.trader.rest;

import com.etc.trader.model.Company;
import com.etc.trader.model.User;
import com.etc.trader.repository.RoleRepository;
import com.etc.trader.repository.UserRepository;
import com.etc.trader.rest.errors.BadRequestAlertException;
import com.etc.trader.rest.errors.InvalidPasswordException;
import com.etc.trader.rest.util.HeaderUtil;
import com.etc.trader.rest.vm.ManagedUserVM;
import com.etc.trader.service.CustomUserDetails;
import com.etc.trader.service.CustomUserDetailsService;
import com.etc.trader.service.UserDTO;
import com.etc.trader.service.UserService;
import com.etc.trader.service.jwt.JwtProvider;
import com.etc.trader.service.jwt.JwtResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Getter
@Setter
public class AccountResource {


    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    private final UserService userService;


    private final CustomUserDetailsService userDetailsService;

    @Value("${trader.app.header}")
    private String tokenHeader;


    public AccountResource(AuthenticationManager authenticationManager, RoleRepository roleRepository,
                           PasswordEncoder encoder, JwtProvider jwtProvider, UserRepository userRepository,
                           UserService userService, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    /** API for innloggin av bruker.
     * autheticationManager sjekker om passord stemmer overens med brukernavn.
     * Om brukeren har oppgitt korrekt passord bygges det en JWT til brukeren.
     * Om brukeren er autentisert vil front-end motta en JWT med
     * brukerens egen data.
     *
     * @param loginRequest: LoginForm objekt med brukernavn og passord
     * @return JwtRepsonse: Bearer token med bruker data.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Sjekker om brukeren er autentisert
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(new JwtResponse(jwt,customUserDetails));
    }

    /**
     * POST /signup: Registrerer bruker
     * @param managedUserVM: Hjelpe objekt for aa validere passord.
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }

        userService.registerUser(managedUserVM, managedUserVM.getPassword());
    }


    /**
     * GET  /authenticate : check if the user is authenticated, and return its username.
     *
     * @param request the HTTP request
     * @return the username if the user is authenticated
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    /**
     * GET  /account : get the current user.
     *
     * @return the current user
     * @throws RuntimeException 500 (Internal Server Error) if the user couldn't be returned
     */
    @RequestMapping(value = "account", method = RequestMethod.GET)
    public CustomUserDetails getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtProvider.getUserNameFromJwtToken(token);
        return (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        //return user;
    }

    @RequestMapping("updateUser")
    public void saveAccount(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) throws IOException {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtProvider.getUserNameFromJwtToken(token);
        userDTO.setUsername(username);
        userService.updateUser(userDTO);
    }



    @PutMapping("/account")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws URISyntaxException {
        if (user.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        User result = userService.save(user);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, user.getId().toString()))
                .body(result);
    }

    /**
     * DELETE  /user/:id : delete the "id" user.
     *
     * @param id the id of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
