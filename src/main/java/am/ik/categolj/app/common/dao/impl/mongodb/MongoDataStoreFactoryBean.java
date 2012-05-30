package am.ik.categolj.app.common.dao.impl.mongodb;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import am.ik.categolj.common.fw.consts.LogId;
import am.ik.yalf.logger.Logger;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class MongoDataStoreFactoryBean implements FactoryBean<Datastore>,
        InitializingBean, PersistenceExceptionTranslator, DisposableBean {
    private static final Logger LOGGER = Logger
            .getLogger(MongoDataStoreFactoryBean.class);

    private String host;
    private Integer port;
    private Datastore ds;
    private String dbName;
    private Mongo mongo;

    @SuppressWarnings("serial")
    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return new DataAccessException("Exception occured", ex) {
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mongo = new Mongo(host, port);
        ds = new Morphia().createDatastore(mongo, dbName);
        LOGGER.debug(LogId.DCTGL006, mongo);
        LOGGER.debug(LogId.DCTGL007, ds);
    }

    @Override
    public Datastore getObject() throws Exception {
        return ds;
    }

    @Override
    public Class<?> getObjectType() {
        return Datastore.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.debug(LogId.DCTGL008, mongo);
        mongo.close();
    }
}
