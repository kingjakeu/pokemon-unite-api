package com.example.pokemon.unite.api.pokemon.repository;

import com.example.pokemon.unite.api.pokemon.domain.PokemonStat;
import com.example.pokemon.unite.api.pokemon.domain.id.PokemonStatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonStatRepository extends JpaRepository<PokemonStat, PokemonStatId> {
}
