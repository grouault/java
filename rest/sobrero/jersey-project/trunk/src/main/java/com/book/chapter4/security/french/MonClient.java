package com.book.chapter4.security.french;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.book.chapter4.french.TitreMusique;

public class MonClient {

	public static void main(final String[] args) {
		final String page = "http://localhost:8080/jersey-service/rest/STitreMusique/1";
		final String nomDUtilisateur = "nom1";
		final String motDePasse = "motdepasse1";
		
		final HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(nomDUtilisateur, motDePasse);
		
		final Client client = ClientBuilder.newClient();
		client.register(feature);
		final Response response = client.target(page).request().get();
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			final TitreMusique titreMusique = response.readEntity(TitreMusique.class);
			System.out.println(titreMusique);
		} else {
			System.err.println("Code retour " + response.getStatus());
		}
	}
}