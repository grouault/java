package com.book.chapter4.french;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MonMapperDExceptions implements ExceptionMapper<ExceptionDeStatut> {

	@Override
	public Response toResponse(final ExceptionDeStatut exception) {
		return Response.status(exception.getStatut()).type(exception.getContentType()).entity(exception).build();
	}

}
