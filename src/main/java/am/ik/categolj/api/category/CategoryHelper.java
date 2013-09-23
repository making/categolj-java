package am.ik.categolj.api.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import am.ik.categolj.domain.model.Category;
import am.ik.categolj.domain.model.Entry;

@Component
public class CategoryHelper {
	private static final String SEPARATOR = "~";

	public Collection<CategoryResponse> convert(Collection<Entry> entries) {
		// too un-efficient x(
		Set<String> categoryStringSet = new TreeSet<String>();
		for (Entry entry : entries) {
			List<String> buf = new ArrayList<>();
			for (Category category : entry.getCategories()) {
				buf.add(category.getName());
			}
			String categoryString = Joiner.on(SEPARATOR).join(buf);
			categoryStringSet.add(categoryString);
		}

		List<CategoryResponse> responses = new ArrayList<>();
		for (String categoryString : categoryStringSet) {
			Iterable<String> splitted = Splitter.on(SEPARATOR).split(
					categoryString);
			List<String> elements = new ArrayList<>();
			for (String name : splitted) {
				elements.add(name);
			}
			responses.add(new CategoryResponse(elements));
		}
		return responses;
	}
}
