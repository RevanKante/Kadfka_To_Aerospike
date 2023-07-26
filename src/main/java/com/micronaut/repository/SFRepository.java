package com.micronaut.repository;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.mapper.tools.AeroMapper;
import com.micronaut.record.SFRecord;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class SFRepository {
    @Inject
    AeroMapper aeroMapper;

    private static final Logger logger = LoggerFactory.getLogger(SFRepository.class);

    public void saveSFRecord(SFRecord sfRecord) {
        try {
            aeroMapper.save(sfRecord);
        }catch (AerospikeException ae) {
            logger.error("Error in saving SF record for Sid = "+ sfRecord.getSid() +" "+ ae);
        }
    }

    public void updateSFRecord(SFRecord sfRecord) {
        try {
            WritePolicy updatePolicy = new WritePolicy();
            updatePolicy.recordExistsAction = RecordExistsAction.REPLACE;
            aeroMapper.save(updatePolicy,sfRecord);
        }catch (AerospikeException ae) {
            logger.error("Error in updating SF record for Sid = "+ sfRecord.getSid() +" "+ ae);
        }
    }

    public void deleteSFRecord(String sid) {
        try {
            WritePolicy deletePolicy = new WritePolicy();
            deletePolicy.durableDelete = true;
            aeroMapper.delete(deletePolicy,SFRecord.class, sid);
        }catch (AerospikeException ae) {
            logger.error("Error while deleting SF record for sid = "+ sid +" "+ae);
        }
    }

    public SFRecord getSFRecordBySid(String sid) {
        try {
            return aeroMapper.read(SFRecord.class, sid);
        }catch (AerospikeException ae){
            logger.error("Error in getting SF record. Sid: " + sid, ae);
            return null;
        }
    }

    public List<SFRecord> getSFRecordList() {
        try {
            return aeroMapper.scan(SFRecord.class);
        }
        catch (AerospikeException ae){
            logger.error("Error in getting SF records List "+ ae);
            return null;
        }
    }
}
