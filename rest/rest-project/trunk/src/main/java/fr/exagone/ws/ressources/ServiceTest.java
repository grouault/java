package fr.exagone.ws.ressources;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fr.exagone.entities.TitreMusique;
/*
 * Urls : 
 * http://websystique.com/springmvc/spring-mvc-4-angularjs-example/
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
 * 
 */
@Path("ServiceTest")
@Component
public class ServiceTest {
	
	/*
	 * http://localhost:8080/rest-project/api/ServiceTest/testStatusCree
	*/
	@GET
	@Path("/testStatusCree")
	@Produces({ "application/javascript", MediaType.APPLICATION_JSON })
	public ResponseEntity<TitreMusique> testStatusCree() {
		
		TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphousnique");
		monTitreMusique.ajouteArtiste("Artiste1");
		monTitreMusique.setIdentifiant(1);
		
		try {
			
			/*
			final URI uri = new URI("TitreMusique/1");
			return Response.status(Status.CREATED).location(uri).entity(monTitreMusique).build();
			*/
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Status", String.valueOf(HttpStatus.CREATED));
			httpHeaders.add("Content-Type", "application/json");
			ResponseEntity<TitreMusique> response = new ResponseEntity<TitreMusique>(monTitreMusique, httpHeaders, HttpStatus.CREATED);
			return response;
			
		} catch (Exception e) {
			/*
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			*/
			monTitreMusique = null;
			return new ResponseEntity<TitreMusique>(monTitreMusique, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * http://localhost:8080/rest-project/api/ServiceTest/testResponseStatutCree
	 */
	@GET
	@Path("/testResponseStatutCree")
	@Produces({ "application/javascript", MediaType.APPLICATION_JSON })
	public Response testResponseStatutCree() {
		
		TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique1", 218F);
		monTitreMusique.setStyle("MetalSymphousnique");
		monTitreMusique.ajouteArtiste("Artiste1");
		monTitreMusique.setIdentifiant(1);
		
		try {
			
			final URI uri = new URI("TitreMusique/1");
			return Response.status(Status.CREATED).location(uri).entity(monTitreMusique).build();
			
			/*
			return new ResponseEntity<TitreMusique>(monTitreMusique, HttpStatus.CREATED);
			*/
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			
			/*
			monTitreMusique = null;
			return new ResponseEntity<TitreMusique>(monTitreMusique, HttpStatus.INTERNAL_SERVER_ERROR);
			*/
		}
	}
	
}
