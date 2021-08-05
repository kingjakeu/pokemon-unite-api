package com.example.pokemon.unite.api.pokemon.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "PRE_GEN_POKEMON")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreGenPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GEN")
    private Integer generation;

    @Column(name = "EVL_LVL")
    private Integer evolutionLevel;
}
