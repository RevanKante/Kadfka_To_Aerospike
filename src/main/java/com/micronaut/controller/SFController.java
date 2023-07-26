package com.micronaut.controller;

import com.micronaut.record.SFRecord;
import com.micronaut.service.SFService;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/")
public class SFController {

    @Inject
    SFService sfService;

    @Get("/getpayloadbysid/{sid}")
    public SFRecord getEmployeeRecordById(@PathVariable String sid) {
        return sfService.getSFRecordBySid(sid);
    }

    @Post("/savepayload")
    public void createPayload(@Body SFRecord sfRecord) {
        sfService.saveSFRecord(sfRecord);
    }

    @Put("/updatepayload")
    public void updateEmpRecord(@Body SFRecord sfRecord) {
        sfService.updateSFRecord(sfRecord);
    }

    @Delete("deletepayload/{sid}")
    public void deletePayload(@PathVariable String sid) {
        sfService.deleteSFRecord(sid);
    }

    @Get("/getallpayloads")
    public List<SFRecord> getSFRecordList() {
        return sfService.getSFRecordList();
    }
}
