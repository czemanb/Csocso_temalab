package hu.bme.aut.freelancerandroid.ui.user.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import hu.bme.aut.freelancerandroid.LoginActivity

class SessionManager(context:Context) {

    val pref:SharedPreferences
    val editor: SharedPreferences.Editor
    val _context: Context
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "UserRepo"
    private val IS_LOGIN = "IsLoggedIn"

    val KEY_TOKEN = "Token"

    val KEY_ID= "ID"

    init {
        this._context = context
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun createLoginSession(token:String, id:Long) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_TOKEN,token)
        editor.putLong(KEY_ID, id)
        editor.commit()
    }

    fun getToken() :String? {
        return pref.getString(KEY_TOKEN,"token")
    }

    fun getId() :Long {
        return pref.getLong(KEY_ID,-1)
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
        val i = Intent(_context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        _context.startActivity(i)
    }

    fun isLoggedIn():Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }




}