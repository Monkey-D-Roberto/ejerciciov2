package com.codigo.ms_sistem_sif.infrastructure.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="familias")
@Getter
@Setter
public class FamiliaEntity {
    @Id
    @Column(name = "cod_familia")
    private Long codFamilia;

    @Column(name = "nom_familia")
    private String nomFamilia;

    @Column(name = "dir_familia")
    private String dirFamilia;

    @Column(name = "cmp_familia")
    private String cmpFamilia;

    @Column(name = "std_familia")
    private Integer stdFamilia;

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


    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL)
    private List<IncidenciaEntity> incidencias;
}
