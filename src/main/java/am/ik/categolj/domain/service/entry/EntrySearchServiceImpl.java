package am.ik.categolj.domain.service.entry;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.reduls.igo.Tagger;
import am.ik.categolj.domain.common.util.CommonUtils;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.repository.entry.MongoEntryRepository;

@Service
public class EntrySearchServiceImpl implements EntrySearchService {
    @Inject
    protected MongoEntryRepository entryRepository;
    @Inject
    protected Tagger tagger;

    @Override
    public List<Entry> getKeywordSearchedEntriesByPage(Set<String> keywords, int page,
            int count) {
        return entryRepository.getKeywordSearchedEntriesByPage(keywords, page, count);
    }

    @Override
    public Set<String> splitQuery(String query) {
        return CommonUtils.createKeywordSet(tagger.wakati(query));
    }

    @Override
    public int getKeywordSearchedEntryCount(Set<String> keywords) {
        return entryRepository.getKeywordSearchedEntryCount(keywords);
    }

}
