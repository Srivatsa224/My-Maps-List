package com.srivatsa.mymapslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.srivatsa.mymapslist.models.UserMap

class MapsAdapter(val context: MainActivity, val userMaps: List<UserMap>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userMaps.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userMap=userMaps[position]
        val textViewTitle=holder.itemView.findViewById<TextView>(android.R.id.text1)
        textViewTitle.text=userMap.title


    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}
