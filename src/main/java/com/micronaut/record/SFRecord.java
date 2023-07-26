package com.micronaut.record;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micronaut.constant.AerospikeConstants;
import com.micronaut.constant.JsonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AerospikeRecord(namespace = AerospikeConstants.NAMESPACE, set = AerospikeConstants.SETS.SF)
public class SFRecord {

    @AerospikeKey
    @JsonProperty(JsonConstants.SF_JSON.SID)
    @AerospikeBin(name = AerospikeConstants.SF_BINS.SID)
    private String sid;

    @JsonProperty(JsonConstants.SF_JSON.ID)
    @AerospikeBin(name = AerospikeConstants.SF_BINS.ID)
    private int id;

    @JsonProperty(JsonConstants.SF_JSON.NAME)
    @AerospikeBin(name = AerospikeConstants.SF_BINS.NAME)
    private String name;

    @JsonProperty(JsonConstants.SF_JSON.ADDRESS)
    @AerospikeBin(name = AerospikeConstants.SF_BINS.ADDRESS)
    private String address;

//    @JsonProperty(JsonConstants.SF_JSON.TIME)
//    @AerospikeBin(name = AerospikeConstants.SF_BINS.TIME)
//    private long time;

    @JsonProperty(JsonConstants.SF_JSON.METADATA$ACTION)
    @AerospikeBin(name = AerospikeConstants.SF_BINS.METADATA$ACTION)
    private String metaDataAction;

    @JsonProperty(JsonConstants.SF_JSON.METADATA$ISUPDATE)
    @AerospikeBin(name = AerospikeConstants.SF_BINS.METADATA$ISUPDATE)
    private boolean metaDataIsUpdate;

    @JsonProperty(JsonConstants.SF_JSON.METADATA$ROW_ID)
    @AerospikeBin(name = AerospikeConstants.SF_BINS.METADATA$ROW_ID)
    private String metaDataRowId;
}
