package com.example.challengeroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeroom.room.tbsiswa
import kotlinx.android.synthetic.main.activity_siswa_adapter.view.*


//1.(private val siswa:ArrayList<tbsiswa>) : RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>(){}
//    class SiswaViewHolder(view: View): RecyclerView.ViewHolder(view){}
//2.Impelement Member
//3.override fun getItemCount(): Int {} -> override fun getItemCount() = siswa.size
//4.1.)return SiswaViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.activity_siswa_adapter,parent, false)
//        )
//5.2.)val sis = siswa[position]
//--onclick--
//6., private val listener: OnAdapterListener
//7.interface OnAdapterListener{
//fun onClick(tbsis : tbsiswa)}
//8.holder.view.TNama.setOnClickListener{ listener.onClick(sis) }

class SiswaAdapter (private val siswa:ArrayList<tbsiswa>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>(){
    class SiswaViewHolder(val view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaViewHolder {
        return SiswaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_siswa_adapter,parent, false)
        )
    }

    override fun onBindViewHolder(holder: SiswaViewHolder, position: Int) {
        val sis = siswa[position]
        holder.view.TNama.text = sis.nama
        holder.view.TNama.setOnClickListener{ listener.onClick(sis) }
    }

    override fun getItemCount() = siswa.size

    fun setData(list: List<tbsiswa>){
        siswa.clear()
        siswa.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(tbsis : tbsiswa)
    }

}