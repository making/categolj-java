package am.ik.categolj.domain.service.link;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import am.ik.categolj.domain.model.Link;
import am.ik.categolj.domain.repository.link.LinkDao;

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
