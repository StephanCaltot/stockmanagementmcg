package fr.univtln.mcg;


import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("person")
public class CPersonServices extends AGenericServices<CPerson> {

    public CPersonServices(CGenericDao<CPerson> mCrudService) {
        super(mCrudService);
    }
}
