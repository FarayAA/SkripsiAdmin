package com.example.skripsi_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.budiardian.moms.activity.session.SessionManager
import kotlinx.android.synthetic.main.activity_profil.*

class Profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        val session = SessionManager(this)
        val nama = session.getNamaUser()
        var email = session.getEmail()
        val no_hp = session.getNoHP()
        val alamat = session.getAlamat()

        txnama.text = nama
        txemail.text = email
        txnohp.text = no_hp
        txalamat.text = alamat
    }
}