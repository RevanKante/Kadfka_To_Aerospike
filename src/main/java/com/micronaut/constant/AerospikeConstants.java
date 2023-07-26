package com.micronaut.constant;

public class AerospikeConstants {

    public static final String NAMESPACE = "bar";

    public static final class SETS {
        public static final String SF = "SFTOAEROSPIKE";
    }

    public static final class SF_BINS{
        public static final String ID = "ID";
        public static final String SID = "SID";
        public static final String NAME = "NAME";
        public static final String ADDRESS = "ADDRESS";
//        public static final String TIME = "TIME";
        public static final String METADATA$ACTION = "MDACTION";
        public static final String METADATA$ISUPDATE = "MDISUPDATE";
        public static final String METADATA$ROW_ID = "MDROWID";
    }
}
