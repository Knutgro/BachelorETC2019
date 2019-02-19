package etc.trader.Server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import etc.trader.Server.config.Constants;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import javax.validation.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.time.Instant;

/**
 * A user.
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Size(max = 50)
    @NotNull
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Email
    @NotNull
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

   /* @Size(min = 2, max = 6)
    @Column(name = "lang_key", length = 6)
    private String langKey;
   */

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    /*
    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;
    */
    /*
    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;
    */
    /*
    @Column(name = "reset_date")
    private Instant resetDate = null;
    */
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})

    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

}