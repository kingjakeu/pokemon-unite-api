package com.example.pokemon.unite.api.pokemon.repository;

import com.example.pokemon.unite.api.pokemon.domain.PreGenPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreGenPokemonRepository extends JpaRepository<PreGenPokemon, Long> {
}
