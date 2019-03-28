package etc.trader.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "listing")
public class Listing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "price")
    private Long price;

    @Column(name = "position")
    private Long position;

    @Column(name = "date_created")
    private Instant dateCreated;

    @Column(name = "date_expired")
    private Instant dateExpired;

    @OneToOne
    @JoinColumn(unique = true)
    private Vehicle vehicle;

    @ManyToOne
    @JsonIgnoreProperties("listings")
    private Company company;
}