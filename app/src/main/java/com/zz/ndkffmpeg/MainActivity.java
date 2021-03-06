package com.zz.ndkffmpeg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zz.ndkffmpeg.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView libinfoText = binding.sampleText;


        Button configurationButton = (Button) this.findViewById(R.id.button_configuration);
        Button urlprotocolButton = (Button) this.findViewById(R.id.button_urlprotocol);
        Button avformatButton = (Button) this.findViewById(R.id.button_avformat);
        Button avcodecButton = (Button) this.findViewById(R.id.button_avcodec);
        Button avfilterButton = (Button) this.findViewById(R.id.button_avfilter);
        videoView = (VideoView) this.findViewById(R.id.video_view);

        libinfoText.setText(configurationinfo());

        urlprotocolButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                libinfoText.setText(urlprotocolinfo());
            }
        });

        avformatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                libinfoText.setText(avformatinfo());
            }
        });

        avcodecButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                libinfoText.setText(avcodecinfo());
            }
        });

        avfilterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                libinfoText.setText(avfilterinfo());
            }
        });

        configurationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                libinfoText.setText(configurationinfo());
            }
        });

        initPermission();
    }

    List<String> mPermissionList = new ArrayList<>();

    private void initPermission() {

        mPermissionList.clear();//???????????????????????????

        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        //?????????????????????????????????????????????
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//???????????????????????????
            }
        }

        //????????????
        if (mPermissionList.size() > 0) {
            //????????????????????????????????????
            ActivityCompat.requestPermissions(this, permissions, 500);
        } else {
            //????????????????????????????????????????????????????????????
        }
    }

    public void startPlayVideo(View view) {
        String path = MainActivity.this.getExternalFilesDir(null).getPath();
        String outFileName = path + "/12345.mp4";
        videoView.player(outFileName);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String getFfmpegInfo();


    //JNI
    public native String urlprotocolinfo();

    public native String avformatinfo();

    public native String avcodecinfo();

    public native String avfilterinfo();

    public native String configurationinfo();


}