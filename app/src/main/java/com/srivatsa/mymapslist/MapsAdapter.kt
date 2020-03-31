package com.srivatsa.mymapslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.srivatsa.mymapslist.models.UserMap

private const val TAG="MapsAdapter"
class MapsAdapter(val context: MainActivity, val userMaps: List<UserMap>, val onClickListner: OnClickListner) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickListner{
        fun onItemClick(position:Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userMaps.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userMap=userMaps[position]
        holder.itemView.setOnClickListener {
            Log.i(TAG,"Tapped on posotion $position")
            onClickListner.onItemClick(position)
        }
        val textViewTitle=holder.itemView.findViewById<TextView>(android.R.id.text1)
        textViewTitle.text=userMap.title


    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}
