package am.ik.categolj.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import am.ik.categolj.app.dao.LinkDao;
import am.ik.categolj.app.domain.Link;
import am.ik.categolj.app.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
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
