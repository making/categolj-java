package am.ik.categolj.app.blog.service;

import java.util.List;
import java.util.Map;

import am.ik.categolj.app.common.domain.Entry;

public interface PagerService {
    List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath);

    List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Entry> entries, String appendPath, Map<String, Object> params);
}
