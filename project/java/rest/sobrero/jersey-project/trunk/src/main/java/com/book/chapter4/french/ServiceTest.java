package com.book.chapter4.french;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.arnx.jsonic.JSON;

@Path("/ServiceTest")
public class ServiceTest {

	@GET
	public String test() {
		return "test de texte";
	}
		
	@GET
	@Path("/test")
	public String test2() {
		return "test de texte 2";
	}
	
	@GET
	@Path("/testJson")
	@Produces(MediaType.APPLICATION_JSON)
	public String testJson() {
		final TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphonique");
		monTitreMusique.ajouteArtiste("Artiste1");
		monTitreMusique.setIdentifiant(95);
		
		final JSON json = new JSON(2);
		return json.format(monTitreMusique);
	}
	
	@GET
	@Path("/testMultiple")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public TitreMusique testMultiple() {
		final TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphonique");
		monTitreMusique.ajouteArtiste("Artiste1");
		
		return monTitreMusique;
	}
	
	@GET
	@Path("/testObjetJson")
	@Produces(MediaType.APPLICATION_JSON)
	public TitreMusique testObjetJson() {
		final TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphonique");
		monTitreMusique.ajouteArtiste("Artiste1");
		
		return monTitreMusique;
	}
	
	@GET
	@Path("/testObjetJson2")
	@Produces(MediaType.APPLICATION_JSON)
	public AlbumMusical testObjetJson2() {
		final AlbumMusical monAlbumMusical = new AlbumMusical();
		monAlbumMusical.setNom("AlbumMetalSymphonique1");
		monAlbumMusical.setDate(new Date());
		monAlbumMusical.setIdentifiant(1);
		
		final TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphonique");
		monTitreMusique.ajouteArtiste("Artiste1");
		monAlbumMusical.ajouteTitre(monTitreMusique);
		monAlbumMusical.setIdentifiant(2);
		
		return monAlbumMusical;
	}
	
	@GET
	@Path("/testObjetJson3")
	@Produces(MediaType.APPLICATION_JSON)
	public TitreMusique testObjetJson3() {
		final TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphonique");
		monTitreMusique.ajouteArtiste("Artiste1");
		monTitreMusique.setIdentifiant(1);
		
		final AlbumMusical monAlbumMusical = new AlbumMusical();
		monAlbumMusical.setNom("AlbumMetalSymphonique1");
		monAlbumMusical.setDate(new Date());
		monAlbumMusical.setIdentifiant(1);
		monTitreMusique.setAlbum(monAlbumMusical);
		
		return monTitreMusique;
	}
	
	@GET
	@Path("/testStatusCree")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testStatusCree() {
		final TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphonique");
		monTitreMusique.ajouteArtiste("Artiste1");
		monTitreMusique.setIdentifiant(1);
		
		try {
			final URI uri = new URI("TitreMusique/1");
			return Response.status(Status.CREATED).location(uri).entity(monTitreMusique).build();
		} catch (URISyntaxException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/testPathParam/{variable1}/{variable2}")
	public String testPathParam(@PathParam("variable1") String variable1, @PathParam("variable2") int variable2) {
		return variable1 + " - " + variable2;
	}
	
	@GET
	@Path("/testMatrixParam")
	public String testMatrixParam(@MatrixParam("variable1") String variable1, @MatrixParam("variable2") String variable2) {
		return variable1 + " - " + variable2;
	}
	
	@GET
	@Path("/testQueryParam")
	public String testQueryParam(@QueryParam("variable1") String variable1, @QueryParam("variable2") String variable2) {
		return variable1 + " - " + variable2;
	}
	
	@POST
	@Path("/testFormParam")
	public String testFormParam(@FormParam("variable1") String variable1) {
		return variable1;
	}
	
	@POST
	@Path("/testFormParamUpload")
	public String testFormParamUpload(@FormParam("file") File file) {
		return file.getName();
	}
	
	@GET
	@Path("/testHeaderParam")
	public String testHeaderParam(@HeaderParam("Accept") String contentType) {
		return "Le type de contenu demandé est " + contentType;
	}
	
	@GET
	@Path("/testCookieParam")
	public String testCookieParam(@CookieParam("variable1") String variable1) {
		return variable1;
	}
}