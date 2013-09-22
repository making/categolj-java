package am.ik.categolj.api.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.api.entry.EntryHelper;
import am.ik.categolj.api.entry.EntryResponse;
import am.ik.categolj.domain.common.consts.Const;
import am.ik.categolj.domain.common.util.CategoryUtils;
import am.ik.categolj.domain.model.Category;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.service.entry.EntryService;

@Controller
@RequestMapping("categories")
public class CategoryRestController {
	@Inject
	protected EntryService entryService;

	@Inject
	protected CategoryHelper categoryHelper;

	@Inject
	protected EntryHelper entryHelper;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<CategoryResponse> getCategories() {
		List<Entry> entries = entryService.getEntriesByPage(Const.START_PAGE,
				Integer.MAX_VALUE);
		return categoryHelper.convert(entries);
	}

	@RequestMapping(value = "{category}/entries", method = RequestMethod.GET)
	@ResponseBody
	public Page<EntryResponse> getEntries(
			@PageableDefault(size = Const.REST_VIEW_COUNT) Pageable pageable,
			@PathVariable("category") String category) {
		List<Category> categories = CategoryUtils.populateCategoriesFromPath(
				category, Const.CATEGORY_DELIM);
		List<Entry> entries = entryService.getCategorizedEntriesByPage(
				categories, pageable.getPageNumber() + 1,
				pageable.getPageSize());
		int total = entryService.getCategorizeEntryCount(categories);
		// bean convert
		List<EntryResponse> responses = new ArrayList<>();
		for (Entry entry : entries) {
			EntryResponse response = entryHelper.convertEntry(entry);
			responses.add(response);
		}
		Page<EntryResponse> page = new PageImpl<>(responses, pageable, total);
		return page;
	}
}
