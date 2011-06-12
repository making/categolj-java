package am.ik.categolj.dao.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class MongoDataStoreFactoryBean implements FactoryBean<Datastore>,
        InitializingBean, PersistenceExceptionTranslator, DisposableBean {
    private static final Logger LOGGER = LoggerFactory
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
        LOGGER.debug("connect mongo {}", mongo);
        LOGGER.debug("create datastore {}", ds);
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
        LOGGER.debug("close mongo {}", mongo);
        mongo.close();
    }
}
