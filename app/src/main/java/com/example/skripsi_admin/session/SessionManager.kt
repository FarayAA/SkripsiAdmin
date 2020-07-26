package com.budiardian.moms.activity.session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.skripsi_admin.MainActivity

class
SessionManager(val contextK: Context) {
    val key_name = "username"
    val id_level = "level"
//    val id_materi = "materi"
//    val id_submateri = "submateri"
//    val id_modul = "modul"


    private val pref_name = "Admin"
    private val is_login = "islogin"

    internal var pref: SharedPreferences? = null
    internal var editor: SharedPreferences.Editor? = null
    internal var context: Context? = null
    internal var mode = 0

    init {
        this.context = contextK
        pref = context!!.getSharedPreferences(pref_name, mode)
        editor = pref!!.edit()
    }

    fun createSession(username: String) {
        editor!!.putBoolean(is_login, true)
        editor!!.putString(id_level, username)
        editor!!.commit()
    }

    //set level
//
//    fun setReminder(data: String) {
//        editor!!.putString("dataGetReminder", data)
//        editor!!.commit()
//    }
//
//    fun getReminder(): String {
//        return pref!!.getString("dataGetReminder", "")
//    }
//
//    //setsubmateri
//    init {
//        this.context = contextK
//        pref = context!!.getSharedPreferences(pref_name, mode)
//        editor = pref!!.edit()
//
//    }
//
//
//    fun createSessionSubMateri(username: String) {
//        editor!!.putBoolean(is_login, true)
//        editor!!.putString(id_submateri, username)
//        editor!!.commit()
//    }
//
//    fun setSubMateri(data: String) {
//        editor!!.putString("dataSubMateri", data)
//        editor!!.commit()
//    }
//
//    fun getSubMateri(): String {
//        return pref!!.getString("dataSubMateri", "")
//    }
//    //setmateri
//
//    init {
//        this.context = contextK
//        pref = context!!.getSharedPreferences(pref_name, mode)
//        editor = pref!!.edit()
//    }
//
//    fun createSessionMateri(username: String) {
//        editor!!.putBoolean(is_login, true)
//        editor!!.putString(id_materi, username)
//        editor!!.commit()
//    }
//
//    fun setCekk(data: String) {
//        editor!!.putString("dataMateri", data)
//        editor!!.commit()
//    }
//
//    fun getCekk(): String {
//        return pref!!.getString("dataMateri", "")
//    }
//
//    //setmodul
//    init {
//        this.context = contextK
//        pref = context!!.getSharedPreferences(pref_name, mode)
//        editor = pref!!.edit()
//    }
//
//    fun createSessionModul(username: String) {
//        editor!!.putBoolean(is_login, true)
//        editor!!.putString(id_modul, username)
//        editor!!.commit()
//    }
//
//    fun setModul(data: String) {
//        editor!!.putString("dataModul", data)
//        editor!!.commit()
//    }
//
//    fun getModul(): String {
//        return pref!!.getString("dataModul", "")
//
//    }

    init {
        this.context = contextK
        pref = context!!.getSharedPreferences(pref_name, mode)
        editor = pref!!.edit()
    }

    fun createSessionLevel(id: String) {
        editor!!.putBoolean(is_login, true)
        editor!!.putString(key_name, id)
        editor!!.commit()
    }

    //set user
    fun setNama(
        id: String,
        nama: String,
        email: String,
        no_hp: String,
        alamat: String
    ) {
        editor!!.putString("id", id)
        editor!!.putString("nama", nama)
        editor!!.putString("email", email)
        editor!!.putString("no_hp", no_hp)
        editor!!.putString("alamat", alamat)
        editor!!.commit()
    }


    fun setId(id: String) {
        editor!!.putString("id", id)
        editor!!.commit()
    }



    fun checkLogin() {
        if (!this.isLogin()) {
            val i = Intent(context, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        } else {
            val i = Intent(context, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        }
    }

    fun isLogin(): Boolean {
        return pref!!.getBoolean(is_login, false)
    }





//    fun logoutUser() {
//        // Clearing all data from Shared Preferences
//        editor!!.clear()
//        editor!!.commit()
//
//        // After logout redirect user to Loing Activity
//        val i = Intent(context, Login_Activity::class.java)
//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//
//        context!!.startActivity(i)
//    }

    fun getId(): String {
        return pref!!.getString("id", "")!!
    }

    fun getNamaUser(): String {
        return pref!!.getString("nama", "")!!
    }

    fun getEmail(): String {
        return pref!!.getString("email", "")!!
    }
    fun getNoHP(): String {
        return pref!!.getString("no_hp", "")!!
    }
    fun getAlamat(): String {
        return pref!!.getString("alamat", "")!!
    }
}