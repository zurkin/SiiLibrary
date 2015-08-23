package pl.sii.library.util;

public enum ConfigurationProperties {
	RENT_DURATION("rentDuration");

	private String name;
	
	ConfigurationProperties(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
