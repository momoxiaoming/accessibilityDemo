package com.mmo.accessibilitydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.auto.assist.accessibility.util.ApiUtil;
import com.mmo.accessibilitydemo.script.ToNetPageScript;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (ApiUtil.isAccessibilityServiceOn(UiApplication.context, MainAccessService.class))
                {
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ToNetPageScript.doWrok();

                        }
                    }).start();
                } else
                {

                    Toast.makeText(MainActivity.this, "请开启辅助功能", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
