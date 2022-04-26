package com.brijeshivam.physicswallah.rvadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brijeshivam.physicswallah.R
import com.brijeshivam.physicswallah.UsersData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class RVAdapter(private val listener: ItemClicked) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    private var dataList = ArrayList<UsersData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        holder.name.text = dataList[position].name
        holder.details.text = dataList[position].subj + " | " + dataList[position].qualification
        Glide.with(holder.itemView.context).asBitmap()
            .load(dataList[position].imageLink)
            .apply(
                RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
            )
            .into(holder.userImage)

//      View More Button Listener
        holder.itemView.findViewById<Button>(R.id.btnViewMore).setOnClickListener {
            listener.onBtnClick(dataList[position].name)
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(dataList: ArrayList<UsersData>){
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView = itemView.findViewById(R.id.userImage)
        val name: TextView = itemView.findViewById(R.id.name)
        val details: TextView = itemView.findViewById(R.id.details)

    }
}

interface ItemClicked {
    fun onBtnClick(name: String)
}
