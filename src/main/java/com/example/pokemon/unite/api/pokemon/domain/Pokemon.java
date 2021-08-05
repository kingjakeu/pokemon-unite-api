package com.example.pokemon.unite.api.pokemon.domain;

import com.example.pokemon.unite.api.pokemon.constant.AttackType;
import com.example.pokemon.unite.api.pokemon.constant.DamageType;
import com.example.pokemon.unite.api.pokemon.constant.RoleStyle;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "POKEMON")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ATTACK_TYPE")
    @Enumerated(EnumType.STRING)
    private AttackType attackType;

    @Column(name = "DAMAGE_TYPE")
    @Enumerated(EnumType.STRING)
    private DamageType damageType;

    @Column(name = "ROLE_STYLE")
    @Enumerated(EnumType.STRING)
    private RoleStyle roleStyle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "POKEMON_EVL_TREE",
            joinColumns = @JoinColumn(name = "POKEMON_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRE_GEN_POKEMON_ID")
    )
    private List<PreGenPokemon> preGenPokemonList;
}
