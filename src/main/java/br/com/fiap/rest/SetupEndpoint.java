package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.DAO;
import br.com.fiap.model.Setup;

@Path("/setups")
@Produces(MediaType.APPLICATION_JSON)
public class SetupEndpoint {

	DAO<Setup> dao = new DAO<Setup>(Setup.class);

	@GET
	public Response index() {
		List<Setup> setups = dao.getAll();
		return Response.status(Response.Status.OK).entity(setups).build();
	}

	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		Setup setup = dao.findById(id);

		if (setup == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.status(Response.Status.OK).entity(setup).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Setup setup) {
		if (setup == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		try {
			dao.save(setup);
			return Response.status(Response.Status.CREATED).entity(setup).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Setup setup) {
		setup.setId(id);
		dao.update(setup);
		return Response.status(Response.Status.OK).entity(setup).build();

	}

	@DELETE
	@Path("{id}")
	public Response destroy(@PathParam("id") Long id, Setup setup) {
		setup.setId(id);
		dao.delete(setup);
		return Response.status(Response.Status.OK).entity(setup).build();
	}

}
