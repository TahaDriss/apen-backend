package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.ibm.watson.developer_cloud.assistant.v1.model.WorkspaceExport.Status;

import interfaces.ImageServiceLocal;
import persistence.Image;

@Path("/files")
public class RestFiles {
	
	@EJB
	ImageServiceLocal is;
	
	   @POST
	    @Path("/upload")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    public Response uploadFile(MultipartFormDataInput input , @QueryParam("idImage") int idImage) throws IOException {
		   Image i = is.getById(idImage);
	        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
	  
	        // Get file data to save
	        List<InputPart> inputParts = uploadForm.get("attachment");
	        for (InputPart inputPart : inputParts) {
	            try {
	  
	                MultivaluedMap<String, String> header = inputPart.getHeaders();
	                String fileName = getFileName(header);
	              
	                // convert the uploaded file to inputstream
	                InputStream inputStream = inputPart.getBody(InputStream.class,
	                        null);
	                
	  
	                
	                byte[] bytes = IOUtils.toByteArray(inputStream);
	                double s = bytes.length/(1024);
	                System.out.println("*********** Size Of the File  " + s + " Ko");

	                if (s > 300) {
		                System.out.println("*********** length limit ");

		                is.delete(i);
		                return Response.status(403)/*.entity(" Size Of the File  " + s + " Mo")
		                        */.build();
		             
						
					}

	 
	              
	        String path = System.getProperty("user.home") + File.separator + "Documents\\coreui-free-angular-admin-template-master\\"
	        		+ "src\\assets\\uploads";
	        System.out.println(path);
	        File customDir = new File(path);
	         
	        if (!customDir.exists()) {
	        	System.out.println("in directory");
	            customDir.mkdir();
	            }
	        System.out.println("before update");
	        i.setRelativePath("assets/uploads/"+ fileName);
            
	                fileName = customDir.getCanonicalPath()  + File.separator + fileName;
	                writeFile(bytes, fileName);
	                i.setAbsolutePath(fileName);
	                System.out.println("Image updated");
	                is.update(i);
	               
	                   
	                return Response.status(200)/*.entity("Uploaded file name : " + fileName)*/
	                        .build();
	  
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }
	  
	    private String getFileName(MultivaluedMap<String, String> header) {
	  
	        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
	  
	        for (String filename : contentDisposition) {
	            if ((filename.trim().startsWith("filename"))) {
	  
	                String[] name = filename.split("=");
	  
	                String finalFileName = name[1].trim().replaceAll("\"", "");
	                return finalFileName;
	            }
	        }
	        return "unknown";
	    }
	  
	    // Utility method
	    private void writeFile(byte[] content, String filename) throws IOException {
	        File file = new File(filename);
	        System.out.println("*********************  "+ file.length());
	        
	    System.out.println("----->"+filename);
	        if (!file.exists()) {
	    	    System.out.println("********** IN EXIST");

	            file.createNewFile();
	    	    System.out.println("********** after create");


	        }
	        FileOutputStream fop = new FileOutputStream(file);
    	    System.out.println("********** fop");

	        fop.write(content);
    	    System.out.println("********** write");

	        fop.flush();
	        fop.close();
	    }
	    
	    @GET
	    @Path("/delete")
	    public Response downloadFile(@QueryParam("idImage") int idImage) {
	    	
	    	try {
	    		Image i = is.getById(idImage);
		    	String path = i.getAbsolutePath();
		    	 //absolute file name with path
		        File file = new File(path);
		        if(file.delete()){
		            System.out.println(path+" File deleted");
		            return Response.status(200)/*.entity("Uploaded file name : " + fileName)*/
	                        .build();
		        }else
		        	{
		        	System.out.println("File "+path+" doesn't exists");
		        	return Response.status(400).build();
		        	}
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(400).build();
			}
	        
	    }

}
