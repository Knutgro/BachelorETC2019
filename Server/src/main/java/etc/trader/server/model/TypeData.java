package etc.trader.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import etc.trader.server.model.enumeration.Engine;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "type_data")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TypeData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body_style")
    private String bodyStyle;

    @Column(name = "trim_level")
    private String trimLevel;

    @Column(name = "list_price")
    private Long listPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type")
    private Engine engineType;

    @Column(name = "effect")
    private Double effect;

    @Column(name = "jhi_range")
    private Double range;

    @Column(name = "battery_capacity")
    private Double batteryCapacity;

    @Column(name = "co_2")
    private Double co2;

    @Column(name = "nox")
    private Double nox;

    @ManyToOne
    @JsonIgnoreProperties("typeData")
    private Model model;
}