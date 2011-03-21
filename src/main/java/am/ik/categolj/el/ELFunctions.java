package am.ik.categolj.el;

import com.petebevin.markdown.MarkdownProcessor;

public class ELFunctions {
    public static String markdown(String txt) {
        MarkdownProcessor md = new MarkdownProcessor();
        return md.markdown(txt);
    }
}
