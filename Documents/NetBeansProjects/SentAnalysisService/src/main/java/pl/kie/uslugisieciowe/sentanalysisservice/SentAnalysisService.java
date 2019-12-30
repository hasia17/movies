/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kie.uslugisieciowe.sentanalysisservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @GET
    @Path("movie/{movie_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovie(@PathParam("movie_id") String movieId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this.moviesMap.get(movieId));
    }

//    @POST
//    @Path("movieReview/{movie_id}")
//    @Consumes(MediaType.TEXT_PLAIN)
//    public Response.ResponseBuilder sendReview(String content, @PathParam("movie_id") String movieId) throws IOException, URISyntaxException {
//        ObjectMapper mapper = new ObjectMapper();
//        int positiveWords=0;
//        int negativeWords=0;
//        for(String word: wordsPositive) {
//            if (content.contains(word))
//                positiveWords=positiveWords+1;
//        }
//        for(String word: wordsNegative) {
//            if (content.contains(word))
//                positiveWords=positiveWords+1;
//        }
//        if (positiveWords>negativeWords) {
//            Movie movie = moviesMap.get(movieId);
//            int newCounter = movie.getPositiveSentimentCoutner();
//            newCounter = newCounter+1;
//          //  Response res = Response.created(new URI("POSITIVE"));
//            return Response.created(new URI("POSITIVE"));
//            
//            
//        }
//        else if (positiveWords<negativeWords) {
//            Movie movie = moviesMap.get(movieId);
//            int newCounter = movie.getNegativeSentimentCounter();
//            newCounter = newCounter+1;
//            return Response.created(new URI("NEGATIVE"));
//        }
//        else{
//            return Response.created(new URI("NEUTRAL")); 
//        }
//     }



    
    @POST
    @Path("movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendReview(String content) throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        // deserializujemy obiekt wypożyczenia
        Movie rsd = mapper.readValue(content, Movie.class);
      moviesMap.put(rsd.getId(), rsd);
    return Response.created(new URI("generic/rents/" + rsd.getId())).build();
    }
        
        
        
        
        
        
//        int positiveWords=0;
//        int negativeWords=0;
//        for(String word: wordsPositive) {
//            if (content.contains(word))
//                positiveWords=positiveWords+1;
//        }
//        for(String word: wordsNegative) {
//            if (content.contains(word))
//                positiveWords=positiveWords+1;
//        }
//        if (positiveWords>negativeWords) {
//            Movie movie = moviesMap.get(movieId);
//            int newCounter = movie.getPositiveSentimentCoutner();
//            newCounter = newCounter+1;
//          //  Response res = Response.created(new URI("POSITIVE"));
//            return Response.created(new URI("POSITIVE"));
//            
//            
//        }
//        else if (positiveWords<negativeWords) {
//            Movie movie = moviesMap.get(movieId);
//            int newCounter = movie.getNegativeSentimentCounter();
//            newCounter = newCounter+1;
//            return Response.created(new URI("NEGATIVE"));
//        }
//        else{
//            return Response.created(new URI("NEUTRAL")); 
//        }
    // }













}















