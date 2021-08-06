package com.example.pokemon.unite.api.crawl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataCrawlServiceTest {

    @Autowired
    DataCrawlService dataCrawlService;

    @Test
    void getAllPokemonInfo() throws IOException {
        dataCrawlService.getAllPokemonInfo();
    }
}