package com.micronaut.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micronaut.constant.KafkaConstants;
import com.micronaut.record.SFRecord;
import com.micronaut.service.SFService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.OffsetStrategy;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(offsetReset = OffsetReset.LATEST, offsetStrategy = OffsetStrategy.DISABLED)
public class SFToAerospikeListener {

    private static final Logger logger = LoggerFactory.getLogger(SFToAerospikeListener.class);

    @Inject
    SFService sfService;

    @Topic(KafkaConstants.SFTOAEROSPIKETOPIC)
    public void receive(ConsumerRecord<String, String> record, long offset) {
        String jsonReq = record.value();

        if (jsonReq == null) {
            logger.info("Received null record from " + KafkaConstants.SFTOAEROSPIKETOPIC + " with offset: " + offset);
            return;
        }
        logger.info("Received message from " + KafkaConstants.SFTOAEROSPIKETOPIC + jsonReq);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonReq);

            String SFJson = jsonNode.get("payload").toString();
            SFRecord sfRecord = objectMapper.readValue(SFJson, SFRecord.class);

            if(sfRecord.isMetaDataIsUpdate()) {
                if (sfRecord.getMetaDataAction().equals("INSERT")) {
                    sfService.updateSFRecord(sfRecord);
                }
                else if (sfRecord.getMetaDataAction().equals("DELETE")) {
                    return;
                }
            }
            else {
                if (sfRecord.getMetaDataAction().equals("INSERT")) {
                    sfService.saveSFRecord(sfRecord);
                }
                else if (sfRecord.getMetaDataAction().equals("DELETE")) {
                    sfService.deleteSFRecord(sfRecord.getSid());
                }
            }
        }
        catch (NullPointerException e) {
            logger.error("error in json conversion from kafka message, SF json "+jsonReq + e);
        }
        catch (JsonProcessingException e) {
            logger.error("error in json processing from kafka message, SF json "+jsonReq + e);
        }
    }
}
