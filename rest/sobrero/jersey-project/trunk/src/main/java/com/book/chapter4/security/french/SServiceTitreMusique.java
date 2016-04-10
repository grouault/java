package com.book.chapter4.security.french;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.book.chapter4.french.ExceptionDeStatut;
import com.book.chapter4.french.ServiceTitreMusique;
import com.book.chapter4.french.TitreMusique;

@Path("/STitreMusique")
@DenyAll
@DeclareRoles({"utilisateur", "admin"})
public class SServiceTitreMusique extends ServiceTitreMusique {
	
	@RolesAllowed({"utilisateur", "admin"})
	public TitreMusique getTitre(final int identifiant) {
		return super.getTitre(identifiant);
	}
	
	@RolesAllowed("admin")
	public TitreMusique postTitre(final TitreMusique titreMusique) {
		return super.postTitre(titreMusique);
	}
	
	@RolesAllowed("admin")
	public Response putTitre(final int identifiant, final TitreMusique titreMusique) {
		return super.putTitre(identifiant, titreMusique);
	}
	
	@RolesAllowed("admin")
	public TitreMusique deleteTitre(final int identifiant, String contentType) throws ExceptionDeStatut {
		return super.deleteTitre(identifiant, contentType);
	}
}