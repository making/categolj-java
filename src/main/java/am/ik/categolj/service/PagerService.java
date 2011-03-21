package am.ik.categolj.service;

import java.util.List;

import am.ik.cateoglj.entity.Category;
import am.ik.cateoglj.entity.Entry;

public interface PagerService {
    List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Category> categories, List<Entry> entries);
}
