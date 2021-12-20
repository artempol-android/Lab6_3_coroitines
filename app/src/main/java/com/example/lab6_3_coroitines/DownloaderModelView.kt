package com.example.lab6_3_coroitines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class DownloaderModelView : ViewModel() {

    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()
    private val polytechURL= URL("https://ivteleradio.ru/images/2017/6/2/7accc16664a74ff9a6d92b432ae05561.jpg")

    fun downloadPicture() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Thread: ", Thread.currentThread().name)
            val picture = BitmapFactory.decodeStream(
                polytechURL.openConnection().getInputStream())

            withContext(Dispatchers.Main) {
                bitmap.value = picture
            }
        }
    }
}