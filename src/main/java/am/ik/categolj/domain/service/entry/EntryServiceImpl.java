package am.ik.categolj.domain.service.entry;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import am.ik.categolj.app.common.consts.Const;
import am.ik.categolj.app.common.exception.NoSuchEntryException;
import am.ik.categolj.domain.common.util.CategoryUtils;
import am.ik.categolj.domain.model.Category;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.repository.entry.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {
    @Inject
    protected EntryRepository entryRepository;

    @Override
    public Entry getEntryById(Long id) throws NoSuchEntryException {
        return entryRepository.getEntryById(id);
    }

    @Override
    public List<Entry> getEntriesByPage(int page, int count) {
        return entryRepository.getEntriesByPage(page, count);
    }

    @Override
    public List<Entry> getEntriesOnlyIdTitle(int count) {
        return entryRepository.getEntriesOnlyIdTitle(count);
    }

    @Override
    public List<Entry> getEntriesForGrid(int page, int rows, String sidx,
            String sord) {
        return entryRepository.getEntriesForGrid(page, rows, sidx, sord);
    }

    @Override
    public int getTotalEntryCount() {
        return entryRepository.getTotalEntryCount();
    }

    @Override
    public List<Entry> getCategorizedEntriesByPage(List<Category> category,
            int page, int count) {
        return entryRepository.getCategorizedEntriesByPage(category, page, count);
    }

    @Override
    public int getCategorizeEntryCount(List<Category> category) {
        return entryRepository.getCategorizeEntryCount(category);
    }

    @Override
    public void insertEntry(Entry entry) {
        entryRepository.insertEntry(entry);
    }

    @Override
    public void updateEntry(Entry entry) {
        entryRepository.updateEntry(entry);
    }

    @Override
    public void deleteEntry(Entry entry) {
        entryRepository.deleteEntry(entry);
    }

    @Override
    public Set<String> getAllCategoryLinkSet() {
        Set<String> linkSet = new TreeSet<String>();
        List<Entry> entries = getEntriesByPage(Const.START_PAGE,
                Integer.MAX_VALUE);
        for (Entry e : entries) {
            linkSet.add(CategoryUtils.categoryLinkString(e.getCategories()));
        }
        return linkSet;
    }

    @Override
    public List<String> getAllCategoryPath(String term) {
        return entryRepository.getAllCategoryPath(term);
    }

}
