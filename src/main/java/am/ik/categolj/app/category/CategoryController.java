package am.ik.categolj.app.category;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;

import am.ik.categolj.app.common.component.PagerComponent;
import am.ik.categolj.domain.common.consts.Const;
import am.ik.categolj.domain.common.consts.LogId;
import am.ik.categolj.domain.common.util.CategoryUtils;
import am.ik.categolj.domain.common.util.CommonUtils;
import am.ik.categolj.domain.model.Category;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.service.entry.EntryService;
import am.ik.yalf.logger.Logger;

@Controller
public class CategoryController {

	private static final Logger logger = Logger
			.getLogger(CategoryController.class);

	@Inject
	protected EntryService entryService;

	@Inject
	protected PagerComponent pager;

	@RequestMapping(value = "/category/**", params = Const.LEGACY)
	public String view(HttpServletRequest request, Model model) {
		return page(request, Const.START_PAGE, model);
	}

	@RequestMapping(value = "/category/**")
	public String redirectForView(HttpServletRequest request, Model model,
			RedirectAttributes attributes) throws UnsupportedEncodingException {
		return redirectForPage(request, Const.START_PAGE, model, attributes);
	}

	@RequestMapping(value = "/page/{page}/category/**", params = Const.LEGACY)
	public String page(HttpServletRequest request, @PathVariable int page,
			Model model) {
		String path = ((String) request
				.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE))
				.replace("/category", "").replace("/page/" + page, "");
		logger.debug(LogId.DCTGL001, path);

		List<Category> categories = CategoryUtils
				.populateCategoriesFromPath(path);
		List<Entry> entries = entryService.getCategorizedEntriesByPage(
				categories, page, Const.VIEW_COUNT);

		int totalPage = CommonUtils.calcTotalPage(entryService
				.getCategorizeEntryCount(categories));
		String appendPath = Const.CATEGORY_PATH
				+ CategoryUtils.categoryPathString(categories);
		List<String> pagerLink = pager.createPaginationLinks(totalPage, page,
				entries, appendPath);

		model.addAttribute(entries);
		model.addAttribute(Const.PAGER_ATTR, pagerLink);
		model.addAttribute(Const.CATEGORY_LINK_ATTR,
				CategoryUtils.categoryBreadCrumb(categories));
		return "category/view";
	}

	@RequestMapping("/page/{page}/category/**")
	public String redirectForPage(HttpServletRequest request,
			@PathVariable int page, Model model, RedirectAttributes attributes)
			throws UnsupportedEncodingException {
		String path = ((String) request
				.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE))
				.replace("/category", "").replace("/page/" + page, "");
		logger.debug(LogId.DCTGL001, path);

		List<Category> categories = CategoryUtils
				.populateCategoriesFromPath(path);
		Collection<String> category = Collections2.transform(categories,
				new Function<Category, String>() {
					@Override
					public String apply(Category input) {
						return input.getName();
					}
				});

		String categoryString = Joiner.on(Const.CATEGORY_DELIM).join(category);
		attributes.addAttribute("category", categoryString);
		attributes.addAttribute("page", page - 1);
		attributes.addAttribute("size", Const.REST_VIEW_COUNT);
		return "redirect:/#/categories/{category}/entries?page={page}&size={size}";
	}

	@RequestMapping(value = "/all", params = Const.LEGACY)
	public String all(Model model) {
		Set<String> linkSet = entryService.getAllCategoryLinkSet();
		model.addAttribute(Const.CATEGORY_LINK_SET_ATTR, linkSet);
		return "category/all";
	}

	@RequestMapping("/all")
	public String redirectForAll(Model model) {
		return "redirect:/#/categories";
	}

	@RequestMapping("/category.json")
	public @ResponseBody
	List<Map<String, String>> allJson(@RequestParam("term") String term) {
		List<String> paths = entryService.getAllCategoryPath(term);
		List<Map<String, String>> response = new ArrayList<Map<String, String>>();
		for (String path : paths) {
			response.add(Collections.singletonMap("label", path));
		}
		return response;
	}
}
