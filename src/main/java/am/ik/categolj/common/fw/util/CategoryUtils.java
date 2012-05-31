package am.ik.categolj.common.fw.util;

import static am.ik.categolj.common.fw.util.CommonUtils.postAppendIfNotEndsWithSlash;
import static am.ik.categolj.common.fw.util.CommonUtils.preAppendIfNotStartsWithSlash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import am.ik.categolj.app.common.consts.Const;
import am.ik.categolj.app.common.domain.Category;
import am.ik.categolj.common.fw.listener.RequestUtil;

public class CategoryUtils {

    private static String extractName(Object o) {
        if (o instanceof Category) {
            return ((Category) o).getName();
        }
        return jp.sf.amateras.functions.utils.StringUtils.escapeHtml(o
                .toString());
    }

    public static List<Category> populateCategoriesFromPath(String path) {
        String[] categoriesPath = StringUtils.delimitedListToStringArray(path,
                "/");
        List<String> categoriesPathList = new ArrayList<String>(
                categoriesPath.length);
        for (String p : categoriesPath) {
            if (StringUtils.hasText(p)) {
                categoriesPathList.add(p);
            }
        }
        List<Category> categories = populateCategoriesFromPath(categoriesPathList);
        return categories;
    }

    public static List<Category> populateCategoriesFromPath(
            List<String> categoriesPath) {
        if (categoriesPath != null) {
            List<Category> categories = new ArrayList<Category>();
            for (int i = 0; i < categoriesPath.size(); i++) {
                String name = categoriesPath.get(i);
                Long index = Long.valueOf(i + 1);
                Category category = new Category(null, name, index);
                categories.add(category);
            }
            return categories;
        } else {
            return null;
        }
    }

    public static String categoryLinkString(List<?> categories) {
        StringBuilder sb = new StringBuilder();
        StringBuilder crumb = new StringBuilder();
        int i = 0;
        for (Object c : categories) {
            if (i++ > 0) {
                sb.append(Const.CATEGORY_DELIM);
            }
            crumb.append(extractName(c));
            crumb.append("/");
            sb.append("<span class=\"category\"><a href=\"");
            sb.append(preAppendIfNotStartsWithSlash(RequestUtil
                    .getContextRoot()));
            postAppendIfNotEndsWithSlash(sb);
            sb.append(Const.CATEGORY_PATH);
            postAppendIfNotEndsWithSlash(sb);
            sb.append(crumb.toString());
            sb.append("\">");
            sb.append(extractName(c));
            sb.append("</a></span>");
        }
        return sb.toString();
    }

    public static String categoryBreadCrumb(List<?> categories) {
        StringBuilder sb = new StringBuilder();
        StringBuilder crumb = new StringBuilder();
        int i = 0;
        sb.append("<ul class=\"breadcrumb\">");
        for (Object c : categories) {
            if (i++ > 0) {
                sb.append(Const.CATEGORY_DELIM);
            }
            crumb.append(extractName(c));
            crumb.append("/");
            sb.append("<li><span class=\"divider\"><a href=\"");
            sb.append(preAppendIfNotStartsWithSlash(RequestUtil
                    .getContextRoot()));
            postAppendIfNotEndsWithSlash(sb);
            sb.append(Const.CATEGORY_PATH);
            postAppendIfNotEndsWithSlash(sb);
            sb.append(crumb.toString());
            sb.append("\">");
            sb.append(extractName(c));
            sb.append("</a></span></li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    public static String categoryPathString(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        for (Category c : categories) {
            sb.append("/");
            sb.append(c.getName());
        }
        return sb.toString();
    }
}
