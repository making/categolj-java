package am.ik.categolj.service.pager;

import java.util.ArrayList;
import java.util.List;

import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Category;
import am.ik.categolj.entity.Entry;
import am.ik.categolj.service.PagerService;
import am.ik.categolj.util.CategoryUtils;

public class DefaultPagerService implements PagerService {

    @Override
    public List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Category> categories, List<Entry> entries) {
        List<String> links = new ArrayList<String>();
        int len = (totalPage > 1) ? totalPage + 1 : 0;
        for (int i = 1; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            if (i == currentPage) {
                sb.append("<strong>");
                sb.append(i);
                sb.append("</strong>");
            } else {
                sb.append("<a href=\"");
                sb.append(Const.CONTEXT_PATH);
                sb.append(Const.PAGE_PATH);
                sb.append("/");
                sb.append(i);
                if (categories != null) {
                    sb.append(Const.CATEGORY_PATH);
                    sb.append(CategoryUtils.categoryPathString(categories));
                }
                sb.append("/\">");
                sb.append(i);
                sb.append("</a>");
            }
            links.add(sb.toString());
        }
        return links;
    }

}
