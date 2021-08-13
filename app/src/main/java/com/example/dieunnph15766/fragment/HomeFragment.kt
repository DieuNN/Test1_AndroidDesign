package com.example.dieunnph15766.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.dieunnph15766.ListViewContactArrayAdapter
import com.example.dieunnph15766.R
import com.example.dieunnph15766.database.ContactDB
import com.example.dieunnph15766.database.Database
import com.example.dieunnph15766.model.Contact


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactList = ContactDB(Database(requireContext()))

        val listView = view.findViewById<ListView>(R.id.lisvview_all_contact)
        listView.adapter = ListViewContactArrayAdapter(requireContext(), R.layout.contact_listview_row, contactList.getAllContact())

    }
}