package com.mobile.api.test;

import wy.app.BuildConfig;
import wy.app.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import com.mobile.api.Device;
import com.mobile.api.Result;
import com.mobile.api.TestData;
import com.rest.adapter.SaturnAdapter;
import java.util.List;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk={21})
public class DeviceTest {
    private MainActivity activity;
    @Before public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void registerDevice() throws Exception {
        SaturnAdapter adapter = new SaturnAdapter.Builder().build(activity, "http://127.0.0.1:8080/mobile-deploy/api");
        Device device = adapter.create(Device.class);
        Result<List<TestData>> devices = device.register("android", "1", "s", "d", "form");

        if (devices != null) {
            for (TestData dataItem : devices.getData()){
                System.out.println("a :" + dataItem.getA());
                System.out.println("cookie :" + dataItem.getCookie());
                System.out.println("form :" + dataItem.getForm());
                System.out.println("zip :" + dataItem.getZip());
            }
            Assert.assertTrue(true);
            return;
        }

        System.out.println("devices == null");
        Assert.assertTrue(false);
    }
}
