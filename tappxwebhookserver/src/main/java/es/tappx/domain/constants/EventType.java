package es.tappx.domain.constants;

public enum EventType {
	
	PREINSERT("pre-insert"),
	POSTINSERT("post-insert"),
	PREUPDATE("pre-update"),
	POSTUPDATE("post-update"),
	PREDELETE("pre-delete"),
	POSTDELETE("post-delete");
	
	private String eventType;
	
	private EventType(String _eventType) {
		eventType = _eventType;
	}
	
	public String getValue() {
		return eventType;
	}
	
}
