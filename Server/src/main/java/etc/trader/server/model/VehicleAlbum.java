package etc.trader.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_album")
public class VehicleAlbum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ManyToOne
    @JsonIgnoreProperties("vehicleAlbums")
    private Vehicle vehicle;
}