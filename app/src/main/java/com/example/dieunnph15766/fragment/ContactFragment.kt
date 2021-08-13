package com.example.dieunnph15766.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766.R
import com.example.dieunnph15766.RecyclerViewAdapter
import com.example.dieunnph15766.database.ContactDB
import com.example.dieunnph15766.database.Database
import com.example.dieunnph15766.model.Contact
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ContactFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerViewAdapter
    lateinit var database: Database
    lateinit var contactDB: ContactDB
    lateinit var contactList:ArrayList<Contact>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        database = Database(requireContext())
        contactDB = ContactDB(database)

        contactList = contactDB.getAllContact()
        adapter = RecyclerViewAdapter(requireContext(), contactList)

        recyclerView.adapter = adapter

        val fab = view.findViewById<FloatingActionButton>(R.id.fab_new_contacts)

        fab.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                var name: EditText = EditText(requireContext())
                var phoneNumber = EditText(requireContext())
                phoneNumber.inputType = InputType.TYPE_CLASS_PHONE

                var linearLayout = LinearLayout(requireContext())
                linearLayout.orientation = LinearLayout.VERTICAL

                linearLayout.addView(name)
                linearLayout.addView(phoneNumber)

                setTitle("New Contact")
                setView(linearLayout)
                setPositiveButton("OK") { _, _ ->

                    contactDB.newContact(Contact(name.text.toString(), phoneNumber.text.toString()))
                    contactList.clear()
                    contactList = contactDB.getAllContact()
                    recyclerView.adapter = RecyclerViewAdapter(requireContext(), contactList)
                }

                setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        }
    }
}