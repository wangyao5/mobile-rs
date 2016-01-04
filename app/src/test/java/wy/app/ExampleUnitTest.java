package wy.app;

import wy.app.BuildConfig;
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

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class ExampleUnitTest {
    private MainActivity activity;
    @Before public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        SaturnAdapter adapter = new SaturnAdapter.Builder().build(activity, "http://192.168.7.245:8088/mobile-deploy/api");
        Device device = adapter.create(Device.class);
        Result<List<TestData>> devices = device.register("android", "1", "s", "d", "form");

        String a ="";
        String cookie ="";
        String form ="";
        String zip ="";
        if (devices != null) {
            a = devices.getData().get(0).getA();
            cookie = devices.getData().get(0).getCookie();
            form = devices.getData().get(0).getForm();
            zip = devices.getData().get(0).getZip();
        }
        android.util.Log.v("yaowang", "wangyaoxxx");
        System.out.println("wangyaoxxx");
    }
}
