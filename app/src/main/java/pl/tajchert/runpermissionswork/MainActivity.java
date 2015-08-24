package pl.tajchert.runpermissionswork;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PHONE = 121;

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
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callTest();
        } else {
            //We don't, lets ask for it. Result will be thrown in onRequestPermissionsResult()
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //Called when user presses "Allow" or "Deny" in permission request, array of permissions
        // and result is there as those request can be merged together - and then one result is
        // thrown for whole batch.
        boolean hasAccess;
        switch ( requestCode ) {
            case(REQUEST_CODE_PHONE):
                callTest();
                break;
            //Here goes other permissions, for example if you ask for Location  permission, here handle as well result
        }
    }

    private void callTest() {
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
