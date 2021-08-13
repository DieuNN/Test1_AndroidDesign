package com.example.dieunnph15766.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(private val mContext: Context): SQLiteOpenHelper(mContext, DATABASE_NAME , null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "CONTACT_MANAGEMENT"
        const val DATABASE_VERSION = 1
        const val TABLE_CONTACT = "CONTACT"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE $TABLE_CONTACT (NAME TEXT PRIMARY KEY, PHONE_NUMBER TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}

