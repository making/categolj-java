package am.ik.categolj.app.common.dao;

import java.util.List;

import am.ik.categolj.app.common.domain.Link;

public interface LinkDao {
    List<Link> findAll();

    void save(Link link);

    void delete(Long id);
}
