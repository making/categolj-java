package am.ik.categolj.domain.service.entry;

import java.util.List;
import java.util.Set;

import am.ik.categolj.domain.common.exception.NoSuchEntryException;
import am.ik.categolj.domain.model.Category;
import am.ik.categolj.domain.model.Entry;

public interface EntryService {
    Entry getEntryById(Long id) throws NoSuchEntryException;

    List<Entry> getEntriesByPage(int page, int count);

    List<Entry> getEntriesOnlyIdTitle(int count);

    List<Entry> getEntriesForGrid(int page, int rows, String sidx, String sord);

    int getTotalEntryCount();

    List<Entry> getCategorizedEntriesByPage(List<Category> category, int page,
            int count);

    int getCategorizeEntryCount(List<Category> category);

    void insertEntry(Entry entry);

    void updateEntry(Entry entry);

    void deleteEntry(Entry entry);

    Set<String> getAllCategoryLinkSet();

    List<String> getAllCategoryPath(String term);
}
