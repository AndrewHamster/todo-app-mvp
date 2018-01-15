package com.homiak.andrii.todomvp.util

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast

/**
 * Created by Andre on 13-Jan-18.
 */
fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Fragment.toast(msg: String) = activity.toast(msg)
fun AppCompatActivity.setupToolbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
}
