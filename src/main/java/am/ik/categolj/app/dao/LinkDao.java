package am.ik.categolj.app.dao;

import java.util.List;

import am.ik.categolj.app.domain.Link;

public interface LinkDao {
    List<Link> findAll();

    void save(Link link);

    void delete(Long id);
}
