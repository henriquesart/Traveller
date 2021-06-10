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
import br.com.fiap.model.Hotel;
import br.com.fiap.model.Reserva;

@Path("/reserva")
@Produces(MediaType.APPLICATION_JSON)
public class ReservaEndpoint {

	DAO<Reserva> dao = new DAO<Reserva>(Reserva.class);

	@GET
	public Response index() {
		List<Reserva> setups = dao.getAll();
		return Response.status(Response.Status.OK).entity(setups).build();
	}

	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		Reserva reserva = dao.findById(id);

		if (reserva == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.status(Response.Status.OK).entity(reserva).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Reserva reserva) {
		if (reserva == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		try {
			dao.save(reserva);
			return Response.status(Response.Status.CREATED).entity(reserva).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Reserva reserva) {
		reserva.setId(id);
		dao.update3(reserva);
		return Response.status(Response.Status.OK).entity(reserva).build();

	}

	@DELETE
	@Path("{id}")
	public Response destroy(@PathParam("id") Long id, Reserva reserva) {
		reserva.setId(id);
		dao.delete3(reserva);
		return Response.status(Response.Status.OK).entity(reserva).build();
	}

}
