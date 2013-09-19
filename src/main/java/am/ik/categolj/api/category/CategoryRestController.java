package am.ik.categolj.api.category;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.domain.common.consts.Const;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.service.entry.EntryService;

@Controller
@RequestMapping("categories")
public class CategoryRestController {
	@Inject
	protected EntryService entryService;

	@Inject
	protected CategoryHelper categoryHelper;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<CategoryResponse> getCategories() {
		List<Entry> entries = entryService.getEntriesByPage(Const.START_PAGE,
				Integer.MAX_VALUE);
		return categoryHelper.convert(entries);
	}
}
