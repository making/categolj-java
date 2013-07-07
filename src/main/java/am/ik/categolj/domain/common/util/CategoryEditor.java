package am.ik.categolj.domain.common.util;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import am.ik.categolj.domain.common.consts.Const;

public class CategoryEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else {
            String[] categoryNames = StringUtils.delimitedListToStringArray(
                    text, Const.CATEGORY_DELIM);
            List<String> categories = Arrays.asList(categoryNames);
            setValue(categories);
        }
    }

    @Override
    public String getAsText() {
        @SuppressWarnings("unchecked")
        List<String> category = (List<String>) getValue();
        String text = StringUtils.collectionToDelimitedString(category,
                Const.CATEGORY_DELIM);
        return text;
    }
}
