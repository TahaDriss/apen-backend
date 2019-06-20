package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Slider implements Serializable {
	
	   
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		private Integer id;
		
		private String nom;
		
		@OneToMany(mappedBy="slider", fetch = FetchType.EAGER)
		private List<Image> images;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public List<Image> getImages() {
			return images;
		}

		public void setImages(List<Image> images) {
			this.images = images;
		}
		
		

}
