package com.example.skripsi_admin

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.budiardian.moms.activity.session.SessionManager
import com.example.skirpsi.network.ApiCall
import com.example.skirpsi.network.ApiInterface
import com.example.skripsi_admin.model.ResponseLogin
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login_.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_Activity : AppCompatActivity() {
    var sessionManager: SessionManager? = null
    lateinit var proges: ProgressDialog
    var mContex: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        sessionManager = SessionManager(applicationContext)
        mContex = this
        if (sessionManager!!.isLogin()) {
            startActivity(Intent(this@Login_Activity, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }

        btnlogin.setOnClickListener {

            var username = etUsername.text.toString()
            var password = etPassword.text.toString()
            if (username.trim({ it <= ' ' }).length > 0 && password.trim({ it <= ' ' }).length > 0) {
                requestLogin()
            } else {
                submitForm()

            }

        }
    }

    private fun requestLogin() {
        proges = ProgressDialog(this)
        proges.setMessage("loading")
        proges.show()
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call: Call<ResponseLogin> = apiInterface.loginKomuntias(etUsername.text.toString(), etPassword.text.toString())
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onFailure(call: Call<ResponseLogin>?, t: Throwable?) {
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
                Log.i("autolog", "t: " + t!!.message);
                Log.i("autolog", "t: " + t!!.getLocalizedMessage());

            }
            override fun onResponse(call: Call<ResponseLogin>?, response: Response<ResponseLogin>?) {
                proges.dismiss()
                if(response!!.body()!!.status!!){
                    val json = Gson().toJson(response)
                    Log.i("autolog","t: "+json);
                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show()
                    sessionManager!!.createSession(response.body()!!.dataLogin!!.id!!)
                    sessionManager!!.setNama(
                        response.body()!!.dataLogin!!.id!!,
                        response.body()!!.dataLogin!!.nama!!,
                        response.body()!!.dataLogin!!.email!!,
                        response.body()!!.dataLogin!!.noHp!!,
                        response.body()!!.dataLogin!!.alamat!!
                    )
                    sessionManager!!.setId(response.body().dataLogin!!.id!!)
                    val intent = Intent(this@Login_Activity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                else run{
                    Toast.makeText(this@Login_Activity, "Gagal", Toast.LENGTH_SHORT).show();

                }
            }
        })

    }

    private fun submitForm() {

        if (!validateUser()) {
            return
        }

        if (!validatePassword()) {
            return
        }

    }

    private fun validateUser(): Boolean {
        if (etUsername.text.toString().trim({ it <= ' ' }).isEmpty()) {
            etUsername.setError(getString(R.string.eror_msg_user))
            requestFocus(etUsername)
            return false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        if (etPassword.text.toString().trim({ it <= ' ' }).isEmpty()) {
            etPassword.setError(getString(R.string.eror_msg_pass))
            requestFocus(etPassword)
            return false
        }

        return true
    }

    private fun requestFocus(view: View) {
        if (view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }
}