package am.ik.categolj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Category;

public class CategoryUtils {

    private static String extractName(Object o) {
        if (o instanceof Category) {
            return ((Category) o).getName();
        }
        return o.toString();
    }

    public static List<Category> populateCategoriesFromPath(String path) {
        String[] categoriesPath = StringUtils.delimitedListToStringArray(path,
                "/");
        List<Category> categories = populateCategoriesFromPath(Arrays
                .asList(categoriesPath));
        return categories;
    }

    public static List<Category> populateCategoriesFromPath(
            List<String> categoriesPath) {
        List<Category> categories = new ArrayList<Category>();
        if (categoriesPath != null) {
            for (int i = 0; i < categoriesPath.size(); i++) {
                String name = categoriesPath.get(i);
                Long index = Long.valueOf(i + 1);
                Category category = new Category(null, name, index);
                categories.add(category);
            }
        }
        return categories;
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
            sb.append(Const.CONTEXT_PATH);
            sb.append(Const.CATEGORY_PATH);
            sb.append("/");
            sb.append(crumb.toString());
            sb.append("\">");
            sb.append(extractName(c));
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
