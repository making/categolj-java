package am.ik.categolj.app.common.component;

import java.util.List;
import java.util.Map;

import am.ik.categolj.domain.model.Entry;

public interface PagerComponent {
    List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath);

    List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath, Map<String, Object> params);
}
