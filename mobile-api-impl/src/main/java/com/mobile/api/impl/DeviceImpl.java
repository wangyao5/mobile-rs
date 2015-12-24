package com.mobile.api.impl;


import com.mobile.api.TestData;
import com.mobile.api.Device;
import com.mobile.api.Result;
import com.mobile.api.Status;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Named("deviceImpl")
@Component
public class DeviceImpl implements Device {
    @Override
    public Result<List<TestData>> register(String agent, String id, String sessionid, String zip, String form) {
        Result<List<TestData>> result = new Result<>();
        result.setStatus(Status.OK.ordinal());
        List<TestData> list = new ArrayList<>();
        TestData a = new TestData();
        a.setA("a" + agent);
        a.setCookie("cookie:" + sessionid);
        a.setZip(zip);
        a.setForm("form:"+form);
        list.add(a);
        result.setData(list);
        return result;
    }
}
