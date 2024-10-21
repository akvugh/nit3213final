package com.example.finale.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class myAdapter(private var dataList: List<String> = listOf(),private val clicklambdafunction: () -> Unit) : RecyclerView.Adapter<viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(dataList[position],clicklambdafunction)
    }

    fun updateData(newDataList:List<String>){
        dataList=newDataList
        notifyDataSetChanged()
    }

}

class viewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val textView: TextView =view.findViewById(R.id.RecyclerTextView)
    fun bind(item: String, clicklambdafunction: () -> Unit) {
        textView.text=item
        textView.setOnClickListener{
            Log.v("s4679530", "Index of selected item: ${this.adapterPosition}")

            // Create a Bundle to pass the data to the next fragment
            val bundle = Bundle()
            bundle.putInt("SelectedItemIndex", this.adapterPosition)  // Pass the clicked text

            // Get the NavController from the item's view context
            val navController = Navigation.findNavController(itemView)

            // Navigate to the detailsFragment and pass the bundle
            navController.navigate(R.id.action_dashboardFragment_to_detailsFragment, bundle)

            clicklambdafunction.invoke()
        }
    }

}