package com.example.pokemon.unite.api.pokemon.repository;

import com.example.pokemon.unite.api.pokemon.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Pokemon findPokemonByName(String name);
}
