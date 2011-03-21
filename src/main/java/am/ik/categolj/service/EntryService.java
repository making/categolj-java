package am.ik.categolj.service;

import java.util.List;
import java.util.Set;

import am.ik.categolj.exception.NoSuchEntryException;
import am.ik.cateoglj.entity.Category;
import am.ik.cateoglj.entity.Entry;

public interface EntryService {
    Entry getEntryById(Long id) throws NoSuchEntryException;

    List<Entry> getEntriesByPage(int page, int count);

    List<Entry> getEntriesOnlyIdTitle(int count);

    int getTotalEntryCount();

    List<Entry> getCategorizedEntriesByPage(List<Category> category, int page,
            int count);

    int getCategorizeEntryCount(List<Category> category);

    void insertEntry(Entry entry);

    void updateEntry(Entry entry);

    void deleteEntry(Entry entry);

    Set<String> getAllCategoryLinkSet();
}
