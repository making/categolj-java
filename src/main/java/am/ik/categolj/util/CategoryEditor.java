package am.ik.categolj.util;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Category;

public class CategoryEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else {
            String[] categoryNames = StringUtils.delimitedListToStringArray(
                    text, Const.CATEGORY_DELIM);
            List<Category> categories = new ArrayList<Category>();
            for (int i = 0; i < categoryNames.length; i++) {
                String name = categoryNames[i];
                Long index = Long.valueOf(i + 1);
                Category category = new Category(null, name, index);
                categories.add(category);
            }
            setValue(categories);
        }
    }

    @Override
    public String getAsText() {
        @SuppressWarnings("unchecked")
        List<Category> category = (List<Category>) getValue();
        StringBuilder sb = new StringBuilder();
        if (category != null) {
            int i = 0;
            for (Category c : category) {
                if (i++ > 0) {
                    sb.append(Const.CATEGORY_DELIM);
                }
                sb.append(c.getName());
            }
        }
        return sb.toString();
    }
}
