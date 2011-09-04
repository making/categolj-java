package am.ik.categolj.service.search;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import net.reduls.igo.Tagger;
import am.ik.categolj.dao.mongodb.MongoEntryDao;
import am.ik.categolj.entity.Entry;
import am.ik.categolj.service.EntrySearchService;
import am.ik.categolj.util.CommonUtils;

public class MongoEntrySearchService implements EntrySearchService {
    @Inject
    protected MongoEntryDao entryDao;
    @Inject
    protected Tagger tagger;

    @Override
    public List<Entry> getKeywordSearchedEntriesByPage(Set<String> keywords, int page,
            int count) {
        return entryDao.getKeywordSearchedEntriesByPage(keywords, page, count);
    }

    @Override
    public Set<String> splitQuery(String query) {
        return CommonUtils.createKeywordSet(tagger.wakati(query));
    }

    @Override
    public int getKeywordSearchedEntryCount(Set<String> keywords) {
        return entryDao.getKeywordSearchedEntryCount(keywords);
    }

}
