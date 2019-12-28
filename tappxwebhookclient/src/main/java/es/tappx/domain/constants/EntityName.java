package es.tappx.domain.constants;

public enum EntityName {
	
	ADVERTISEMENT("Ad"),
	WEBHOOK("Webhook");
	
	private String entityName;
	
	private EntityName(String _entityName) {
		entityName = _entityName;
	}
	
	public String getValue() {
		return entityName;
	}

}
