package am.ik.categolj.api.entry;

import java.util.Date;
import java.util.List;

import am.ik.categolj.api.category.CategoryResponseElement;
import am.ik.categolj.api.user.UserResponse;

public class EntryResponse {
	private Long entryId;
	private String title;
	private String contents;
	private List<CategoryResponseElement> category;
	private Date createdAt;
	private UserResponse createdBy;
	private Date updatedAt;
	private UserResponse updatedBy;

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public List<CategoryResponseElement> getCategory() {
		return category;
	}

	public void setCategory(List<CategoryResponseElement> category) {
		this.category = category;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public UserResponse getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserResponse createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserResponse getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserResponse updatedBy) {
		this.updatedBy = updatedBy;
	}

}
