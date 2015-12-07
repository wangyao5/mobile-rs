package com.mobile.api.impl;


import com.mobile.api.Device;

import org.springframework.stereotype.Component;

import javax.inject.Named;

@Named("deviceImpl")
@Component
public class DeviceImpl implements Device {
    @Override
    public String register(String id) {
        return "id : " + id;
    }
}
