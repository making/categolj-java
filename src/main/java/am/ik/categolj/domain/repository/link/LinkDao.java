package am.ik.categolj.domain.repository.link;

import java.util.List;

import am.ik.categolj.domain.model.Link;

public interface LinkDao {
    List<Link> findAll();

    void save(Link link);

    void delete(Long id);
}
