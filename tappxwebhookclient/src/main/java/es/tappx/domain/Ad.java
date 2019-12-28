package es.tappx.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity(name = "ad")
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="index", nullable=false)
    private Long index;  						//-->id Advertisement
    
    @Column(name="name")
	private String name;						//-->name Advertisement
    
    @Column(name="desc")
    private String desc;
    
	@Column(name="so")
    private String so;							//-->so Advertisement
	
    ///@OneToMany(fetch = FetchType.LAZY, mappedBy = "ad", cascade = CascadeType.ALL)
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_ad", referencedColumnName="index")
	private Set<AdSize> sizes = new HashSet<>();

	public Ad() {
		
	}
	
    
    public Long getIndex() {
		return index;
	}


	public void setIndex(Long id) {
		this.index = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSo() {
		return so;
	}


	public void setSo(String so) {
		this.so = so;
	}
	
	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
	

	public Set<AdSize> getSizesList() {
		return sizes;
	}

	public void setSizesList(Set<AdSize> sizes) {
		this.sizes = sizes;
	}
	
	public void addSize(AdSize size) {
		//size.setAdvertisement(this);
		sizes.add(size);
    	
    }
	
    public void removeSize(AdSize size) {
    	//size.setAdvertisement(null);
    	sizes.remove(size);
    }
	
	
	
    @Override
   	public String toString() {
   		return "id:= " + index  + " name:= " +  name + " desc:= " +  desc + " so:= " + so + " " + sizes.toString();
    }

}
