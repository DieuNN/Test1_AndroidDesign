package com.example.dieunnph15766.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.dieunnph15766.model.Contact

class ContactDB(val database: Database) {
    var sqliteDB: SQLiteDatabase? = null
    var list = ArrayList<Contact>()

    fun getAllContact(): ArrayList<Contact> {
        sqliteDB = database.writableDatabase
        val cursor = sqliteDB!!.rawQuery("SELECT * FROM ${Database.TABLE_CONTACT}", null)
        list = ArrayList<Contact>()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                var contact = Contact()
                contact.name = cursor.getString(0)
                contact.phoneNumber = cursor.getString(1)
                list.add(contact)
                cursor.moveToNext()

            }


        }
        return list
    }

    fun newContact(contact: Contact): Boolean {
        sqliteDB = database.writableDatabase
        var contentValues = ContentValues()
        contentValues.put("NAME", contact.name)
        contentValues.put("PHONE_NUMBER", contact.phoneNumber)

        return sqliteDB!!.insert(Database.TABLE_CONTACT, null, contentValues) > 0
    }

    fun editContact(contact: Contact, currentName:String): Boolean {
        sqliteDB = database.writableDatabase
        var contentValues = ContentValues()
        contentValues.put("NAME", contact.name)
        contentValues.put("PHONE_NUMBER", contact.phoneNumber)

        return sqliteDB!!.update(Database.TABLE_CONTACT, contentValues, "NAME = ?", arrayOf(currentName)) > 0
    }

    fun removeContact( contact: Contact):Boolean {
        sqliteDB = database.writableDatabase
        return sqliteDB!!.delete(Database.TABLE_CONTACT, "NAME = ?", arrayOf(contact.name)) >0
    }


}