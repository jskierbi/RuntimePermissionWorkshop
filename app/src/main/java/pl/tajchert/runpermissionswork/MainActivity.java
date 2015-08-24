package pl.tajchert.runpermissionswork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.main_layout)
    View mLayout;//We will use it later

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonCall)
    public void clickButtCall() {
        //TODO Ok so when targeting API=23 we will not have "out of the box" permission to do stuff.
        //Lets try ask user for it!
        //Try yourself, if not move to next commit
        //Useful methods: ActivityCompat.checkSelfPermission(), ActivityCompat.requestPermissions(),
        //and remember to handle result in onRequestPermissionsResult()

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "11122233"));
        try {
            startActivity(intent);
        } catch (SecurityException e) {
            Toast.makeText(this, "We do not have permission, abort", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "clickButtCall lack of permission: " + e.getLocalizedMessage());
        }
    }
}
