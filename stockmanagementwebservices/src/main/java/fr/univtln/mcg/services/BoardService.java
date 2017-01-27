package fr.univtln.mcg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.business.BoardManagerBean;
import fr.univtln.mcg.material.pedagogic.Board;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
 */

/*
 * REST Service at url /boards
 * Provides ways to create/update an entity (see superclass)
 * and also get a board by his id or get all the boards
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("boards")
public class BoardService extends GenericService<Board> {

    @Inject
    BoardManagerBean boardManagerBean;

    /**
     * @param id
     * @return Response with the Board loaded from the
     * database with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Board board = boardManagerBean.findBoardById(id);
        return Response.ok().entity(board).build();
    }

    /**
     * @return Response with the json including all the
     * boards in the database
     * @throws JsonProcessingException
     *
     */

    @GET
    public Response all() throws JsonProcessingException {
        List<Board> boards = boardManagerBean.findAllBoards();
        return Response.ok().entity(boards).build();
    }
}
