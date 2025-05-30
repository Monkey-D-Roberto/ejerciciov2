package com.codigo.ms_sistem_sif.infrastructure.entity;

import com.codigo.ms_sistem_sif.domain.enums.EstadoIncidencia;
import com.codigo.ms_sistem_sif.domain.enums.TipoIncidencia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name="incidencias")
@Getter
@Setter

public class IncidenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_incidencia")
    private Long codIncidencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoIncidencia tipoIncidencia;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;


    @Enumerated(EnumType.STRING)
    private EstadoIncidencia estadoIncidencia;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "std_incidencia")
    private Integer stdIncidencia;

    @Column(name = "usuacrea", length = 254)
    private String usuaCrea;

    @Column(name = "datecreate")
    private Timestamp dateCreate;

    @Column(name = "usuamodif", length = 254)
    private String usuaModif;

    @Column(name = "datemodif")
    private Timestamp dateModif;

    @Column(name = "usuadelet", length = 254)
    private String usuaDelet;

    @Column(name = "datedelet")
    private Timestamp dateDelet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_familia")
    private FamiliaEntity familia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_trabajador")
    private UsuarioEntity trabajador;



}
