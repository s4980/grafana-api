package com.s4980.commons.annotators;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.codemodel.*;
import org.jsonschema2pojo.AbstractAnnotator;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Jackson2CustomAnnotator extends AbstractAnnotator {

    @Override
    public void propertyOrder(JDefinedClass clazz, JsonNode propertiesNode) {
        JAnnotationArrayMember annotationValue = clazz.annotate(JsonPropertyOrder.class).paramArray("value");
        Iterator properties = propertiesNode.fieldNames();

        while (properties.hasNext()) {
            annotationValue.param((String) properties.next());
        }
    }

    @Override
    public void propertyInclusion(JDefinedClass clazz, JsonNode schema) {
        clazz.annotate(JsonInclude.class).param("value", JsonInclude.Include.ALWAYS);
        clazz.annotate(JsonTypeInfo.class).param("use", JsonTypeInfo.Id.CLASS).param("include", JsonTypeInfo.As.WRAPPER_OBJECT);
    }

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        field.annotate(JsonProperty.class).param("value", propertyName);
        if (field.type().erasure().equals(field.type().owner().ref(Set.class))) {
            field.annotate(JsonDeserialize.class).param("as", LinkedHashSet.class);
        }

        if (propertyNode.has("javaJsonView")) {
            field.annotate(JsonView.class).param("value", field.type().owner().ref(propertyNode.get("javaJsonView").asText()));
        }

    }

    @Override
    public void propertyGetter(JMethod getter, String propertyName) {
        getter.annotate(JsonProperty.class).param("value", propertyName);
    }

    @Override
    public void propertySetter(JMethod setter, String propertyName) {
        setter.annotate(JsonProperty.class).param("value", propertyName);
    }

    @Override
    public void anyGetter(JMethod getter) {
        getter.annotate(JsonAnyGetter.class);
    }

    @Override
    public void anySetter(JMethod setter) {
        setter.annotate(JsonAnySetter.class);
    }

    @Override
    public void enumCreatorMethod(JMethod creatorMethod) {
        creatorMethod.annotate(JsonCreator.class);
    }

    @Override
    public void enumValueMethod(JMethod valueMethod) {
        valueMethod.annotate(JsonValue.class);
    }

    @Override
    public void enumConstant(JEnumConstant constant, String value) {
    }

    @Override
    public boolean isAdditionalPropertiesSupported() {
        return true;
    }

    @Override
    public void additionalPropertiesField(JFieldVar field, JDefinedClass clazz, String propertyName) {
        field.annotate(JsonIgnore.class);
    }
}
