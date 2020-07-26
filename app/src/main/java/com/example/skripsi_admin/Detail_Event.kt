package com.example.skripsi_admin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skirpsi.network.ApiCall
import com.example.skirpsi.network.ApiInterface
import com.example.skripsi.adapter.EventListAdapter
import com.example.skripsi_admin.model.DataEvent
import com.example.skripsi_admin.model.ResponsePostEvent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail__event.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail_Event : AppCompatActivity() {

    var event: DataEvent? = null
//    var imagee: ImageView? = null

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail__event)
        event = intent.getParcelableExtra(Detail_Event.EXTRA_MOVIE)
        getMovieData(
            event!!.namaEvent!!.toString(),
            event!!.deskripsi!!.toString(),
            event!!.tanggal!!.toString()
        )
//        val actionBar = supportActionBar
//        actionBar!!.title = "Detail Event"
//        actionBar.setDisplayHomeAsUpEnabled(true)
//        actionBar.setDisplayHomeAsUpEnabled(true)
        btndel.setOnClickListener {
            requestDelete()
        }
        btnedit.setOnClickListener {
            val intent = Intent(this, Edit_Event::class.java)
            intent.putExtra("id_event",event?.idEvent)
            intent.putExtra(Edit_Event.EXTRA_UPDATEKELUHAN, event)
            startActivity(intent)
        }
    }

    private fun getMovieData(judul: String, deskripsi: String, tanggal: String) {

        txjdl.text = judul
        txdesk.text = deskripsi
        txtgl.text = tanggal
//        Glide.with(this).load(ApiCall.ImageUrl + image).into(imagedetailkeluhan)

    }
    private fun requestDelete() {

        val data = intent
        val iddata = data.getStringExtra("id_event")
        val apiInterface: ApiInterface = ApiCall().client().create(ApiInterface::class.java)
        val idjann=intent?.getStringExtra("id_event").toString()
        Log.i("autolog", "t: " + idjann)
        val call: Call<ResponsePostEvent> = apiInterface.deleteevent(iddata)
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
                if (response != null) {
                    if (response.body()!!.status === true) {
                        val json = Gson().toJson(response)
                        Log.i("autolog", "t: $json")

                        Toast.makeText(this@Detail_Event, "Berhasil Hapuss", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@Detail_Event, "Gagal Daftar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
