package com.example.dieunnph15766

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.dieunnph15766.model.Contact

class ListViewContactArrayAdapter(private val mContext: Context,private val mResource:Int, private val mList:ArrayList<Contact>) :ArrayAdapter<Contact>(mContext, mResource, mList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(mContext).inflate(mResource, null)
        var name = view.findViewById<TextView>(R.id.textview_listview_contact_name)
        var phoneNumber = view.findViewById<TextView>(R.id.textview_listview_contact_phone_number)

        name.text = mList[position].name
        phoneNumber.text = mList[position].phoneNumber
        return view
    }
}