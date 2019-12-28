/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kie.uslugisieciowe.sentanalysisservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Agata
 */
@Singleton
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    Map<String, Movie> moviesMap;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        moviesMap = new HashMap<>();
        Movie m1 = new Movie("Pulp Fiction", "Quentin Tarantino");
        moviesMap.put(m1.getId(), m1);

    }

    /**
     * Retrieves representation of an instance of
     * pl.kie.uslugisieciowe.sentanalysisservice.GenericResource
     *
     * @return an instance of java.lang.String
     */
//    @GET
//    @Path("movies")
//    //@Produces("application/json")
//     @Produces(MediaType.APPLICATION_JSON)
//    public String getMovie() throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(moviesMap.values());
//    
    @GET
    @Path("movie/{movie_id}")
    //@Produces("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovie(@PathParam("movie_id") String movieId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(moviesMap.values());

    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
