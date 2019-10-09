package com.freedom.notey.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}


infix fun String.concat(other:String)="hello"

//Todo understand kotlin dsl core
@SuppressLint("ResourceType")
inline fun View.snack(@IntegerRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    snack(resources.getString(messageRes), length, f)
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

@SuppressLint("ResourceType")
fun Snackbar.action(@IntegerRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

inline fun<reified T:Any> newIntent(activity: Activity): Intent = Intent(activity,T::class.java)


inline fun <reified T:Any> Activity.launchActivity(
    requestCode:Int = -1,
    options: Bundle?=null,
    noinline init : Intent.()->Unit={}){

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
        startActivity(intent, options)
    }else
        startActivity(intent)

}

fun logs(message: String){
    Log.d("tag:Debug",message)
}


//SharedPrefence Extention Method
inline fun <reified T:Any> SharedPreferences.saveData(Key:String,value:T){
    val editor = this.edit()
    when (T::class) {
        Boolean::class -> editor.putBoolean(Key, value as Boolean)
        Float::class -> editor.putFloat(Key, value as Float)
        String::class -> editor.putString(Key, value as String)
        Int::class -> editor.putInt(Key, value as Int)
        Long::class -> editor.putLong(Key, value as Long)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(Key, value as Set<String>)
            }
        }
    }
    editor.commit()
}

inline fun <reified T> SharedPreferences.getData(Key:String,defaultValue:T):T
{
    when(T::class){
        Boolean::class->return this.getBoolean(Key,defaultValue as Boolean) as T
        Float::class->return this.getFloat(Key,defaultValue as Float) as T
        String::class->return this.getString(Key,defaultValue as String) as T
        Int::class->return this.getInt(Key,defaultValue as Int) as T
        Long::class->return this.getLong(Key,defaultValue as Long) as T
        else->{
            if (defaultValue is Set<*>){
                return this.getStringSet(Key,defaultValue as Set<String>) as T
            }
        }
    }
    return defaultValue
}
