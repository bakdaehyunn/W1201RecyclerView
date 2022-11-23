package kr.ac.kumoh.s20181370.w1201recyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20181370.w1201recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model : ListViewModel by viewModels()
    private val songAdapter = SongAdapter()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.list.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = songAdapter
        }
        model.getList().observe(this){
            songAdapter.notifyDataSetChanged()
        }
        for (i in 1..3){
            model.add("사랑은 장난이 아니야")
        }
        model.add("사랑 찾아 인생을 찾아")

    }
    inner class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>(){
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val txSong: TextView = itemView.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txSong.text = model.getSong(position)
        }

        override fun getItemCount() = model.getSize()
    }
}