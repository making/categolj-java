package am.ik.categolj.app.common.service;

import java.util.List;

import am.ik.categolj.app.common.domain.Link;

public interface LinkService {
    List<Link> findAll();

    void save(Link link);

    void delete(Long id);
}
