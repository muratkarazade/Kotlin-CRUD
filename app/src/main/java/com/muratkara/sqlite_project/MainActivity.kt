package com.muratkara.sqlite_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.muratkara.sqlite_project.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = this
        var db = DataBaseHelper(context)
        binding.btnSave.setOnClickListener{
            var nameSurname = binding.userNameSurname.text.toString()
            var age = binding.userAge.text.toString()
            var country = binding.userCountry.text.toString()
            if (nameSurname.isNotEmpty() && age.isNotEmpty() && country.isNotEmpty()){
                var userClass = User(nameSurname,age.toInt(),country)
                db.insertData(userClass)
            }else{
                Toast.makeText(applicationContext, "Lütfen Boş Yerleri Doldurunuz!", Toast.LENGTH_LONG).show()
            }
        }


        //Verilerin okumak için
        binding.btnRead.setOnClickListener {
            var data =db.dataRead()
            binding.tvResult.text = ""
            for (i in 0 until data.size){
                binding.tvResult.append(data.get(i).id.toString()+ " "
                + data.get(i).nameSurname+ " " + data.get(i).age + "\n" )
            }
        }

        // Verilerin güncellenmesi
        binding.btnUpdate.setOnClickListener {
            db.updateData()
            binding.btnRead.callOnClick()

        }


    }
}