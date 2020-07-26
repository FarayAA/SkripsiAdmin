package com.example.skripsi_admin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataEvent(

	@field:SerializedName("id_event")
	val idEvent: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("nama_event")
	val namaEvent: String? = null
) : Parcelable