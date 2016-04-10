package com.book.chapter4.french;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/TitreMusique")
public class ServiceTitreMusique {
	
	@GET
	@Path("{identifiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public TitreMusique getTitre(@PathParam("identifiant") final int identifiant) {
		final TitreMusique monTitreMusique = Factory.getTitre(identifiant);
		return monTitreMusique;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public TitreMusique postTitre(final TitreMusique titreMusique) {
		Factory.enregistrerTitre(titreMusique);
		return titreMusique;
	}
	
	@PUT
	@Path("{identifiant}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putTitre(@PathParam("identifiant") final int identifiant, final TitreMusique titreMusique) {
		final TitreMusique ancienTitreMusique = Factory.getTitre(identifiant);
		if (ancienTitreMusique == null) {
			Factory.enregistrerTitre(titreMusique);
			return Response.status(Status.CREATED).entity(titreMusique).build();
		}
		
		Factory.modifierTitre(titreMusique);
		return Response.status(Status.OK).entity(titreMusique).build();
	}
	
	@DELETE
	@Path("{identifiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public TitreMusique deleteTitre(@PathParam("identifiant") final int identifiant, @HeaderParam("Content-Type") String contentType) throws ExceptionDeStatut {
		final TitreMusique monTitreMusique = Factory.getTitre(identifiant);
		if (monTitreMusique == null) {
			throw new ExceptionDeStatut(Status.NOT_FOUND, contentType, "/TitreMusique/" + identifiant);
		}
		Factory.supprimerTitre(monTitreMusique);
		return monTitreMusique;
	}
}