package com.example.pokemon.unite.api.pokemon.domain;

import com.example.pokemon.unite.api.pokemon.domain.id.PokemonStatId;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@Entity
@Table(name = "POKEMON_STAT")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PokemonStat {

    @EmbeddedId
    private PokemonStatId pokemonStatId;

    @MapsId("pokemonId")
    @ManyToOne
    @JoinColumn(name = "POKEMON_ID")
    private Pokemon pokemon;

    @Column(name = "HEALTH_POINT")
    private Integer healthPoint;

    @Column(name = "ATTACK_DAMAGE")
    private Integer attackDamage;

    @Column(name = "SP_ATTACK_DAMAGE")
    private Integer specialAttackDamage;

    @Column(name = "DEFENSE")
    private Integer defense;

    @Column(name = "SP_DEFENCE")
    private Integer specialDefense;

    @Column(name = "SPEED")
    private Integer speed;
}
