package uk.ac.gcal.groupthree.domain.model;
/*
ebenezergraham created on 4/26/19
*/

public class LinkDetails {
	private String link;
	private String projectId;
	
	public LinkDetails(String link, String projectId) {
		this.link = link;
		this.projectId = projectId;
	}
	
	public LinkDetails() {
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getProjectId() {
		return projectId;
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Override
	public String toString() {
		return "LinkDetails{" +
				"link='" + link + '\'' +
				", projectId='" + projectId + '\'' +
				'}';
	}
}
