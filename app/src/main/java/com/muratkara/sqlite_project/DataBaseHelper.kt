package com.muratkara.sqlite_project

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val database_name = "UserDataBase"
val table_name = "Users"
val col_id = "id"
val col_name = "nameAndSurname"
val col_age = "age"
const val col_country = "country"

class DataBaseHelper (  var context : Context) : SQLiteOpenHelper(context,
    database_name,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //Tabloyu oluşturan kod bloğu


        //" UNIQUE ("+ KEY_PHONE+") )"

        val createTable =" CREATE TABLE "+ table_name + "("+
                col_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                col_name + " VARCHAR(250)," +
                col_age + " INTEGER," +
                col_country + " VARCHAR(250))"


        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    // Kullanicidan gelen verilerin kaydedildigi fonksiyon
    fun insertData(userClass: User){
        Log.i("TESTFIX","hata")
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col_name, userClass.nameSurname)
        cv.put(col_age, userClass.age)
        cv.put(col_country , userClass.country)

        var sum = db.insert(table_name , null,cv)
        if (sum == (-1).toLong()){
            Toast.makeText(context,"Hatalı İşlem :(", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context , "İşlem Başarılı :)", Toast.LENGTH_LONG).show()
        }


    }
    @SuppressLint("Range")
    fun dataRead(): MutableList<User> {
        var userList:MutableList<User> = ArrayList()
        val db = this.readableDatabase
        var userQuerry = " Select * from "+ table_name
        var sum = db.rawQuery(userQuerry,null)
        if (sum.moveToFirst()){
            do {
                var user = User()
                user.id = sum.getString(sum.getColumnIndex(col_id)).toInt()
                user.nameSurname = sum.getString(sum.getColumnIndex(col_name)).toString()
                user.age = sum.getString(sum.getColumnIndex(col_age)).toInt()
                userList.add(user)
            }while (sum.moveToNext())
        }

        sum.close()
        db.close()
        return userList
    }


}