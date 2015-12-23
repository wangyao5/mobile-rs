package com.mobile.api.impl;


import com.mobile.api.A;
import com.mobile.api.Device;
import com.mobile.api.Result;
import com.mobile.api.Status;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Named("deviceImpl")
@Component
public class DeviceImpl implements Device {
    @Override
    public Result<List<A>> register(String agent, String id, String sessionid, String zip) {
        Result<List<A>> result = new Result<>();
        result.setStatus(Status.OK.ordinal());
        List<A> list = new ArrayList<>();
        A a = new A();
        a.setA("a");
        list.add(a);
        result.setData(list);
        return result;
    }
}
