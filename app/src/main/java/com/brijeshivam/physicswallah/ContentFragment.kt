package com.brijeshivam.physicswallah

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brijeshivam.physicswallah.datafetch.APICall
import com.brijeshivam.physicswallah.rvadapter.ItemClicked
import com.brijeshivam.physicswallah.rvadapter.RVAdapter


class ContentFragment : Fragment(), ItemClicked {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Attaching Adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val adapter = RVAdapter(this)
        context?.let { APICall().getData(it, adapter) } //call getData to fetch the data from api
        recyclerView.adapter = adapter


        super.onViewCreated(view, savedInstanceState)
    }

    //handles view more button clicks
    override fun onBtnClick(name: String) {
        Toast.makeText(context,"$name Clicked",Toast.LENGTH_SHORT).show()
    }


}
