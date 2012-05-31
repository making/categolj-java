package am.ik.categolj.app.common.dao.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import am.ik.categolj.app.common.dao.LinkDao;
import am.ik.categolj.app.common.domain.Link;

@Repository
public class MockLinkDao implements LinkDao {

    @Override
    public List<Link> findAll() {
        // TODO Auto-generated method stub
        return new ArrayList<Link>();
    }

    @Override
    public void save(Link link) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

}
