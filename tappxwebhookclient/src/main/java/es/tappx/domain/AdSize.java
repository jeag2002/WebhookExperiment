package es.tappx.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "ad_size")
@Table(name = "ad_size")
public class AdSize {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_size", nullable=false)
    private Long id;						//-->id advertisement size
    
    @Column(name="id_ad")
    private Long id_ad;
    
	@Column(name="url")
    private String url;						//-->url asoc
    
    @Column(name="size")
    private String size;					//-->size
   
    
    public Long getId_ad() {
		return id_ad;
	}

	public void setId_ad(Long id_ad) {
		this.id_ad = id_ad;
	}    
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdSize)) return false;
        return id!= null && id.equals(((AdSize) o).getId());
    }
 	
	
    @Override
    public int hashCode() {
        return 31;
    }
	
	
	
	@Override
	public String toString() {
		return "id:= " + id + " url:= " + url + " size:= " + size; 
	}

    
    
	
}
