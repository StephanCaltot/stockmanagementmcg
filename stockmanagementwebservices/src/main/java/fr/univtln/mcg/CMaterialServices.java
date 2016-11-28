package fr.univtln.mcg;

import fr.univtln.mcg.utils.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("material")
public class CMaterialServices extends AGenericServices<CMaterial> {

    public CMaterialServices(CrudService<CMaterial> mCrudService) {
        super(mCrudService);
    }
}
