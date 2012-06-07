package am.ik.categolj.app.common.dao.impl.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import am.ik.categolj.app.common.dao.EntryDao;
import am.ik.categolj.app.common.domain.Category;
import am.ik.categolj.app.common.domain.Entry;
import am.ik.categolj.app.common.exception.NoSuchEntryException;
import am.ik.categolj.common.fw.util.CommonUtils;

@Repository
public class MockEntryDao implements EntryDao {

    private Map<Long, Entry> entryMap = createInitialMap();

    protected Map<Long, Entry> createInitialMap() {
        Map<Long, Entry> entryMap = new LinkedHashMap<Long, Entry>();

        Entry[] entries = {
                new Entry(1L, "aaa", "hoge", new Date(), new Date(),
                        Arrays.asList("xxx", "yyy")),
                new Entry(2L, "bbb", "foo", new Date(), new Date(),
                        Arrays.asList("xxx", "yyy")),
                new Entry(3L, "ccc", "bar", new Date(), new Date(),
                        Arrays.asList("xxx", "yyy", "zzz")),
                new Entry(4L, "ddd", "aaa", new Date(), new Date(),
                        Arrays.asList("xxx", "www")) };

        for (Entry e : entries) {
            entryMap.put(e.getId(), e);
        }

        return entryMap;
    }

    protected Map<Long, Entry> getEntryMap() {
        return entryMap;
    }

    @Override
    public Entry getEntryById(Long id) throws NoSuchEntryException {
        Entry entry = getEntryMap().get(id);
        if (entry == null) {
            throw new NoSuchEntryException(id);
        }
        return entry;
    }

    private static List<Entry> getPaged(List<Entry> list, int page, int count) {
        Collections.reverse(list);
        List<Entry> result = new ArrayList<Entry>();
        int offset = CommonUtils.calcOffset(page, count);
        int len = Math.min(count, list.size()) - offset;
        for (int i = 0; i < len; i++) {
            Entry e = list.get(i + offset);
            result.add(e);
        }
        return result;
    }

    @Override
    public List<Entry> getEntriesByPage(int page, int count) {
        List<Entry> list = new ArrayList<Entry>(getEntryMap().values());
        return getPaged(list, page, count);
    }

    @Override
    public List<Entry> getEntriesOnlyIdTitle(int count) {
        List<Entry> list = new ArrayList<Entry>(getEntryMap().values());
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<Entry> getEntriesForGrid(int page, int rows, String sidx,
            String sord) {
        return getEntriesByPage(page, rows);
    }

    @Override
    public int getTotalEntryCount() {
        return getEntryMap().size();
    }

    @Override
    public List<Entry> getCategorizedEntriesByPage(List<Category> category,
            int page, int count) {
        List<Entry> list = new ArrayList<Entry>();

        for (Map.Entry<Long, Entry> e : getEntryMap().entrySet()) {
            Entry entry = e.getValue();
            boolean add = true;
            for (Category target : category) {
                if (!entry.getCategories().contains(target)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                list.add(entry);
            }
        }

        return getPaged(list, page, count);
    }

    @Override
    public int getCategorizeEntryCount(List<Category> category) {
        return getCategorizedEntriesByPage(category, 1, Integer.MAX_VALUE)
                .size();
    }

    @Override
    public void insertEntry(Entry entry) {
        Entry last = new ArrayList<Entry>(getEntryMap().values())
                .get(getEntryMap().size() - 1);
        entry.setId(last.getId() + 1);
        getEntryMap().put(entry.getId(), entry);
    }

    @Override
    public void updateEntry(Entry entry) {
        getEntryMap().put(entry.getId(), entry);
    }

    @Override
    public void deleteEntry(Entry entry) {
        getEntryMap().remove(entry.getId());
    }

    @Override
    public List<String> getAllCategoryPath(String term) {
        return Arrays.asList(new String[] { "foo", "bar" });
    }

}
