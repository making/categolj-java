package am.ik.categolj.dao.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import am.ik.categolj.dao.LinkDao;
import am.ik.categolj.entity.Link;

public class MongoDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/webapp/WEB-INF/spring/appServlet/dao/mongodb.xml");
        LinkDao linkDao = ctx.getBean(LinkDao.class);
        linkDao.save(new Link("Twitter", "http://twitter.com/#!/making"));
        linkDao.save(new Link("Github", "http://github.com/making"));
        linkDao.save(new Link("Facebook",
                "http://www.facebook.com/toshiaki.maki"));
        linkDao.save(new Link("LinkedIn",
                "http://jp.linkedin.com/pub/toshiaki-maki/37/148/703"));

        List<Link> links = linkDao.findAll();
        System.out.println(links);

        for (Link l : links) {
            // linkDao.delete(l.getId());
        }
    }
}
