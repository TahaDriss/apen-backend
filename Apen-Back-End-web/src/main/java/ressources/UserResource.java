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

import interfaces.UserServiceRemote;
import persistence.User;

@Path("/users")
@Stateless
public class UserResource {
	
	@EJB
	UserServiceRemote ps;
	
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password ) {
		try {
			 User u =	ps.getForLogin(username, password);
			if (u == null) {
				return Response.status(Status.NOT_FOUND).entity(u).build();
			}else {
				return Response.status(Status.OK).entity(u).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}
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
	public Response findById(@QueryParam("idUser") int idcarre) {
		try {
			return Response.status(Status.OK).entity(ps.getById(idcarre)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(User p) {
		try {

			return Response.status(Status.OK).entity(ps.add(p)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update( @QueryParam("userName") String userName , @QueryParam("oldPass") String oldPass, @QueryParam("newPass") String newPass) {
	
		int id = 0;
		id = ps.update(userName,  oldPass ,  newPass);
		if(id == 0) {
			return Response.status(Status.BAD_REQUEST).build();

		}else {
			return Response.status(Status.ACCEPTED).entity(id).build();

		}
	}
	
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(User p) {
		
		ps.remove(p);
		return Response.status(Status.ACCEPTED).entity("Carre Rmoved").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Response deleteId(@QueryParam("idUser") int idUser) {
		User p = ps.getById(idUser);
		ps.remove(p);
		return Response.status(Status.ACCEPTED).entity("Carre Rmoved").build();
	}
	

}
