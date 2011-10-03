package am.ik.categolj.serializer;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Category;

public class CategorySerializer extends JsonSerializer<List<Category>> {

    @Override
    public void serialize(List<Category> value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        for (Category c : value) {
            if (c.getIndex() > 1) {
                sb.append(Const.CATEGORY_DELIM);
            }
            sb.append(c.getName());
        }
        jgen.writeString(sb.toString());
    }

}
