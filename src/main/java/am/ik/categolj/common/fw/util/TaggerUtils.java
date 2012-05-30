package am.ik.categolj.common.fw.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import net.reduls.igo.Morpheme;
import net.reduls.igo.Tagger;

public class TaggerUtils {
    public static final Pattern XML_TAG = Pattern.compile("<[^<]+>");

    public static Set<String> extractKeywords(Tagger tagger, String content) {
        Set<String> keywords = new HashSet<String>();
        String html = MarkdownUtils.markdown(content); // HTMLに変換
        html = XML_TAG.matcher(html).replaceAll(" "); // タグを除去
        List<Morpheme> ret = tagger.parse(html);
        for (Morpheme m : ret) {
            if (CommonUtils.isValidKeyword(m.surface)) {
                String[] fea = m.feature.split(",");
                if ("名詞".equals(fea[0])
                        && ("一般".equals(fea[1]) || "固有名詞".equals(fea[1]))) {
                    keywords.add(m.surface.toUpperCase());
                }
            }
        }
        return keywords;
    }
}
