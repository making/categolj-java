package am.ik.categolj.util;

import com.petebevin.markdown.MarkdownProcessor;

public class MarkdownUtils {
    public static String markdown(String txt) {
        MarkdownProcessor md = new MarkdownProcessor();
        return md.markdown(txt);
    }
}
