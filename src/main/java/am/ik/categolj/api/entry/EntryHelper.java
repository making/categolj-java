package am.ik.categolj.api.entry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import am.ik.categolj.api.category.CategoryResponseElement;
import am.ik.categolj.api.user.UserResponse;
import am.ik.categolj.domain.common.util.MarkdownUtils;
import am.ik.categolj.domain.model.Category;
import am.ik.categolj.domain.model.Entry;

@Component
public class EntryHelper {

	protected EntryResponse convertEntry(Entry entry) {
		EntryResponse response = new EntryResponse();
		response.setEntryId(entry.getId());
		response.setTitle(entry.getTitle());
		if (entry.getContent() != null) {
			response.setContents(MarkdownUtils.markdown(entry.getContent()));
		}
		response.setCreatedAt(entry.getCreatedAt());
		response.setUpdatedAt(entry.getUpdatedAt());
		if (entry.getCategories() != null && !entry.getCategories().isEmpty()) {
			List<CategoryResponseElement> categoryResponses = new ArrayList<>();
			for (Category category : entry.getCategories()) {
				CategoryResponseElement categoryResponse = new CategoryResponseElement();
				categoryResponse.setCategoryId(category.getId());
				categoryResponse.setCategoryName(category.getName());
				categoryResponses.add(categoryResponse);
			}
			response.setCategory(categoryResponses);
		}
		UserResponse userResponse = new UserResponse();
		// FIXME
		userResponse.setUserName("making");
		userResponse.setUserId("making");
		response.setCreatedBy(userResponse);
		response.setUpdatedBy(userResponse);
		return response;
	}
}
