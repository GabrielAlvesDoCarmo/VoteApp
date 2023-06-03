package com.gdsdevtec.voteapp

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.message(msg :String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}