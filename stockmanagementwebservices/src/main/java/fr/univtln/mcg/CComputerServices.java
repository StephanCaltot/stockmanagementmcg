package fr.univtln.mcg;

import fr.univtln.mcg.material.technologic.CComputer;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("computer")
public class CComputerServices extends AGenericServices<CComputer> {

}
