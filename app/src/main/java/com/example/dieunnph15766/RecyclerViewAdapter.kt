package com.example.dieunnph15766

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766.database.ContactDB
import com.example.dieunnph15766.database.Database
import com.example.dieunnph15766.model.Contact

class RecyclerViewAdapter(val mContext: Context, var mList: ArrayList<Contact>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    lateinit var database: Database
    lateinit var contactDB: ContactDB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.recycler_contact_row, null)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mList[position].name
        holder.phoneNumber.text = mList[position].phoneNumber

        database = Database(mContext)
        contactDB = ContactDB(database)

        holder.edit.setOnClickListener {
            AlertDialog.Builder(mContext).apply {
                var name: EditText = EditText(mContext)
                var phoneNumber = EditText(mContext)
                phoneNumber.inputType = InputType.TYPE_CLASS_PHONE

                name.setText(mList[position].name)
                phoneNumber.setText(mList[position].phoneNumber)

                var linearLayout = LinearLayout(mContext)
                linearLayout.orientation = LinearLayout.VERTICAL

                linearLayout.addView(name)
                linearLayout.addView(phoneNumber)

                setTitle("Edit Contact")
                setView(linearLayout)
                setPositiveButton("OK") { _, _ ->
                    contactDB.editContact(
                        Contact(
                            name.text.toString(),
                            phoneNumber.text.toString()
                        ), mList[position].name!!
                    )
                    mList.clear()
                    mList = contactDB.getAllContact()
                    this@RecyclerViewAdapter.notifyDataSetChanged()
                }
                setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }

        }

        holder.delete.setOnClickListener {
            AlertDialog.Builder(mContext).apply {
                setTitle("Delete this?")
                setMessage("Are you sure to delete ${mList[position].name} ?")
                setPositiveButton("OK") { _, _ ->
                    contactDB.removeContact(mList[position])
                    this@RecyclerViewAdapter.notifyItemRemoved(position)
                    mList.clear()
                    mList.addAll(contactDB.getAllContact())
                }
                setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.recyclerview_textview_name)
        val phoneNumber = itemView.findViewById<TextView>(R.id.recyclerview_textview_phone_number)
        val delete = itemView.findViewById<ImageButton>(R.id.recyclerview_button_delete)
        val edit = itemView.findViewById<ImageButton>(R.id.recyclerview_button_edit)
    }

}