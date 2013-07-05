package am.ik.categolj.domain.service.link;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import am.ik.categolj.domain.model.Link;
import am.ik.categolj.domain.repository.link.LinkRepository;

@Service
public class LinkServiceImpl implements LinkService {
    @Inject
    protected LinkRepository linkRepository;

    @Override
    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    @Override
    public void save(Link link) {
        linkRepository.save(link);
    }

    @Override
    public void delete(Long id) {
        linkRepository.delete(id);
    }

}
