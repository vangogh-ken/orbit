package org.cc.automate.config;


import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

 
/**
 * 
 * JSONBinder
 *
 * @Description: json与Obj互相转换
 * @author leon.gan
 *
 */
public final class JSONBinder<T> {
     
    private final Class<T> beanClass;
 
    private final Class<?>[] elementClasses;
     
    private JSONBinder(Class<T> beanClass, Class<?>... elementClasses) {
        this.beanClass = beanClass;
        this.elementClasses = elementClasses;
    }
     
    public static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
     
    public static <T> JSONBinder<T> binder(Class<T> beanClass, Class<?>... elementClasses) {
        return new JSONBinder<T>(beanClass, elementClasses);
    }
 
    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DATETIME);
        //mapper.setSerializationConfig(mapper.getSerializationConfig().withDateFormat(dateFormat));
        //mapper.setDeserializationConfig(mapper.getDeserializationConfig().withDateFormat(dateFormat));
        //mapper.setAnnotationIntrospector(new JaxbAnnotationIntrospector());
        //mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        
        //mapper.setse.setSerializerProvider(mapper.getDeserializationConfig().with(df)).setSerializationConfig(mapper.getSerializationConfig().with(dateFormat));
        mapper.setConfig(mapper.getDeserializationConfig().with(dateFormat));
        mapper.setConfig(mapper.getSerializationConfig().with(dateFormat));
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        return mapper;
    }
 
    public T fromJSON(String json) {
        ObjectMapper mapper = createMapper();
        try {
            return elementClasses == null || elementClasses.length == 0 ? mapper.readValue(json, beanClass) : fromJSONToGeneric(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
 
    private T fromJSONToGeneric(String json) throws IOException {
        ObjectMapper mapper = createMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(beanClass, elementClasses));
    }
 
    public String toJSON(T object) {
        ObjectMapper mapper = createMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}