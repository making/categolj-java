package am.ik.categolj.app.common.service.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import am.ik.categolj.app.common.consts.Const;
import am.ik.categolj.app.common.dao.EntryDao;
import am.ik.categolj.app.common.domain.Category;
import am.ik.categolj.app.common.domain.Entry;
import am.ik.categolj.app.common.exception.NoSuchEntryException;
import am.ik.categolj.app.common.service.EntryService;
import am.ik.categolj.common.fw.util.CategoryUtils;

@Service
public class EntryServiceImpl implements EntryService {
    @Inject
    protected EntryDao entryDao;

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
    public List<Entry> getEntriesForGrid(int page, int rows, String sidx,
            String sord) {
        return entryDao.getEntriesForGrid(page, rows, sidx, sord);
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
        entryDao.insertEntry(entry);
    }

    @Override
    public void updateEntry(Entry entry) {
        entryDao.updateEntry(entry);
    }

    @Override
    public void deleteEntry(Entry entry) {
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

    @Override
    public List<String> getAllCategoryPath(String term) {
        return entryDao.getAllCategoryPath(term);
    }

}
