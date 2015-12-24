package wy.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mobile.api.Device;
import com.mobile.api.Result;
import com.mobile.api.TestData;
import com.rest.adapter.SaturnAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SaturnAdapter adapter = new SaturnAdapter.Builder().build(MainActivity.this, "http://192.168.7.245:8080/mobile/api");
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

                        android.util.Log.v("yaowang", "xxx");

                        Snackbar.make(view, "Replace with your own action" + "a:" + a + "cookie:" + cookie + "form : " + form + "zip:" + zip, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
                thread.start();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
