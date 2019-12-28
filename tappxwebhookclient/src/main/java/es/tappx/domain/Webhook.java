package es.tappx.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;


public class Webhook implements Serializable{
	
	private static final long serialversionUID = 129348938L; 
	
    private String idwh;
	
	//-->Callback url
    private String url;
    
    //-->Entity Name
    private String entityName;
   
    //-->Entity Type
    private String eventType;
    
    

	public String getIdwh() {
		return idwh;
	}

	public void setIdwh(String idwh) {
		this.idwh = idwh;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Override
    public String toString() {
		return "id:= " + idwh +  " url:= " + url + " entityName:= " +  entityName +  " eventType:= " + eventType;
		
	}
    
    
    
	

}
