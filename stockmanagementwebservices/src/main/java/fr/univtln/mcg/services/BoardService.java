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

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("boards")
public class BoardService extends GenericService<Board> {

    @Inject
    BoardManagerBean boardManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Board board = boardManagerBean.findBoardById(id);
        return Response.ok().entity(board).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Board> boards = boardManagerBean.findAllBoards();
        //String json = mapper.writerWithType(new TypeReference<List<Computer>>() {}).writeValueAsString(computers);
        return Response.ok().entity(boards).build();
    }
}
