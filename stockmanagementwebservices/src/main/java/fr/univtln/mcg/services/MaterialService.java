package fr.univtln.mcg.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.business.MaterialManagerBean;
import fr.univtln.mcg.material.Material;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("materials")
public class MaterialService extends GenericService<Material> {

    @Inject
    MaterialManagerBean materialManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Material material = materialManagerBean.findMaterialById(id);
        return Response.ok().entity(material).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Material> materials = materialManagerBean.findAllMaterials();
        String json = mapper.writerWithType(new TypeReference<List<Material>>() {}).writeValueAsString(materials);
        return Response.ok().entity(json).build();
    }



}
