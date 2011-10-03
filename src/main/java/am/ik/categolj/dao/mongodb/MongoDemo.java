package am.ik.categolj.dao.mongodb;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import am.ik.categolj.entity.Entry;

import com.google.code.morphia.Datastore;
import com.mongodb.BasicDBObject;

public class MongoDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/webapp/WEB-INF/spring/appServlet/dao/mongodb.xml");
        Datastore ds = ctx.getBean(Datastore.class);
        MongoEntryDao entryDao = ctx.getBean(MongoEntryDao.class);

        List<?> col = ds.getCollection(Entry.class).distinct(
                "distinct-category",
                new BasicDBObject("distinct-category",
                        Pattern.compile(Pattern.quote("dev|o") + ".+",
                                Pattern.CASE_INSENSITIVE)));
        for (Object c : col) {
            System.out.println(c);
        }
    }
}
