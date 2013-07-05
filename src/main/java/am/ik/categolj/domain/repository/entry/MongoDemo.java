package am.ik.categolj.domain.repository.entry;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.model.User;
import am.ik.categolj.domain.repository.user.UserRepository;

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
        User u = new User("foo", "bar", "USER");
        UserRepository dao = ctx.getBean(UserRepository.class);
        dao.insertUser(u);
        System.out.println(dao.getUserByName(u.getName()));
        ctx.close();
    }
}
