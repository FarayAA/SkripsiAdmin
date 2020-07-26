package com.example.skripsi_admin

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.skirpsi.network.ApiCall
import com.example.skirpsi.network.ApiInterface
import com.example.skripsi_admin.model.DataEvent
import com.example.skripsi_admin.model.DataPostEvent
import com.example.skripsi_admin.model.ResponsePostEvent
import kotlinx.android.synthetic.main.activity_add__event.*
import kotlinx.android.synthetic.main.activity_edit__event.*
import kotlinx.android.synthetic.main.activity_edit__event.txdesk
import kotlinx.android.synthetic.main.activity_edit__event.txjdl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Edit_Event : AppCompatActivity() {
    private var datePickerDialog: DatePickerDialog? = null
    private var dateFormatter: SimpleDateFormat? = null
    var keluhan: DataEvent? = null

    companion object {
        const val EXTRA_UPDATEKELUHAN = "EXTRA_UPDATEKELUHAN"
        const val STATE = "state"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit__event)
        keluhan = intent.getParcelableExtra(EXTRA_UPDATEKELUHAN)

        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        tgl.setText(keluhan!!.tanggal)
        txdesk.setText(keluhan!!.deskripsi)
        txjdl.setText(keluhan!!.namaEvent)

        tlglbtn.setOnClickListener {
            showDateDialog()
        }

        tbledit.setOnClickListener {
            requesttambah()
        }
    }
    private fun requesttambah() {


        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann=intent?.getStringExtra("id_event").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponsePostEvent> = apiInterface.editevent(idjann,txjdl.text.toString(),txdesk.text.toString(),tgl.text.toString())
        call.enqueue(object : Callback<ResponsePostEvent> {

            override fun onFailure(call: Call<ResponsePostEvent>?, t: Throwable?) {
                Toast.makeText(applicationContext, "gagal mendaftar", Toast.LENGTH_SHORT).show()
                if (t != null) {
                    Log.i("autolog", "t: " + t.message)
                }
                if (t != null) {
                    Log.i("autolog", "t: " + t.getLocalizedMessage())
                }
            }

            override fun onResponse(call: Call<ResponsePostEvent>?, response: Response<ResponsePostEvent>?) {


                Toast.makeText(this@Edit_Event, "Berhasil Update", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Edit_Event,Detail_Event::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun showDateDialog() {

        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, dayOfMonth, monthOfYear, year ->
                var newDate = Calendar.getInstance()
                newDate.set( dayOfMonth,monthOfYear,year)

                tgl!!.text = dateFormatter?.format(newDate.getTime())
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )

        datePickerDialog!!.show()
    }

}