package am.ik.categolj.app.common.dao.impl.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import am.ik.categolj.app.common.dao.LinkDao;
import am.ik.categolj.app.common.domain.Link;

public class MongoDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/webapp/WEB-INF/spring/appServlet/dao/mongodb.xml");
        LinkDao linkDao = ctx.getBean(LinkDao.class);
        List<Link> links = linkDao.findAll();
        System.out.println(links);
        ctx.close();
    }
}
