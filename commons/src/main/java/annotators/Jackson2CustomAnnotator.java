package annotators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import org.jsonschema2pojo.Jackson2Annotator;

/**
 * Created by MZ on 2016-01-31.
 */
public class Jackson2CustomAnnotator extends Jackson2Annotator {

    @Override
    public void propertyInclusion(JDefinedClass clazz, JsonNode schema) {
        clazz.annotate(JsonInclude.class).param("value", JsonInclude.Include.ALWAYS);
        clazz.annotate(JsonRootName.class).param("value", clazz.getClass().getSimpleName().toLowerCase());
    }
}
