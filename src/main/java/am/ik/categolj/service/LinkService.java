package am.ik.categolj.service;

import java.util.List;

import am.ik.categolj.entity.Link;

public interface LinkService {
    List<Link> findAll();

    void save(Link link);

    void delete(Long id);
}
