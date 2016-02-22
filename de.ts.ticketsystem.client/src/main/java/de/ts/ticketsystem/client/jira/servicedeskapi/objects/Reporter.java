package de.ts.ticketsystem.client.jira.servicedeskapi.objects;

import java.net.URI;

public class Reporter {

	private URI self;
	
	private String name;
	private String key;
	private String emailAddress;
	private String displayName;
	private boolean active;
	private String timeZone;

	private AvatarUrl avatarUrls;

	public Reporter() {
		
	}


	@Override
	public String toString() {
		return "Reporter [self=" + self + ", name=" + name + ", key=" + key + ", emailAddress=" + emailAddress
				+ ", displayName=" + displayName + ", active=" + active + ", timeZone=" + timeZone + ", avatarUrls="
				+ avatarUrls + "]";
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public AvatarUrl getAvatarUrls() {
		return avatarUrls;
	}

	public void setAvatarUrls(AvatarUrl avatarUrls) {
		this.avatarUrls = avatarUrls;
	}

	public URI getSelf() {
		return self;
	}

	public void setSelf(URI self) {
		this.self = self;
	}

}
