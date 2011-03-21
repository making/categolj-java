package am.ik.categolj.service;

import java.util.List;

import am.ik.categolj.entity.Category;
import am.ik.categolj.entity.Entry;

public interface PagerService {
    List<String> createPaginationLinks(int totalPage, int currentPage,
            List<Category> categories, List<Entry> entries);
}
