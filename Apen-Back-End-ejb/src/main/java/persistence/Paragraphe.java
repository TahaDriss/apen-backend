package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paragraphe implements Serializable {
	
	   
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		private Integer id;
		
		private String titre;
		@Column(length = 2000)
		private String contenue;
		private String link;
		
		@ManyToMany(mappedBy="paragraphes",  fetch = FetchType.EAGER)
		private List<Image> images;
		
		@ManyToOne(/*cascade = CascadeType.ALL*/)
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

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public List<Image> getImages() {
			return images;
		}

		public void setImages(List<Image> images) {
			this.images = images;
		}

		@JsonIgnore

		public Page getPage() {
			return page;
		}

		public void setPage(Page page) {
			this.page = page;
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
			Paragraphe other = (Paragraphe) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}




		

}
