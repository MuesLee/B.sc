package de.ts.ticketsystem.client.jira.objects;

import java.net.URI;

import com.google.gson.annotations.SerializedName;

public class AvatarUrl {

	@SerializedName("48x48")
	private URI size48x48;
	@SerializedName("24x24")
	private URI size24x24;
	@SerializedName("16x16")
	private URI size16x16;
	@SerializedName("32x32")
	private URI size32x32;
	
	public AvatarUrl() {
	}
	

	@Override
	public String toString() {
		return "AvatarUrl [size48x48=" + size48x48 + ", size24x24=" + size24x24 + ", size16x16=" + size16x16
				+ ", size32x32=" + size32x32 + "]";
	}
	
}
