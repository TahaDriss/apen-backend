package ressources;

import java.util.ArrayList;
import java.util.List;

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
import interfaces.PageServiceRemote;
import interfaces.ParagrapheServiceRemote;
import persistence.Image;
import persistence.Page;
import persistence.Paragraphe;

@Path("/paragraphes")
@Stateless
public class ParagrapheResource {

	@EJB
	PageServiceRemote pageService;
	@EJB
	ParagrapheServiceRemote ps;
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
	public Response findById(@QueryParam("idparagraphe") int idparagraphe) {
		try {
			return Response.status(Status.OK).entity(ps.getById(idparagraphe)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(@QueryParam("idPage") int idPage , Paragraphe p) {
		try {
			Page page = pageService.getById(idPage);
			int id = ps.add(p);
			p.setId(id);
			p.setPage(page);
			page.getParagraphes().add(p);
			pageService.update(page);
			ps.update(p);
			
			return Response.status(Status.OK).entity(p.getPage()).build();
		} catch (Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(p.getPage()).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Paragraphe p) {
		ps.update(p);
		return Response.status(Status.ACCEPTED).entity(p).build();
	}
	
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(Paragraphe p) {
		try {
			ps.delete(p);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();

		}
		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Response deleteId(@QueryParam("idparagraphe") int idParagraphe) {
		try {
			
			Paragraphe p = ps.getById(idParagraphe);
			Page page = pageService.getById(p.getPage().getId());
			page.getParagraphes().remove(p);
			pageService.update(page);
			p.setPage(null);
			ps.update(p);
			ps.delete(p);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Path("/affecter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response affecterImageToParagraphe(@QueryParam("idImage") int idImage ,
			                                  @QueryParam("idParagraphe") int idParagraphe) {
	Paragraphe p =	ps.getById(idParagraphe);
	Image i = is.getById(idImage);
	if (i.getParagraphes() != null && p.getImages() != null ) {
		System.out.println("////////////////////"+1);
		i.getParagraphes().add(p);
		p.getImages().add(i);
		
		
	}else if (i.getParagraphes() != null && p.getImages() == null ) {
		System.out.println("////////////////////"+2);

		List<Image> images = new ArrayList<>();
		images.add(i);
		p.setImages(images);
		i.getParagraphes().add(p);
		
	}else if (i.getParagraphes() == null && p.getImages() != null ) {
		System.out.println("////////////////////"+3);

		List<Paragraphe> paragraphes = new ArrayList<>();
		paragraphes.add(p);
		i.setParagraphes(paragraphes);
		p.getImages().add(i);
		
	}else if (i.getParagraphes() == null && p.getImages() == null) {
		System.out.println("////////////////////"+4);

		List<Image> images = new ArrayList<>();
		List<Paragraphe> paragraphes = new ArrayList<>();
		paragraphes.add(p);
		images.add(i);
		i.setParagraphes(paragraphes);
		p.setImages(images);

		
	}
	is.update(i);
	ps.update(p);
	System.out.println("////////////////////"+0);

	
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/image")
	public Response deleteImageParagraphe(@QueryParam("idImage") int idImage ,
            @QueryParam("idParagraphe") int idParagraphe) {
		Paragraphe p =	ps.getById(idParagraphe);
		Image i = is.getById(idImage);
		i.getParagraphes().remove(p);
		p.getImages().remove(i);
		is.update(i);
		ps.update(p);
		return Response.status(Status.OK).build();
	}
	

}
