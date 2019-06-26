package gotopark.buster.lottogen.qrCodeReader;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;

import gotopark.buster.lottogen.R;

public class FullScannerFragmentActivity extends BaseScannerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("QR Code Reader");
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_full_scanner_fragment);
        //setupToolbar();
    }
}
