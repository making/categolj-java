package am.ik.categolj.service.entry;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import am.ik.categolj.common.Const;
import am.ik.categolj.dao.CategoryDao;
import am.ik.categolj.dao.EntryDao;
import am.ik.categolj.entity.Category;
import am.ik.categolj.entity.Entry;
import am.ik.categolj.exception.NoSuchEntryException;
import am.ik.categolj.service.EntryService;
import am.ik.categolj.util.CategoryUtils;

@Service
public class EntryServiceImpl implements EntryService {
    @Inject
    protected EntryDao entryDao;
    @Inject
    protected CategoryDao categoryDao;

    @Override
    public Entry getEntryById(Long id) throws NoSuchEntryException {
        return entryDao.getEntryById(id);
    }

    @Override
    public List<Entry> getEntriesByPage(int page, int count) {
        return entryDao.getEntriesByPage(page, count);
    }

    @Override
    public List<Entry> getEntriesOnlyIdTitle(int count) {
        return entryDao.getEntriesOnlyIdTitle(count);
    }

    @Override
    public int getTotalEntryCount() {
        return entryDao.getTotalEntryCount();
    }

    @Override
    public List<Entry> getCategorizedEntriesByPage(List<Category> category,
            int page, int count) {
        return entryDao.getCategorizedEntriesByPage(category, page, count);
    }

    @Override
    public int getCategorizeEntryCount(List<Category> category) {
        return entryDao.getCategorizeEntryCount(category);
    }

    @Override
    public void insertEntry(Entry entry) {
        List<Category> categories = entry.getCategory();
        if (categories != null) {
            for (Category category : categories) {
                categoryDao.insertCategory(category);
            }
        }
        entryDao.insertEntry(entry);
    }

    @Override
    public void updateEntry(Entry entry) {
        List<Category> categories = entry.getCategory();
        if (categories != null) {
            for (Category category : categories) {
                categoryDao.updateCategory(category);
            }
        }
        entryDao.updateEntry(entry);
    }

    @Override
    public void deleteEntry(Entry entry) {
        List<Category> categories = entry.getCategory();
        if (categories != null) {
            for (Category category : categories) {
                categoryDao.deleteCategory(category);
            }
        }
        entryDao.deleteEntry(entry);
    }

    @Override
    public Set<String> getAllCategoryLinkSet() {
        Set<String> linkSet = new TreeSet<String>();
        List<Entry> entries = getEntriesByPage(Const.START_PAGE,
                Integer.MAX_VALUE);
        for (Entry e : entries) {
            linkSet.add(CategoryUtils.categoryLinkString(e.getCategory()));
        }
        return linkSet;
    }

}