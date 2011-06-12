package am.ik.categolj.dao.mongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import am.ik.categolj.dao.EntryDao;
import am.ik.categolj.entity.Entry;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public class MongoDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // Mongo mongo = new Mongo("192.168.11.116", 27017);
        // Morphia morphia = new Morphia();
        // Datastore ds = morphia.createDatastore(mongo, "categolj");

        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/webapp/WEB-INF/spring/appServlet/dao/mongodb.xml");
        Datastore ds = ctx.getBean(Datastore.class);
        EntryDao entryDao = ctx.getBean(EntryDao.class);
        System.out.println(entryDao.getEntryById(44L));
        for (Entry e : entryDao.getEntriesByPage(1, 3)) {
            System.out.println(e);
        }
        Query<Sequence> q = ds.find(Sequence.class).filter("key", "entry");
        UpdateOperations<Sequence> ops = ds
                .createUpdateOperations(Sequence.class).inc("value", 1);
        System.out.println(ds.findAndModify(q, ops, false, true));
    }
}
