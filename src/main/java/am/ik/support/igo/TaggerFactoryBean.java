package am.ik.support.igo;

import java.io.File;

import net.reduls.igo.Tagger;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

public class TaggerFactoryBean implements FactoryBean<Tagger> {
    private Resource dictDir;

    @Override
    public Tagger getObject() throws Exception {
        File dict = dictDir.getFile();
        return new Tagger(dict.getPath());
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
}
