package com.example.pokemon.unite.api.pokemon.dto;

import com.example.pokemon.unite.api.pokemon.constant.AttackType;
import com.example.pokemon.unite.api.pokemon.constant.DamageType;
import com.example.pokemon.unite.api.pokemon.constant.RoleStyle;
import com.example.pokemon.unite.api.pokemon.domain.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonCreationReqDto {

    private String name;
    private AttackType attackType;
    private DamageType damageType;
    private RoleStyle roleStyle;
    private List<PreGenPokemonReqDto> preGenPokemons;

    public Pokemon toPokemon(){
        return Pokemon.builder()
                .name(this.name)
                .attackType(this.attackType)
                .damageType(this.damageType)
                .roleStyle(this.roleStyle)
                .preGenPokemonList(
                        this.preGenPokemons.stream()
                        .map(PreGenPokemonReqDto::toPreGenPokemon)
                        .collect(Collectors.toList())
                ).build();
    }
}

