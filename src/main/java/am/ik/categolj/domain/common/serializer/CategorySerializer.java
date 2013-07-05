package am.ik.categolj.domain.common.serializer;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.util.StringUtils;

import am.ik.categolj.app.common.consts.Const;

public class CategorySerializer extends JsonSerializer<List<String>> {

    @Override
    public void serialize(List<String> value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {
        String text = StringUtils.collectionToDelimitedString(value,
                Const.CATEGORY_DELIM);
        jgen.writeString(text);
    }

}
