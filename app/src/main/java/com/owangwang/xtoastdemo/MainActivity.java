package com.owangwang.xtoastdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XToast.create(MainActivity.this).setMessage("Testing:This is a XToast....")
                        .setmShowAnimationType(AnimationUtils.ANIMATION_DRAWER)
                        .setmDuration(XToast.XTOAST_SHORT)
                        .setmOnDisappearListener(new XToast.OnDisappearListener() {
                            @Override
                            public void onDisappear(XToast xToast) {
                                Log.d("cylog","The XToast has disappeared..");
                            }
                        }).show();
            }
        });

    }
}
