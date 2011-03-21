package am.ik.categolj.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Category;

public class CategoryUtils {
    public static List<Category> populateCategoriesFromPath(String path) {
        List<Category> categories = new ArrayList<Category>();
        String[] categoriesPath = StringUtils.delimitedListToStringArray(path,
                "/");
        for (int i = 0; i < categoriesPath.length; i++) {
            String name = categoriesPath[i];
            Long index = Long.valueOf(i + 1);
            Category category = new Category(null, name, index);
            categories.add(category);
        }
        return categories;
    }

    public static String categoryLinkString(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        StringBuilder crumb = new StringBuilder();
        int i = 0;
        for (Category c : categories) {
            if (i++ > 0) {
                sb.append(Const.CATEGORY_DELIM);
            }
            crumb.append(c.getName());
            crumb.append("/");
            sb.append("<span class=\"category\"><a href=\"");
            sb.append(Const.CONTEXT_PATH);
            sb.append(Const.CATEGORY_PATH);
            sb.append("/");
            sb.append(crumb.toString());
            sb.append("\">");
            sb.append(c.getName());
            sb.append("</a></span>");
        }
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
