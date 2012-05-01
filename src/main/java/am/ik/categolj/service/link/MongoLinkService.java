package am.ik.categolj.service.link;

import java.util.List;

import javax.inject.Inject;

import am.ik.categolj.dao.LinkDao;
import am.ik.categolj.entity.Link;
import am.ik.categolj.service.LinkService;

public class MongoLinkService implements LinkService {
    @Inject
    protected LinkDao linkDao;

    @Override
    public List<Link> findAll() {
        return linkDao.findAll();
    }

    @Override
    public void save(Link link) {
        linkDao.save(link);
    }

    @Override
    public void delete(Long id) {
        linkDao.delete(id);
    }

}
