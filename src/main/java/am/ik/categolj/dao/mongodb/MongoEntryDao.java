package am.ik.categolj.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import am.ik.categolj.dao.EntryDao;
import am.ik.categolj.entity.Category;
import am.ik.categolj.entity.Entry;
import am.ik.categolj.exception.NoSuchEntryException;
import am.ik.categolj.util.CommonUtils;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;
import com.mongodb.WriteResult;

public class MongoEntryDao implements EntryDao {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MongoEntryDao.class);

    @Inject
    private Datastore ds;

    @Override
    public Entry getEntryById(Long id) throws NoSuchEntryException {
        Entry entry = ds.find(Entry.class, "id", id)
                .retrievedFields(false, "category-index", "distinct-category")
                .get();
        return entry;
    }

    @Override
    public List<Entry> getEntriesByPage(int page, int count) {
        int offset = CommonUtils.calcOffset(page);
        List<Entry> entries = ds.find(Entry.class).order("-id")
                .retrievedFields(false, "category-index", "distinct-category")
                .offset(offset).limit(count).asList();
        return entries;
    }

    @Override
    public List<Entry> getEntriesOnlyIdTitle(int count) {
        List<Entry> entries = ds.find(Entry.class).order("-id")
                .retrievedFields(true, "id", "title").limit(count).asList();
        LOGGER.debug("entries={}", entries);
        return entries;
    }

    @Override
    public int getTotalEntryCount() {
        return (int) ds.getCount(Entry.class);
    }

    protected Query<Entry> getCategorizedQuery(List<Category> category) {

        List<String> cl = new ArrayList<String>();
        for (Category c : category) {
            cl.add(c.getName() + "|" + c.getIndex());
        }
        Query<Entry> q = ds.createQuery(Entry.class)
                .filter("category-index all", cl)
                .retrievedFields(false, "category-index", "distinct-category");
        LOGGER.debug("query={}", q);
        return q;
    }

    @Override
    public List<Entry> getCategorizedEntriesByPage(List<Category> category,
            int page, int count) {
        int offset = CommonUtils.calcOffset(page);
        List<Entry> entries = getCategorizedQuery(category).offset(offset)
                .limit(count).asList();
        LOGGER.debug("entries={}", entries);
        return entries;
    }

    @Override
    public int getCategorizeEntryCount(List<Category> category) {
        return (int) getCategorizedQuery(category).countAll();
    }

    protected Long incrementAndGet() {
        Query<Sequence> q = ds.find(Sequence.class).filter("key", "entry");
        UpdateOperations<Sequence> ops = ds.createUpdateOperations(
                Sequence.class).inc("value", 1);
        Sequence seq = ds.findAndModify(q, ops, false, true);
        return seq.getValue();
    }

    protected void prepareEntry(Entry entry) {
        List<String> categoriesPath = entry.getCategoriesPath();
        List<String> indexes = new ArrayList<String>();
        for (int i = 0; i < categoriesPath.size(); i++) {
            String c = categoriesPath.get(i);
            indexes.add(c + "|" + (i + 1));
        }
        entry.setCategoryIndex(indexes);
        entry.setDistinctCategory(StringUtils.collectionToDelimitedString(
                categoriesPath, "|"));
    }

    @Override
    public void insertEntry(Entry entry) {
        entry.setId(incrementAndGet());
        prepareEntry(entry);
        LOGGER.info("insert={}", entry);
        Key<Entry> key = ds.save(entry);
        LOGGER.info("key={}", key);
    }

    @Override
    public void updateEntry(Entry entry) {
        prepareEntry(entry);
        LOGGER.info("update={}", entry);
        Query<Entry> q = ds.find(Entry.class).filter("id", entry.getId());
        UpdateResults<Entry> result = ds.updateFirst(q, entry, false);
        LOGGER.info("result={}", result);
    }

    @Override
    public void deleteEntry(Entry entry) {
        LOGGER.info("delete={}", entry);
        Query<Entry> q = ds.find(Entry.class).filter("id", entry.getId());
        WriteResult result = ds.delete(q);
        LOGGER.info("result={}", result);
    }

    public Datastore getDs() {
        return ds;
    }

    public void setDs(Datastore ds) {
        this.ds = ds;
    }
}
