package am.ik.categolj.app.entry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import am.ik.categolj.app.common.component.PagerComponent;
import am.ik.categolj.domain.common.consts.Const;
import am.ik.categolj.domain.common.util.CommonUtils;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.service.entry.EntrySearchService;

@Controller
public class SearchController {

	protected static final Logger logger = LoggerFactory
			.getLogger(SearchController.class);

	private static final String QUERY_KEY = "q";

	@Inject
	protected EntrySearchService entrySearchService;

	@Inject
	protected PagerComponent pager;

	@RequestMapping(value = "/search/**", params = Const.LEGACY)
	public String view(@RequestParam(QUERY_KEY) String query, Model model) {
		return page(query, Const.START_PAGE, model);
	}

	@RequestMapping(value = "/search/**")
	public String redirectForView(@RequestParam(QUERY_KEY) String query,
			Model model, RedirectAttributes attributes) {
		attributes.addAttribute("q", query);
		return "redirect:/#/entries?q={q}";
	}

	@RequestMapping(value = "/page/{page}/search/**", params = Const.LEGACY)
	public String page(@RequestParam(QUERY_KEY) String query,
			@PathVariable int page, Model model) {
		Set<String> keywords = entrySearchService.splitQuery(query);
		List<Entry> entries = entrySearchService
				.getKeywordSearchedEntriesByPage(keywords, page,
						Const.VIEW_COUNT);
		int count = entrySearchService.getKeywordSearchedEntryCount(keywords);
		int totalPage = CommonUtils.calcTotalPage(count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(QUERY_KEY, query);
		List<String> pagerLink = pager.createPaginationLinks(totalPage, page,
				entries, Const.SEARCH_PATH, params);
		model.addAttribute(entries);
		model.addAttribute(Const.PAGER_ATTR, pagerLink);
		return "home";
	}

	@RequestMapping(value = "/page/{page}/search/**")
	public String redirectForPage(@RequestParam(QUERY_KEY) String query,
			@PathVariable int page, Model model, RedirectAttributes attributes) {
		Set<String> keywords = entrySearchService.splitQuery(query);
		List<Entry> entries = entrySearchService
				.getKeywordSearchedEntriesByPage(keywords, page,
						Const.VIEW_COUNT);
		int count = entrySearchService.getKeywordSearchedEntryCount(keywords);
		int totalPage = CommonUtils.calcTotalPage(count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(QUERY_KEY, query);
		List<String> pagerLink = pager.createPaginationLinks(totalPage, page,
				entries, Const.SEARCH_PATH, params);
		model.addAttribute(entries);
		model.addAttribute(Const.PAGER_ATTR, pagerLink);
		attributes.addAttribute("q", query);
		attributes.addAttribute("page", page);
		attributes.addAttribute("size", Const.REST_VIEW_COUNT);
		return "redirect:/#/entries?q={q}&page={page}&size={size}";
	}
}
