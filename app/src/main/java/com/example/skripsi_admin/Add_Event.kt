package com.example.skripsi_admin

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.skirpsi.network.ApiCall
import com.example.skirpsi.network.ApiInterface
import com.example.skripsi_admin.model.ResponseEvent
import com.example.skripsi_admin.model.ResponsePostEvent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add__event.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Add_Event : AppCompatActivity() {
    private var datePickerDialog: DatePickerDialog? = null
    private var dateFormatter: SimpleDateFormat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__event)
        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        btnadd.setOnClickListener {
            addevent()

        }
        tgtlglbtn.setOnClickListener {
            showDateDialog()
        }
    }

    fun addevent() {
//        val nama_event = RequestBody.create(MediaType.parse("text/plain"), txjdl.text.toString())
//        val deskripsi = RequestBody.create(MediaType.parse("text/plain"), txdesk.text.toString())
//        val tanggal = RequestBody.create(MediaType.parse("text/plain"), txtgl.text.toString())
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val call: Call<ResponsePostEvent> = apiInterface.addevent(txjdl.text.toString(),txdesk.text.toString(),txtgl.text.toString())
        call.enqueue(object : Callback<ResponsePostEvent> {
            override fun onFailure(call: Call<ResponsePostEvent>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("add", "t: " + t.message)

                }
                if (t != null) {
                    Log.i("add", "t: " + t.getLocalizedMessage())
                }
            }
            override fun onResponse(call: Call<ResponsePostEvent>?, response: Response<ResponsePostEvent>?) {
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("add", "t: $json")
                        Toast.makeText(this@Add_Event, "Berhasil Daftar", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Add_Event,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@Add_Event, "Gagal Daftar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    private fun showDateDialog() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view,dayOfMonth , monthOfYear,year ->
                var newDate = Calendar.getInstance()
                newDate.set( dayOfMonth,monthOfYear,year)

                txtgl!!.text = dateFormatter?.format(newDate.getTime())
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )

        datePickerDialog!!.show()
    }
}