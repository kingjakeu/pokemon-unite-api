package com.example.pokemon.unite.api.pokemon.dto;

import com.example.pokemon.unite.api.pokemon.domain.PreGenPokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreGenPokemonReqDto {
    private String name;
    private Integer generation;
    private Integer evolutionLevel;

    public PreGenPokemon toPreGenPokemon(){
        return PreGenPokemon.builder()
                .name(this.name)
                .generation(this.generation)
                .evolutionLevel(this.evolutionLevel)
                .build();
    }
}
