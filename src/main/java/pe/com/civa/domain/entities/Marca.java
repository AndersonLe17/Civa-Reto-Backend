package pe.com.civa.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "marcas")
public class Marca {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mar_id", nullable = false)
    private Long marId;

    @Column(name = "mar_nom", nullable = false, unique = true, length = 25)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String marNom;

    @OneToMany(mappedBy = "busMar")
    private List<Bus> marBuses;

}
