package com.example.pokemon.unite.api.pokemon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pokemon")
@RequiredArgsConstructor
public class PokemonInfoController {

    @PostMapping
    public void createPokemon(){

    }
}
