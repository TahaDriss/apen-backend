package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Page implements Serializable {
	
	   
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		private String nom;
		
		@OneToMany(mappedBy="page", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	    
		List<Paragraphe> paragraphes;
		
		@OneToMany(mappedBy="page", fetch= FetchType.EAGER)
	    @JsonManagedReference
		List<Carre> carres;
		

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


		public List<Paragraphe> getParagraphes() {
			return paragraphes;
		}


		public void setParagraphes(List<Paragraphe> paragraphes) {
			this.paragraphes = paragraphes;
		}


		public List<Carre> getCarres() {
			return carres;
		}


		public void setCarres(List<Carre> carres) {
			this.carres = carres;
		}




}
