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
import interfaces.SliderServiceRemote;
import persistence.Image;
import persistence.Slider;

@Path("/sliders")
@Stateless
public class SliderResource {

	@EJB
	SliderServiceRemote ps;
	
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
	public Response findById(@QueryParam("idslider") int idslider) {
		try {
			return Response.status(Status.OK).entity(ps.getById(idslider)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Slider p) {
		try {

			return Response.status(Status.OK).entity(ps.add(p)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Slider p) {
		ps.update(p);
		return Response.status(Status.ACCEPTED).entity(p).build();
	}
	
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(Slider p) {
		
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Slider Rmoved").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Response deleteId(@QueryParam("idslider") int idSlider) {
		Slider p = ps.getById(idSlider);
		ps.delete(p);
		return Response.status(Status.ACCEPTED).entity("Slider Rmoved").build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("image")
	public Response addImageSlider(@QueryParam("idImage") int idImage, @QueryParam("idSlider")  int idSlider) {
		
		Slider s = ps.getById(idSlider);
		Image i = is.getById(idImage);
		/*s.getImages().add(i);
		ps.update(s);*/
		i.setSlider(s);
		is.update(i);
		return Response.status(Status.OK).build();
	}
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("image")
	public Response removeImageSlider(@QueryParam("idImage") int idImage) {
		
		Image i = is.getById(idImage);
		
		i.setSlider(null);
		is.update(i);
		return Response.status(Status.OK).build();
	}
	
	
	
	

}
