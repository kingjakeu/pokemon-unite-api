package com.example.pokemon.unite.api.pokemon.domain.id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PokemonStatId implements Serializable {

    @Column(name = "POKEMON_ID")
    private Long pokemonId;

    @Column(name = "LVL")
    private Integer level;
}
