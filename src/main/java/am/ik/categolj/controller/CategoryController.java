package am.ik.categolj.controller;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import am.ik.categolj.common.Const;
import am.ik.categolj.common.LogId;
import am.ik.categolj.entity.Category;
import am.ik.categolj.entity.Entry;
import am.ik.categolj.service.EntryService;
import am.ik.categolj.service.PagerService;
import am.ik.categolj.util.CategoryUtils;
import am.ik.categolj.util.CommonUtils;
import am.ik.yalf.logger.Logger;

@Controller
public class CategoryController {

    private static final Logger logger = Logger
            .getLogger(CategoryController.class);

    @Inject
    protected EntryService entryService;

    @Inject
    protected PagerService pager;

    @RequestMapping("/category/**")
    public String view(HttpServletRequest request, Model model) {
        return page(request, Const.START_PAGE, model);
    }

    @RequestMapping("/page/{page}/category/**")
    public String page(HttpServletRequest request, @PathVariable int page,
            Model model) {
        String path = (String) request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        logger.debug(LogId.DCTGL001, path);

        List<Category> categories = CategoryUtils
                .populateCategoriesFromPath(path);
        List<Entry> entries = entryService.getCategorizedEntriesByPage(
                categories, page, Const.VIEW_COUNT);

        int totalPage = CommonUtils.calcTotalPage(entryService
                .getCategorizeEntryCount(categories));
        List<String> pagerLink = pager.createPaginationLinks(totalPage, page,
                categories, entries);

        model.addAttribute(entries);
        model.addAttribute(Const.PAGER_ATTR, pagerLink);
        model.addAttribute(Const.CATEGORY_LINK_ATTR,
                CategoryUtils.categoryLinkString(categories));
        return "category/view";
    }

    @RequestMapping("/all")
    public String all(Model model) {
        Set<String> linkSet = entryService.getAllCategoryLinkSet();
        model.addAttribute(Const.CATEGORY_LINK_SET_ATTR, linkSet);
        return "category/all";
    }
}
