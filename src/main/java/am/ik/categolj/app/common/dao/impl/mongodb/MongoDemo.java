package am.ik.categolj.app.common.dao.impl.mongodb;

import java.util.List;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import am.ik.categolj.app.common.dao.EntryDao;
import am.ik.categolj.app.common.domain.Entry;

public class MongoDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
                new String[] {
                        "src/main/webapp/WEB-INF/spring/appServlet/controllers.xml",
                        "src/main/webapp/WEB-INF/spring/appServlet/services.xml" });
        EntryDao entryDao = ctx.getBean(EntryDao.class);
        List<Entry> entries = entryDao.getEntriesByPage(1, 5);
        for (Entry e : entries) {
            System.out.println(e);
        }
        ctx.close();
    }
}
