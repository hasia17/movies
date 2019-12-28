/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kie.uslugisieciowe.sentanalysisservice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author jacek
 */
@Path("generic")
@Singleton
public class SentAnalysisService {

    @Context
    private UriInfo context;
    // tu trzymamy listę słów o pozytywnym wydźwięku
    private List<String> wordsPositive;
    // tu trzymamy listę słów o negatywnym wydźwięku
    private List<String> wordsNegative;
    // kolejcja przechowująca obiekty filmów
    // klucz to id filmu, wartość to konkretny obiekt
    private Map<String, Movie> moviesMap;
    
    
    /**
     * Creates a new instance of SentAnalysisService
     */
    public SentAnalysisService() {
        // lista naszych pozytywnych słów
        wordsPositive = Arrays.asList("dobry fajny super fantastyczny niezły świetny genialny dobra fajna super fantastyczna niezła świetna genialna dobre fajne fantastyczne niezłe świetne genialne".split(" "));
        // lista naszych negatywnych słów
        wordsNegative = Arrays.asList("słaby kiepski beznadziejny zły paskudny brzydki słaba kiepska beznadziejna zła paskudna brzydka słabe kiepskie beznadziejne złe paskudne brzydkie".split(" "));
        // baza przykładowych filmów
        // id: pulpfiction
        moviesMap = new HashMap<>();
        Movie m1 = new Movie("Pulp Fiction", "Quentin Tarantino");
        moviesMap.put(m1.getId(), m1);
        // id: thegodfather
        Movie m2 = new Movie("The Godfather", "Francis Ford Copolla");
        moviesMap.put(m2.getId(), m2);
        // id: fightclub
        Movie m3 = new Movie("Fight Club", "David Fincher");
        moviesMap.put(m3.getId(), m3);
        // id: it
        Movie m4 = new Movie("It", "Andy Muschietti");
        moviesMap.put(m4.getId(), m4);
    }
   
    
}
