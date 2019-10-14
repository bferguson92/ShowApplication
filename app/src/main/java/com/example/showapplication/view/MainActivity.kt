package com.example.showapplication.view

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.showapplication.R
import com.example.showapplication.adapter.CustomerAdapter
import com.example.showapplication.fragment.ImageFragment
import com.example.showapplication.model.Customer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CustomerAdapter.CustomerAdapterDelagate {

    private val contentUrl =
        "content://com.example.entryapplication.provider.CustomerProvider/customer"
    private val customerList = mutableListOf<Customer>()
    private lateinit var imageFragment: ImageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCustomers()

        recycler_view_customers.adapter = CustomerAdapter(customerList,this )
        recycler_view_customers.layoutManager = LinearLayoutManager(this)

    }


    private fun getCustomers() {
        val uri = Uri.parse(contentUrl)
        var cursor: Cursor?

        try {
            cursor = contentResolver.query(uri, null, null, null, null)

            cursor?.let { values ->
                if (values.count == 0) {

                } else {
                    values.moveToFirst()
                    customerList.add((buildDisplay(values)))
                    while (values.moveToNext()) {
                        customerList.add(buildDisplay(values))
                    }
                    recycler_view_customers?.adapter?.notifyDataSetChanged()
                }
                values.close()
            } ?: {
             }()
        } catch (throwable: Throwable) {
            Log.e("TAG_ERROR", throwable.toString())
        }
    }

    private fun buildDisplay(cursor: Cursor): Customer {
        return Customer(
            cursor.getString(cursor.getColumnIndex("customer_name")),
            cursor.getString(cursor.getColumnIndex("customer_relation"))
        )
    }

    override fun viewItem(item: String) {
        imageFragment = ImageFragment(item)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_image, imageFragment).addToBackStack(null).commit()
    }
}
