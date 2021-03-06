package de.ts.ticketsystem.client.jira;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Links;

public class ResultPage<T> {

	private String expand;
	private Integer size;
	private Integer start;
	private Integer limit;
	private boolean isLastPage;
	@SerializedName("_links")
	private Links links;
	
	private List<T> values;
	
	public ResultPage() {
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public List<T> getValues() {
		return values;
	}

	public void setValues(List<T> values) {
		this.values = values;
	}
	
	
}
