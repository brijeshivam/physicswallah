package com.brijeshivam.physicswallah.datafetch

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.brijeshivam.physicswallah.UsersData
import com.brijeshivam.physicswallah.rvadapter.RVAdapter

class APICall {
    fun getData(cntxt: Context, rAdapter: RVAdapter){

        //Api Link
            val url = "https://my-json-server.typicode.com/easygautam/data/users"

        //API call using Volley Library
            val dataList = ArrayList<UsersData>()
            val queue = Volley.newRequestQueue(cntxt)
            val jsonRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                { response ->
                    for (i in 0 until response.length()) {
                        val data = response.getJSONObject(i)
                        val name = data.getString("name")
                        val subList = data.getJSONArray("subjects")
                        var subj = ""
                        for(j in 0 until subList.length() ){
                            subj += subList.getString(j) + " "
                        }
                        val qualList = data.getJSONArray("qualification")
                        var qual = ""
                        for(j in 0 until qualList.length() ){
                            qual += qualList.getString(j) + " "
                        }
                        val imageLink = data.getString("profileImage")
                        val userData = UsersData(name, subj, qual, imageLink)
                        dataList.add(userData)
                    }
                    rAdapter.updateData(dataList)
                },
                {
                    Toast.makeText(cntxt, "Network error", Toast.LENGTH_SHORT)
                        .show()
                })
            queue.add(jsonRequest)



    }
}