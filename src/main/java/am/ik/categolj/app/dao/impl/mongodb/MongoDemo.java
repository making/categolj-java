package am.ik.categolj.app.dao.impl.mongodb;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import am.ik.categolj.app.dao.EntryDao;
import am.ik.categolj.app.dao.UserDao;
import am.ik.categolj.app.domain.Entry;
import am.ik.categolj.app.domain.User;

public class MongoDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("mongodb");
        ctx.load(new String[] { "classpath:/META-INF/spring/applicationContext.xml" });
        ctx.refresh();
        
//        EntryDao entryDao = ctx.getBean(EntryDao.class);
//        List<Entry> entries = entryDao.getEntriesByPage(1, 5);
//        for (Entry e : entries) {
//            System.out.println(e);
//        }
        User u = new User("making", "makix", "USER");
        UserDao dao = ctx.getBean(UserDao.class);
        dao.insertUser(u);
        System.out.println(dao.getUserByName(u.getName()));
        ctx.close();
    }
}
