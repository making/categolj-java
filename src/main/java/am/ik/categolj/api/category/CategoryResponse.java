package am.ik.categolj.api.category;

import java.util.List;

public class CategoryResponse {
	private List<CategoryResponseElement> category;

	public CategoryResponse() {
	}

	public CategoryResponse(List<CategoryResponseElement> category) {
		this.category = category;
	}

	public void setCategory(List<CategoryResponseElement> category) {
		this.category = category;
	}

	public List<CategoryResponseElement> getCategory() {
		return category;
	}
}
