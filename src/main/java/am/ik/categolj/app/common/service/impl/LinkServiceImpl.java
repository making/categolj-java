package am.ik.categolj.app.common.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import am.ik.categolj.app.common.dao.LinkDao;
import am.ik.categolj.app.common.domain.Link;
import am.ik.categolj.app.common.service.LinkService;

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
