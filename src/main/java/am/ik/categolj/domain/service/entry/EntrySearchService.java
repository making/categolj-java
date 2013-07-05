package am.ik.categolj.domain.service.entry;

import java.util.List;
import java.util.Set;

import am.ik.categolj.domain.model.Entry;

public interface EntrySearchService {
    Set<String> splitQuery(String query);

    List<Entry> getKeywordSearchedEntriesByPage(Set<String> keywords, int page, int count);

    int getKeywordSearchedEntryCount(Set<String> keywords);
}
