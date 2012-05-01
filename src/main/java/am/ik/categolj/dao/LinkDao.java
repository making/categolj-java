package am.ik.categolj.dao;

import java.util.List;

import am.ik.categolj.entity.Link;

public interface LinkDao {
    List<Link> findAll();

    void save(Link link);

    void delete(Long id);
}
