package com.example.pokemon.unite.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class JsoupUtil {
    public static Document doGetDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
