package com.computer.inu.readit_appjam.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.computer.inu.readit_appjam.R
import kotlinx.android.synthetic.main.activity_mypage__setting_alarm.*

private val NOTIFICATION_PERMISSION_CODE = 123


class Mypage_Setting_alarm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage__setting_alarm)
        iv_mypage_setting_alarm_back_btn.setOnClickListener {
            finish()
        }

    }
}