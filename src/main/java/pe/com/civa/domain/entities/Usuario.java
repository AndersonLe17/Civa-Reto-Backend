package pe.com.civa.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import pe.com.civa.domain.enums.Estado;
import pe.com.civa.domain.enums.Perfil;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "usuarios")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id", nullable = false)
    private Long usuId;

    @Column(name = "usu_nom", nullable = false, unique = true, length = 100)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String usuNom;

    @Column(name = "usu_con", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String usuCon;

    @Column(name = "usu_nom_com", nullable = false, length = 200)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String usuNomCom;

    @Enumerated(EnumType.STRING)
    @Column(name = "usu_per", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Perfil usuPer;

    @Enumerated(EnumType.STRING)
    @Column(name = "usu_est", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Estado usuEst;


}
