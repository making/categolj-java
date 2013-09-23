package am.ik.categolj.api.category;

import java.util.List;

public class CategoryResponse {
	private List<String> category;

	public CategoryResponse() {
	}

	public CategoryResponse(List<String> category) {
		this.category = category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<String> getCategory() {
		return category;
	}
}
