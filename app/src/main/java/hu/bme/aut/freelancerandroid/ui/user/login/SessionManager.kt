package hu.bme.aut.freelancerandroid.ui.user.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import hu.bme.aut.freelancerandroid.MainActivity

class SessionManager(context:Context) {

    val pref:SharedPreferences
    val editor: SharedPreferences.Editor
    val _context: Context
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "UserRepo"
    private val IS_LOGIN = "IsLoggedIn"

    val KEY_TOKEN = "Token"

    val KEY_EXPIRESIN= "experesIn"

    init {
        this._context = context
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun createLoginSession(token:String, time:Int) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_TOKEN,token)
        editor.putInt(KEY_EXPIRESIN, time)
        editor.commit()
    }

    fun getToken() :String? {
        return pref.getString(KEY_TOKEN,"token")
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
        val i = Intent(_context, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        _context.startActivity(i)
    }

    private fun isLoggedIn():Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }




}