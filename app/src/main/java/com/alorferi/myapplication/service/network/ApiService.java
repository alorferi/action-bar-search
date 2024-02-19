package com.alorferi.myapplication.service.network;


import com.alorferi.myapplication.service.model.AllStudents;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("verifed-students.php")
    Call<AllStudents> getAllStudentsLists();



}


