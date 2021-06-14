package com.zz.ndkffmpeg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zz.ndkffmpeg.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

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