package com.example.challengeroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengeroom.room.Constant
import com.example.challengeroom.room.dbsmksa
import com.example.challengeroom.room.tbsiswa
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//--ONCLICK--
//1.private var tbsisnis: Int = 0
//tbsisnis = intent.getIntExtra("intent_id",0)
//Toast.makeText(this,tbsisnis.toString(),Toast.LENGTH_SHORT).show()
class EditActivity : AppCompatActivity() {

    val db by lazy { dbsmksa(this) }
    private var tbsisnis: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        simpandata()
        //tbsisnis = intent.getIntExtra("intent_id",0)
        //Toast.makeText(this,tbsisnis.toString(),Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
       val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ-> {
                btnSimpan.visibility = View.GONE
                EtNis.visibility = View.GONE
                tampildatanis()

            }
        }
    }

    fun simpandata(){
        btnSimpan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.tbsisDao().addtbsiswa(
                    tbsiswa(EtNis.text.toString().toInt(),EtNama.text.toString(),EtKelas.text.toString(),EtAlamat.text.toString())
                )
                finish()
            }
        }
    }

    fun tampildatanis(){
        tbsisnis = intent.getIntExtra("intent_nis",0)
        CoroutineScope(Dispatchers.IO).launch{
            val notes = db.tbsisDao().tampilsemuaa(tbsisnis)[0]
            //EtNis.setText(notes.nis)
            EtNama.setText(notes.nama)
            EtKelas.setText(notes.kelas)
            EtAlamat.setText(notes.alamat)

        }
    }
}