package com.alorferi.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.alorferi.myapplication.service.model.Students;
import com.alorferi.myapplication.service.repository.AllStudentsRepository;

import java.util.List;

public class StudentListViewModel extends AndroidViewModel {


    AllStudentsRepository allStudentsRepository;


    public StudentListViewModel(@NonNull Application application) {
        super(application);

        allStudentsRepository = AllStudentsRepository.getInstance(application);
    }


    public LiveData<List<Students>> getAllStudentList() {
        return allStudentsRepository.getStudentList();
    }


}
