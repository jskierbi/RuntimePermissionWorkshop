package pl.tajchert.runpermissionswork;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionListener;

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
        Nammu.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Lets check what permission were added/removed since last time user looked at us
        //(maybe he went to settings and left us permissionless?!)
        Nammu.permissionCompare(new PermissionListener() {
            @Override
            public void permissionsChanged(String s) {
                Log.d(TAG, "permissionsChanged : " + s);
                Toast.makeText(MainActivity.this, "Permission changed: " + s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void permissionsGranted(String s) {
                Log.d(TAG, "permissionsGranted : " + s);
                Toast.makeText(MainActivity.this, "Permission granted: " + s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void permissionsRemoved(String s) {
                Log.d(TAG, "permissionsRemoved : " + s);
                Toast.makeText(MainActivity.this, "Permission removed: " + s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.buttonCall)
    public void clickButtCall() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callTest();
        } else {
            //We don't, lets ask for it. Result will be thrown in onRequestPermissionsResult()
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                //User already refused to give us this permission or removed it
                //Now he/she can mark "never ask again" (sic!), so we better explain why we need it
                Snackbar.make(mLayout, "Here we explain to user why we neeed to use call feature",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_PHONE);
                            }
                        })
                        .show();
            } else {
                // We do not need to explain - first time asking for permission
                // or phone doesn't offer permission
                // or user marked "never ask again"
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_PHONE);
            }
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
