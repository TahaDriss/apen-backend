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

import interfaces.CarreServiceRemote;
import interfaces.ImageServiceRemote;
import persistence.Carre;
import persistence.Image;

@Path("/Carres")
@Stateless
public class CarreResource {

	@EJB
	CarreServiceRemote ps;
	
	@EJB
	ImageServiceRemote is;

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
	public Response findById(@QueryParam("idcarre") int idcarre) {
		try {
			return Response.status(Status.OK).entity(ps.getById(idcarre)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Carre p) {
		try {

			return Response.status(Status.OK).entity(ps.add(p)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Carre p) {
		Carre c = ps.getById(p.getId());
		c.setContenue(p.getContenue());
		c.setTitre(p.getTitre());
		ps.update(c);
		return Response.status(Status.ACCEPTED).entity(p).build();
	}
	
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(Carre p) {
		
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Carre Rmoved").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Response deleteId(@QueryParam("idCarre") int idCarre) {
		Carre p = ps.getById(idCarre);
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Carre Rmoved").build();
	}
	
	@PUT
	@Path("/affecter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response affecterImageToCarre(@QueryParam("idImage") int idImage ,
			                                  @QueryParam("idCarre") int idCarre) {
	Carre p =	ps.getById(idCarre);
	if (p.getImage() != null) {
		Image i2 = is.getById(p.getImage().getId());
		i2.setCarre(null);
		is.update(i2);
	}
	
	Image i = is.getById(idImage);
	p.setImage(i);
	i.setCarre(p);
	
	is.update(i);
	ps.update(p);
	System.out.println("////////////////////"+0);

	
		return Response.status(Status.OK).build();
	}
	
	
	

}
