package com.micronaut.service;

import com.micronaut.record.SFRecord;
import com.micronaut.repository.SFRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class SFService {
    @Inject
    SFRepository sfRepository;

    private static final Logger logger = LoggerFactory.getLogger(SFService.class);

    public void saveSFRecord(SFRecord sfRecord) {
        sfRepository.saveSFRecord(sfRecord);
        logger.info("Saved SF record to Aerospike for Sid :" + sfRecord.getSid());
    }

    public void updateSFRecord(SFRecord sfRecord) {
        sfRepository.updateSFRecord(sfRecord);
        logger.info("Updated SF record for Sid : " + sfRecord.getSid());
    }

    public void deleteSFRecord(String sid) {
        sfRepository.deleteSFRecord(sid);
        logger.info("Deleted SF record. Sid: " + sid);
    }

    public SFRecord getSFRecordBySid(String sid) {
        return sfRepository.getSFRecordBySid(sid);
    }

    public List<SFRecord> getSFRecordList() {
        return sfRepository.getSFRecordList();
    }
}
