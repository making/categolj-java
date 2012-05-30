package am.ik.categolj.common.fw.support.igo;

import java.io.File;

import net.reduls.igo.Tagger;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class TaggerFactoryBean implements FactoryBean<Tagger>,
        InitializingBean, DisposableBean {
    private Resource dictDir;
    private Tagger tagger;

    @Override
    public Tagger getObject() throws Exception {
        return tagger;
    }

    @Override
    public Class<?> getObjectType() {
        return Tagger.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Resource getDictDir() {
        return dictDir;
    }

    public void setDictDir(Resource dictDir) {
        this.dictDir = dictDir;
    }

    @Override
    public void destroy() throws Exception {
        tagger = null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        File dict = dictDir.getFile();
        tagger = new Tagger(dict.getPath());
    }
}
