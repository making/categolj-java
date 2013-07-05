package am.ik.categolj.domain.common.util;

import com.atlassian.labs.markdown.PageDownMarkdown;

public class MarkdownUtils {
    public static String markdown(String txt) {
        return new PageDownMarkdown().markdown(txt);
    }
}
