package am.ik.categolj.service.pager;

import static am.ik.categolj.util.CommonUtils.postAppendIfNotEndsWithSlash;
import static am.ik.categolj.util.CommonUtils.preAppendIfNotStartsWithSlash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.sf.amateras.functions.utils.StringUtils;
import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Entry;
import am.ik.categolj.service.PagerService;

public class DefaultPagerService implements PagerService {

    @Override
    public List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath) {
        return createPaginationLinks(totalPage, currentPage, entries,
                appendPath, null);
    }

    @Override
    public List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath, Map<String, Object> params) {
        List<String> links = new ArrayList<String>();
        int len = (totalPage > 1) ? totalPage + 1 : 0;
        String queryString = StringUtils.map2queryString(params);
        for (int i = 1; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            if (i == currentPage) {
                sb.append("<strong>");
                sb.append(i);
                sb.append("</strong>");
            } else {
                sb.append("<a href=\"");
                sb.append(preAppendIfNotStartsWithSlash(Const.CONTEXT_ROOT));
                postAppendIfNotEndsWithSlash(sb);
                sb.append(Const.PAGE_PATH);
                postAppendIfNotEndsWithSlash(sb);
                sb.append(i);
                if (appendPath != null && !appendPath.isEmpty()) {
                    sb.append(preAppendIfNotStartsWithSlash(appendPath));
                }
                // if (categories != null) {
                // sb.append(preAppendIfNotStartsWithSlash(Const.CATEGORY_PATH));
                // sb.append(CategoryUtils.categoryPathString(categories));
                // }
                sb.append("/");
                if (params != null) {
                    sb.append("?");
                    sb.append(queryString);
                }
                sb.append("\">");
                sb.append(i);
                sb.append("</a>");
            }
            links.add(sb.toString());
        }
        return links;
    }
}
