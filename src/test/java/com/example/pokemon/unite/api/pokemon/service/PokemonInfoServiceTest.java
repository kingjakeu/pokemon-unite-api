package com.example.pokemon.unite.api.pokemon.service;

import com.example.pokemon.unite.api.pokemon.constant.AttackType;
import com.example.pokemon.unite.api.pokemon.constant.DamageType;
import com.example.pokemon.unite.api.pokemon.constant.RoleStyle;
import com.example.pokemon.unite.api.pokemon.dto.PokemonCreationReqDto;
import com.example.pokemon.unite.api.pokemon.dto.PreGenPokemonReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PokemonInfoServiceTest {

    @Autowired
    PokemonInfoService pokemonInfoService;

    @Test
    void createPokemon() {
        List<PreGenPokemonReqDto> reqDtos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            reqDtos.add(PreGenPokemonReqDto.builder()
                    .name("p" + i)
                    .evolutionLevel(i+5)
                    .generation(i)
                    .build());
        }
        this.pokemonInfoService.createPokemon(
                PokemonCreationReqDto.builder()
                        .name("pikachu")
                        .attackType(AttackType.RANGED)
                        .damageType(DamageType.SPECIAL)
                        .roleStyle(RoleStyle.ATTACKER)
                        .preGenPokemons(reqDtos)
                        .build()
        );
    }
}