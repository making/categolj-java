package am.ik.categolj.app.common.component.impl;

import static am.ik.categolj.common.fw.util.CommonUtils.postAppendIfNotEndsWithSlash;
import static am.ik.categolj.common.fw.util.CommonUtils.preAppendIfNotStartsWithSlash;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jp.sf.amateras.functions.utils.StringUtils;
import am.ik.categolj.app.common.component.PagerComponent;
import am.ik.categolj.app.common.consts.Const;
import am.ik.categolj.app.domain.Entry;
import am.ik.categolj.common.fw.listener.RequestUtil;

@Service
public class DefaultPagerComponent implements PagerComponent {

    @Override
    public List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath) {
        return createPaginationLinks(totalPage, currentPage, entries,
                appendPath, null);
    }

    @Override
    public List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath, Map<String, Object> params) {
        LinkedList<String> links = new LinkedList<String>();
        int maxLen = 10;

        // max=7,total=10,current=1 -> 1,2,3,4,5,6,7 0
        // max=7,total=10,current=2 -> 1,2,3,4,5,6,7 0
        // max=7,total=10,current=3 -> 1,2,3,4,5,6,7 0
        // max=7,total=10,current=4 -> 1,2,3,4,5,6,7 0 <- maxLen/2 + 1
        // max=7,total=10,current=5 -> 2,3,4,5,6,7,8 1
        // max=7,total=10,current=6 -> 3,4,5,6,7,8,9 2
        // max=7,total=10,current=7 -> 4,5,6,7,8,9,10 3
        // max=7,total=10,current=8 -> 4,5,6,7,8,9,10 3
        // max=7,total=10,current=9 -> 4,5,6,7,8,9,10 3
        // max=7,total=10,current=10-> 4,5,6,7,8,9,10 3

        // max=7,total=15,current=1 -> 1,2,3,4,5,6,7 0
        // max=7,total=15,current=2 -> 1,2,3,4,5,6,7 0
        // max=7,total=15,current=3 -> 1,2,3,4,5,6,7 0
        // max=7,total=15,current=4 -> 1,2,3,4,5,6,7 0
        // max=7,total=15,current=5 -> 2,3,4,5,6,7,8 1
        // max=7,total=15,current=6 -> 3,4,5,6,7,8,9 2
        // max=7,total=15,current=7 -> 4,5,6,7,8,9,10 3
        // max=7,total=15,current=8 -> 5,6,7,8,9,10,11 4
        // max=7,total=15,current=9 -> 6,7,8,9,10,11,12 5
        // max=7,total=15,current=10-> 7,8,9,10,11,12,13 6
        // max=7,total=15,current=11-> 8,9,10,11,12,13,14 7
        // max=7,total=15,current=12-> 9,10,11,12,13,14,15 8
        // max=7,total=15,current=13-> 9,10,11,12,13,14,15 8
        // max=7,total=15,current=14-> 9,10,11,12,13,14,15 8
        // max=7,total=15,current=15-> 9,10,11,12,13,14,15 8

        int len = Math.min(maxLen + 1, (totalPage > 1) ? totalPage + 1 : 0);
        int offset = 0;
        if (totalPage >= maxLen) {
            if (currentPage > totalPage - maxLen / 2) {
                offset = totalPage - maxLen;
            } else if (currentPage >= maxLen / 2 + 1) {
                offset = currentPage - (maxLen / 2 + 1);
            }
        }
        String queryString = StringUtils.map2queryString(params);
        StringBuilder lastLink = new StringBuilder();
        StringBuilder firstLink = new StringBuilder();

        for (int i = 0; i <= len; i++) {
            int page = i + offset;
            StringBuilder sb = new StringBuilder();
            if (page == currentPage) {
                sb.append("<span class=\"active\">");
            }
            sb.append("<a href=\"");
            sb.append(preAppendIfNotStartsWithSlash(RequestUtil
                    .getContextRoot()));
            postAppendIfNotEndsWithSlash(sb);
            sb.append(Const.PAGE_PATH);
            postAppendIfNotEndsWithSlash(sb);
            sb.append(page);
            if (appendPath != null && !appendPath.isEmpty()) {
                sb.append(preAppendIfNotStartsWithSlash(appendPath));
            }
            sb.append("/");
            if (params != null) {
                sb.append("?");
                sb.append(queryString);
            }
            sb.append("\">");
            if (page >= 1 && page == currentPage - 1) {
                firstLink.append("<span class=\"prev\">");
                firstLink.append(sb);
                firstLink.append("&larr; Previous</a></span>");
            }
            if (page <= totalPage && page == currentPage + 1) {
                lastLink.append("<span class=\"next\">");
                lastLink.append(sb);
                lastLink.append("Next &rarr;</a></span>");
            }

            sb.append(page);
            sb.append("</a>");
            if (i == currentPage) {
                sb.append("</span>");
            }
            if (i != 0 && i != len) { // exclude prev & next
                links.add(sb.toString());
            }
        }
        if (!links.isEmpty()) {
            // add prev
            if (firstLink.length() > 0) {
                links.addFirst(firstLink.toString());
            } else {
                links.addFirst("<span class=\"prev disabled\"><a href=\"#\">&larr; Previous</a></span>");
            }
            // add next
            if (lastLink.length() > 0) {
                links.addLast(lastLink.toString());
            } else {
                links.addLast("<span class=\"next disabled\"><a href=\"#\">Next &rarr;</a></span>");
            }
        }
        return links;
    }
}
