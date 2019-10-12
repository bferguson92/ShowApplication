package com.example.showapplication

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    val contentUrl = "content://com.example.entryapplication.provider.CustomerProvider/customer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        getCustomers()
    }

    private fun getCustomers() {
        val uri = Uri.parse(contentUrl)
        var cursor: Cursor?
        val stringBuilder = StringBuilder()

        try {
            cursor = contentResolver.query(uri, null, null, null, null)

            cursor?.let { values ->
                if (values.count == 0) {
                    text_view_provider.text = "THERE ARE NO CUSTOMERS AT THE MOMENT"
                } else {
                    values.moveToFirst()
                    stringBuilder.append(buildDisplay(values))
                    while (values.moveToNext()) {
                        stringBuilder.append(buildDisplay(values))
                    }
                    text_view_provider.text = stringBuilder.toString()
                }
                values.close()
            } ?: {
                text_view_provider.text = "EMPTY LIST"
            }()
        } catch (throwable: Throwable) {
            Log.e("TAG_ERROR", throwable.toString())
        }
    }

    fun buildDisplay(cursor: Cursor): String {
        return "${cursor.getString(cursor.getColumnIndex("customer_name"))} | ${cursor.getString(
            cursor.getColumnIndex("customer_relation")
        )}\n"
    }
}
