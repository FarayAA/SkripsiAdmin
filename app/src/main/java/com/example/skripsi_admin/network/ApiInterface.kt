package com.example.skirpsi.network


import com.example.skripsi_admin.model.ResponseEvent
import com.example.skripsi_admin.model.ResponseLogin
import com.example.skripsi_admin.model.ResponsePostEvent
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
//    //untuk Login
    @FormUrlEncoded
    @POST("loginkomunitas")
    fun loginKomuntias(
        @Field("nama") nama: String,
        @Field("password") password: String
    ): Call<ResponseLogin>
//
//    //mendaftar
//    //  @FormUrlEncoded
//    @Multipart
//    @POST("register")
//    fun daftarUser(
//        @Part("nama_lengkap") nama_lengkap: RequestBody,
//        @Part("username") username: RequestBody,
//        @Part("password") password: RequestBody,
//        @Part("tgl_lahir") tgl_lahir: RequestBody,
//        @Part("telepon") telepon: RequestBody
//        // @Part image : MultipartBody.Part
//
//    ): Call<ResponseRegister>

//    @Multipart
//    @POST("barang/tambah")
//    fun addProduk(@PartMap param: Map<String, @JvmSuppressWildcards RequestBody>, @Part foto: MultipartBody.Part): Observable<Response<ResponseAkik>>
//
//    @POST("pembayaran/{id}/status/terkonfirmasi")
//    fun updateOrderStatus(@Path("id") id: String): Call<ResponseTambahPemeriksaan>

//    @FormUrlEncoded
//    @POST("cekkesehatan")
//    fun tambahperiksa(
//        @Field("id_moms") id_moms: String,
//        @Field("tgl_pemeriksaan") tgl_pemeriksaan: String,
//        @Field("berat_badan") berat_badan: String,
//        @Field("tekanan_darah") tekanan_darah: String,
//        @Field("tinggi_fundus") tinggi_fundus: String,
//        @Field("denyut_jantung_janin") denyut_jantung_janin: String,
//        @Field("lingkar_lengan_atas") lingkar_lengan_atas: String,
//        @Field("keluhan") keluhan: String
//    ): Call<ResponseTambahPemeriksaan>
//
    @GET("event")
    fun getevent(): Call<ResponseEvent>

    @DELETE("event/{id_event}")
    fun deleteevent(@Path("id_event")id_event : String): Call<ResponsePostEvent>

    @FormUrlEncoded
    @POST("event")
    fun addevent(
//        @Field("id_event") id_event: String,
        @Field("nama_event") nama_event: String,
        @Field("deskripsi") deskripsi: String,
        @Field("tanggal") tanggal: String
    ): Call<ResponsePostEvent>

    @FormUrlEncoded
    @PUT("event/id_event}")
    fun editevent(
        @Field("id_event") id_event: String,
        @Field("nama_event") nama_event: String,
        @Field("deskripsi") deskripsi: String,
        @Field("tanggal") tanggal: String
    ): Call<ResponsePostEvent>
//
//    @GET("soal/{id}")
//    fun getsoal(@Path("id")id:String): Call<ResponseSoal>
//
//    @GET("soalmateri/{id}")
//    fun getsoalmateri(@Path("id")id:String): Call<ResponseSoalMateri>
//
//    @GET("level")
//    fun getlevel(): Call<ResponseLevel>
//

//    @GET("register")
//    fun getregis(): Call<ResponseIbuHamil>
//
//    @GET("janin")
//    fun getBayi(): Call<ResponseJanin>
//
//    @GET("keluhan")
//    fun getKeluhan(): Call<ResponseKeluhan>
//
//
//    @GET("cekkesehatan/{id}")
//    fun getCek(@Path("id") id: String): Call<ResponseCekKesehatan>
//
//    @GET("submateri/{id}")
//    fun getsubmateri(@Path("id")id: String): Call<ResponseSubmateri>
//
//    @GET("penilaian/{id}")
//    fun getnilai(@Path("id") id: String): Call<ResponsePenilaian>
//
//    @FormUrlEncoded
//    @POST("penilaian")
//    fun addnilaiii(@Header("Content-Type")head:String, @FieldMap param: Map<String,String>
//    ): Call<ResponsePenilaian>

}