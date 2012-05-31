package am.ik.categolj.app.blog.service.impl.mock;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import am.ik.categolj.app.blog.service.EntrySearchService;
import am.ik.categolj.app.common.domain.Entry;

@Service
public class MockEntrySearchService implements EntrySearchService {

    @Override
    public Set<String> splitQuery(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Entry> getKeywordSearchedEntriesByPage(Set<String> keywords,
            int page, int count) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getKeywordSearchedEntryCount(Set<String> keywords) {
        // TODO Auto-generated method stub
        return 0;
    }

}
