package com.example.challengeroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeroom.room.Constant
import com.example.challengeroom.room.dbsmksa
import com.example.challengeroom.room.tbsiswa
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//--ONCLICK--
//1.,object : SiswaAdapter.OnAdapterListener{
//            override fun onClick(tbsis: tbsiswa) {
//                 startActivity(Intent(applicationContext,EditActivity::class.java)
//                .putExtra("intent_nis",tbsis.nis)
//              )
//            }
//        })

//Toast.makeText(applicationContext, tbsis.nis, Toast.LENGTH_LONG).show()
//intentEdit(tbsis.nis,Constant.TYPE_READ)
//}
//})


class MainActivity : AppCompatActivity() {

    lateinit var siswaAdapter: SiswaAdapter
    val db by lazy { dbsmksa(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecyclerView()
    }

    override fun onStart(){
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val siswa = db.tbsisDao().tampilsemua()
            Log.d("MainActivity", "dbResponse:$siswa")
            withContext(Dispatchers.Main){
                siswaAdapter.setData(siswa)
            }
        }
    }

    private fun halEdit(){
        btnInput.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(tbsisnis:Int, intentType:Int){
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_nis",tbsisnis)
                .putExtra("intent_type",intentType)
        )
    }

    fun setupRecyclerView(){
        siswaAdapter = SiswaAdapter(arrayListOf(),object : SiswaAdapter.OnAdapterListener{
            override fun onClick(tbsis: tbsiswa) {
                intentEdit(tbsis.nis,Constant.TYPE_READ)
               // startActivity(Intent(applicationContext,EditActivity::class.java)
                //.putExtra("intent_nis",tbsis.nis))
           }
      })

        //idRecyclerView
        listdatasiswa.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = siswaAdapter
        }
    }

}