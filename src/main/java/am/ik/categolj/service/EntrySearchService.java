package am.ik.categolj.service;

import java.util.List;
import java.util.Set;

import am.ik.categolj.entity.Entry;

public interface EntrySearchService {
    Set<String> splitQuery(String query);

    List<Entry> getKeywordSearchedEntriesByPage(Set<String> keywords, int page, int count);

    int getKeywordSearchedEntryCount(Set<String> keywords);
}
