package com.alorferi.myapplication.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorferi.myapplication.databinding.ActivityMainBinding
import com.alorferi.myapplication.service.model.Students
import com.alorferi.myapplication.view.adapter.StudentsAdapter
import com.alorferi.myapplication.viewmodel.StudentListViewModel

class MainActivity : AppCompatActivity() {
    //viewbinding
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: StudentListViewModel
    private lateinit var adapter: StudentsAdapter
    private var mymodelList: List<Students> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Student List"
        binding.studentsRecycler.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(StudentListViewModel::class.java)
        viewModel.allStudentList.observe(this, Observer { students ->
            mymodelList = students
            binding.progressBar.visibility = View.GONE
            adapter = StudentsAdapter(this, students)
            binding.studentsRecycler.adapter = adapter
            binding.studentsRecycler.visibility = View.VISIBLE
            binding.searchveiw.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    filter(newText)
                    return true
                }
            })
        })
    }

    //for filter in searchview
    private fun filter(newText: String) {
        val studentsList = ArrayList<Students>()
        for (item in mymodelList) {
            if (item.roll.lowercase().contains(newText.lowercase()) || item.name.lowercase()
                    .contains(newText.lowercase())
            ) {
                studentsList.add(item)
            }
        }
        adapter.filterlist(studentsList)


    }
}