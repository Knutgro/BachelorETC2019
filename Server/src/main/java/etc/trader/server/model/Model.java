package etc.trader.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Model.
 */
@Entity
@Table(name = "model")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_year")
    private Integer year;

    @OneToMany(mappedBy = "model")
    private Set<Vehicle> vehicles = new HashSet<>();
    @OneToMany(mappedBy = "model")
    private Set<TypeData> typeData = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("models")
    private Brand brand;

}