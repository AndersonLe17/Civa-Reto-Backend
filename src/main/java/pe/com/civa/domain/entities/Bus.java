package pe.com.civa.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import pe.com.civa.domain.enums.Estado;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "buses")
public class Bus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id", nullable = false)
    private Long busId;

    @Column(name = "bus_num", nullable = false, unique = true, length = 16)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String busNum;

    @Column(name = "bus_pla", nullable = false, unique = true, length = 6)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String busPla;

    @Column(name = "bus_fec_cre", nullable = false, updatable = false, columnDefinition = "timestamp with time zone")
    @CreationTimestamp
    private LocalDateTime busFecCre;

    @Column(name = "bus_car", nullable = false, length = 512)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String busCar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bus_mar_id", nullable = false)
    private Marca busMar;

    @Enumerated(EnumType.STRING)
    @Column(name = "bus_est", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Estado busEst;

}
