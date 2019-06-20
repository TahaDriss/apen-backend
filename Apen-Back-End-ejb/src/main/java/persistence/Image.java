package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Image implements Serializable {
	
	   
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		private Integer id;
		
		private String nom;
		private String relativePath;
		private String absolutePath;

		
		@ManyToMany( fetch = FetchType.EAGER)
		private List<Paragraphe> paragraphes;
		
		@ManyToOne(cascade = CascadeType.ALL)
		private Slider slider;
		
		@OneToOne(cascade = CascadeType.ALL)
		private Carre carre;
		
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
	
	
		public String getRelativePath() {
			return relativePath;
		}
		public void setRelativePath(String relativePath) {
			this.relativePath = relativePath;
		}
		public String getAbsolutePath() {
			return absolutePath;
		}
		public void setAbsolutePath(String absolutePath) {
			this.absolutePath = absolutePath;
		}
		@JsonIgnore
		public List<Paragraphe> getParagraphes() {
			return paragraphes;
		}
		public void setParagraphes(List<Paragraphe> paragraphes) {
			this.paragraphes = paragraphes;
		}
		@JsonIgnore
		public Carre getCarre() {
			return carre;
		}
		public void setCarre(Carre carre) {
			this.carre = carre;
		}
		@JsonIgnore

		public Slider getSlider() {
			return slider;
		}
		public void setSlider(Slider slider) {
			this.slider = slider;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Image other = (Image) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		
		

}
