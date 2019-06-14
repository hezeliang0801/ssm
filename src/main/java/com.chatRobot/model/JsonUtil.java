package com.chatRobot.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    public static ObjectMapper mapper = new ObjectMapper();
    public static <T> T getObjFromJson(String jsonContent, Class<T> classT) {
        if (String.class == classT) {
            return (T) jsonContent;
        }
        try {
            return mapper.readValue(jsonContent, classT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
