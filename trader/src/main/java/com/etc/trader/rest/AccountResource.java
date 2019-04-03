package com.etc.trader.rest;

import com.etc.trader.model.Role;
import com.etc.trader.model.RoleName;
import com.etc.trader.model.User;
import com.etc.trader.repository.RoleRepository;
import com.etc.trader.repository.UserRepository;
import com.etc.trader.rest.errors.InternalServerErrorException;
import com.etc.trader.rest.errors.InvalidPasswordException;
import com.etc.trader.rest.vm.ManagedUserVM;
import com.etc.trader.service.CustomUserDetails;
import com.etc.trader.service.CustomUserDetailsService;
import com.etc.trader.service.UserDTO;
import com.etc.trader.service.UserService;
import com.etc.trader.service.jwt.JwtProvider;
import com.etc.trader.service.jwt.JwtResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        userService.registerUser(managedUserVM, managedUserVM.getPassword());
    }


    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
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
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        return user;
    }

    @RequestMapping("updateUser")
    public void saveAccount(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtProvider.getUserNameFromJwtToken(token);
        userDTO.setUsername(username);
        userService.updateUser(userDTO);
    }


    @RequestMapping("")

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}