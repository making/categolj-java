package am.ik.categolj.app.dao;

import java.util.List;

import am.ik.categolj.app.common.exception.NoSuchEntryException;
import am.ik.categolj.app.domain.Category;
import am.ik.categolj.app.domain.Entry;

public interface EntryDao {
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

    List<String> getAllCategoryPath(String term);
}
