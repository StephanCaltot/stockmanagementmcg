package fr.univtln.mcg.business;

import fr.univtln.mcg.material.pedagogic.Board;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
 */

/**
 * Business class for the Boards.
 * Provides way to do the basic CRUD operations
 * on the Board class.
 */

@Stateless
public class BoardManagerBean extends GenericManagerBean<Board> {

    public BoardManagerBean(){
        super();
    }

    public Board findBoardById(int pId){
        return crudService.find(Board.class, pId);
    }

    public List<Board> findAllBoards(){
        return (List<Board>) crudService.findWithNamedQuery(Board.GET_ALL);
    }
}
