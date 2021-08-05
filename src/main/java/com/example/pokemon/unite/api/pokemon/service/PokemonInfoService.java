package com.example.pokemon.unite.api.pokemon.service;

import com.example.pokemon.unite.api.pokemon.domain.Pokemon;
import com.example.pokemon.unite.api.pokemon.dto.PokemonCreationReqDto;
import com.example.pokemon.unite.api.pokemon.repository.PokemonRepository;
import com.example.pokemon.unite.api.pokemon.repository.PreGenPokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PokemonInfoService {

    private final PokemonRepository pokemonRepository;
    private final PreGenPokemonRepository preGenPokemonRepository;

    @Transactional
    public void createPokemon(PokemonCreationReqDto reqDto){
        final Pokemon pokemon = reqDto.toPokemon();
        this.preGenPokemonRepository.saveAll(pokemon.getPreGenPokemonList());
        this.pokemonRepository.save(reqDto.toPokemon());
    }
}
