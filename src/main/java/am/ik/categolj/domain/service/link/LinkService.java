package am.ik.categolj.domain.service.link;

import java.util.List;

import am.ik.categolj.domain.model.Link;

public interface LinkService {
    List<Link> findAll();

    void save(Link link);

    void delete(Long id);
}
