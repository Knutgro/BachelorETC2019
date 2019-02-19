package etc.trader.Server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

/**
 * An authority (a security role) used by Spring Security.
 */

@Getter @Setter @EqualsAndHashCode @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;

}