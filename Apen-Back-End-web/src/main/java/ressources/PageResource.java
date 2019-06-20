package ressources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import interfaces.PageServiceRemote;
import persistence.Page;
import utilities.Secured;

@Path("/pages")
@Stateless
public class PageResource {

	@EJB
	PageServiceRemote ps;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response findAll() {
		try {
			return Response.status(Status.OK).entity(ps.getAll()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}
	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@QueryParam("idpage") int idPage) {
		try {
			return Response.status(Status.OK).entity(ps.getById(idPage)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}
	@GET
	@Path("/nom")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByName(@QueryParam("nom") String nom) {
		try {
			Page p =ps.getAll().stream().filter(x -> x.getNom().equals(nom)).findFirst().get();
			return Response.status(Status.OK).entity(p).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Page p) {
		try {

			return Response.status(Status.OK).entity(ps.add(p)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Page p) {
		ps.update(p);
		return Response.status(Status.ACCEPTED).entity(p).build();
	}
	
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(Page p) {
		
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Page Rmoved").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Response deleteId(@QueryParam("idpage") int idpage) {
		Page p = ps.getById(idpage);
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Page Rmoved").build();
	}
	
	

}
