package com.example.pokemon.unite.api.pokemon.domain.id;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Builder
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PokemonStatId implements Serializable {

    @Column(name = "POKEMON_ID")
    private Long pokemonId;

    @Column(name = "LVL")
    private Integer level;
}
