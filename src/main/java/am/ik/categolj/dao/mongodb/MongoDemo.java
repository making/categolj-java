package am.ik.categolj.dao.mongodb;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.reduls.igo.Tagger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import am.ik.categolj.entity.Entry;
import am.ik.categolj.util.TaggerUtils;

import com.google.code.morphia.Datastore;

public class MongoDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/webapp/WEB-INF/spring/appServlet/dao/mongodb.xml");
        Datastore ds = ctx.getBean(Datastore.class);
        MongoEntryDao entryDao = ctx.getBean(MongoEntryDao.class);

        List<Entry> entries = Arrays.asList(entryDao.getEntryById(74L));
        entries = entryDao.getEntriesByPage(1, 300);
        Tagger tagger = new Tagger(new ClassPathResource("ipadic").getFile()
                .getPath());
        entryDao.setTagger(tagger);
        for (Entry e : entries) {
            Set<String> keywords = TaggerUtils.extractKeywords(tagger,
                    e.getContent() + " " + e.getTitle());
            System.out.println(e.getId() + "=" + keywords);
            e.setKeywords(keywords);
            entryDao.updateEntry(e);
            System.out.println("----------");
        }
    }
}
