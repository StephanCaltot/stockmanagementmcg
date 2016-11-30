package fr.univtln.mcg;


import fr.univtln.mcg.material.pedagogic.Educational;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("educational")
public class EducationalServices extends GenericServices<Educational> {

}
