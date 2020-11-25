package httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

//clasa folosita pt a crea functionalitate pt read si write htpp.Json

public class Json {


    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); //parsing will not crash in case of one property missing
        return om;
    }

    //parse (parcurg/ procesez )a jason string -> json node -> container pt elemente inauntrul unui json stream
    public static JsonNode parse(String jsonSrc) throws IOException {     // json String --> json Node
        return myObjectMapper.readTree(jsonSrc);
    }

    //mutam json node in configuration
    //ii dam ca parametru nodul pe care il transformam in clasa  in care vrem sa l transformam pe care o dam ca parametru
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException
    {
        return myObjectMapper.treeToValue(node,clazz);
    }

    //get this configuration file into a json node
    private static JsonNode toJson(Object obj)
    {
        return myObjectMapper.valueToTree(obj);
    }


    private static String stringify(JsonNode node) throws JsonProcessingException
    {
        return generateJson(node,false);
    }

    private static String stringifyPretty(JsonNode node) throws JsonProcessingException
    {
        return generateJson(node,true);
    }

    //to be able to see a json node in string format
    private static String generateJson(Object o,boolean pretty) throws JsonProcessingException
    {
        //pt serialization the process of translating a data structure or object state into a format that can be stored
        ObjectWriter objectWriter=myObjectMapper.writer();
        if(!pretty)
        {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }

}
