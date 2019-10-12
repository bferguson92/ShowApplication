package com.example.showapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.showapplication.R
import com.example.showapplication.model.Customer

class CustomerAdapter(
    private val customerList: List<Customer>,
    private val delegator: CustomerAdapterDelagate
) : RecyclerView.Adapter<CustomerAdapter.CustomViewHolder>() {

    interface CustomerAdapterDelagate{
        fun viewItem(item: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_item_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            nameText.text = customerList[position].name
            relationText.text = customerList[position].relation
            viewButton.setOnClickListener{
               delegator.viewItem(customerList[position].relation)
            }
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.text_view_name)
        val relationText: TextView = view.findViewById(R.id.text_view_relation)
        val viewButton: Button = view.findViewById(R.id.button_show_item)
    }
}