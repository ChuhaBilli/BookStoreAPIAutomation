package com.utils.API;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DynamicJsonGenerator {
	
    private static final Logger logger = LoggerFactory.getLogger(DynamicJsonGenerator.class);


    static String inclusionCheck(Object bodyParam, inclusion flag) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            
            logger.info("Json generation with flag "+flag);
            switch (flag) {
                case EXCLUDE_NULL:
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    return (mapper.writeValueAsString(bodyParam));
                case EXCLUDE_EMPTY:
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                    return (mapper.writeValueAsString(bodyParam));
                case INCLUDE_ALL:
                    mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
                    return (mapper.writeValueAsString(bodyParam));
                case EXCLUDE_ALL:
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                    return (mapper.writeValueAsString(bodyParam));
                default:
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    return (mapper.writeValueAsString(bodyParam));
            }
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
            logger.error(jpe.toString());
            return null;
        }
    }

    public enum inclusion {
        EXCLUDE_NULL,
        EXCLUDE_EMPTY,
        INCLUDE_ALL,
        EXCLUDE_ALL
    } ;
}
