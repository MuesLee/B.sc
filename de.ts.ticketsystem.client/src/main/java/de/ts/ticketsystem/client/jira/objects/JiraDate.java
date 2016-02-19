package de.ts.ticketsystem.client.jira.objects;

import java.util.Date;

public class JiraDate {

private Date iso8601;
private String friendly;
private long epochMillis;

public JiraDate() {
}



@Override
public String toString() {
	return "JiraDate [iso8601=" + iso8601 + ", friendly=" + friendly + ", epochMillis=" + epochMillis + "]";
}



public Date getIso8601() {
	return iso8601;
}

public void setIso8601(Date iso8601) {
	this.iso8601 = iso8601;
}

public String getFriendly() {
	return friendly;
}

public void setFriendly(String friendly) {
	this.friendly = friendly;
}

public long getEpochMillis() {
	return epochMillis;
}

public void setEpochMillis(long epochMillis) {
	this.epochMillis = epochMillis;
}


	
}
