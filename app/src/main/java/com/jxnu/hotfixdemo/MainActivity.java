package com.jxnu.hotfixdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jxnu.hotfixlib.HotFix;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView tv_info;
    private Button btn_test,btn_patch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_info = (TextView) findViewById(R.id.tv_info);
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_patch = (Button) findViewById(R.id.btn_patch);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_info.setText(new BugClass().getInfo());
            }
        });
        btn_patch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dexPath = new File(getDir("dex", Context.MODE_PRIVATE), "patch_dex.jar");
                Utils.prepareDex(MainActivity.this, dexPath, "patch_dex.jar");
                HotFix.patch(MainActivity.this, dexPath.getAbsolutePath(), "com.jxnu.hotfixdemo.BugClass");
            }
        });
    }
}
