package fr.univtln.mcg;

import fr.univtln.mcg.material.Material;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("materials")
public class MaterialServices extends GenericServices<Material> {

}
