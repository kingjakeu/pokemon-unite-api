package com.example.pokemon.unite.api.crawl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PokemonDataCrawlServiceTest {

    @Autowired
    PokemonDataCrawlService pokemonDataCrawlService;

    @Test
    void getAllPokemonInfo() throws IOException {
        pokemonDataCrawlService.getAllPokemonInfo();
    }
}