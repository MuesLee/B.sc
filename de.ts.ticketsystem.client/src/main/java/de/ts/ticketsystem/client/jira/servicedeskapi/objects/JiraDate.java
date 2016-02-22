package de.ts.ticketsystem.client.jira.servicedeskapi.objects;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class JiraDate {

@SerializedName("iso8601")
private Date date;

@SerializedName("friendly")
private String friendlyFormattedDate;
private long epochMillis;

public JiraDate() {
}


@Override
public String toString() {
	return "JiraDate [date=" + date + ", friendlyFormattedDate=" + friendlyFormattedDate + ", epochMillis="
			+ epochMillis + "]";
}


public Date getIso8601() {
	return date;
}

public void setIso8601(Date iso8601) {
	this.date = iso8601;
}

public String getFriendly() {
	return friendlyFormattedDate;
}

public void setFriendly(String friendly) {
	this.friendlyFormattedDate = friendly;
}

public long getEpochMillis() {
	return epochMillis;
}

public void setEpochMillis(long epochMillis) {
	this.epochMillis = epochMillis;
}


	
}
