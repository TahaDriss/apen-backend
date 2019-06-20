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

import interfaces.ImageServiceRemote;
import persistence.Image;

@Path("/images")
@Stateless
public class ImageResource {

	@EJB
	ImageServiceRemote ps;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
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
	public Response findById(@QueryParam("idimage") int idimage) {
		try {
			return Response.status(Status.OK).entity(ps.getById(idimage)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Image p) {
		try {

			return Response.status(Status.OK).entity(ps.add(p)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Image p) {
		ps.update(p);
		return Response.status(Status.ACCEPTED).entity(p).build();
	}
	
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(Image p) {
		
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Image Rmoved").build();
	}
	@DELETE
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response deleteById(int id) {
		Image i = ps.getById(id);
		
		ps.delete(i);
		return Response.status(Status.ACCEPTED).entity("Image Rmoved").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Response deleteId(@QueryParam("idImage") int idImage) {
		Image p = ps.getById(idImage);
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Image Rmoved").build();
	}
	
	

}
