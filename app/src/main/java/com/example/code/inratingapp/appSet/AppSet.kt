package com.example.code.inratingapp.appSet

import android.content.Context
import com.orhanobut.hawk.Hawk

class AppSet(context: Context) {

    init {
        Hawk.init(context).build()
    }

    var currentPostId: String
        get() = Hawk.get(SET_KEY_CURRENT_POST_ID, "0")
        set(value) {
            Hawk.put(SET_KEY_CURRENT_POST_ID, value)
        }

}