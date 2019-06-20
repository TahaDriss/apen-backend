package persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Carre implements Serializable {
	
	   
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		private Integer id;
		
		private String titre;
		private String contenue;
		
		@OneToOne(mappedBy="carre")
		private Image image;
		
		@ManyToOne()
	    @JsonBackReference
		private Page page;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitre() {
			return titre;
		}

		public void setTitre(String titre) {
			this.titre = titre;
		}

		public String getContenue() {
			return contenue;
		}

		public void setContenue(String contenue) {
			this.contenue = contenue;
		}

	
		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
		}
		//@JsonIgnore
		public Page getPage() {
			return page;
		}

		public void setPage(Page page) {
			this.page = page;
		}
		
		

}
