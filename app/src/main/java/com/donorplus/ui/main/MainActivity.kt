package com.donorplus.ui.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.donorplus.R
import com.donorplus.data.DataDarah
import com.donorplus.databinding.ActivityMainBinding
import com.donorplus.ui.donor.DonorDarahActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var drList: ArrayList<DataDarah>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        db = FirebaseDatabase
            .getInstance()
            .getReference("Data_Darah")



        drList = arrayListOf()
        fetchData()

        binding.apply {
            //Filter Golongan Darah
            val adapterGolonganDarah = ArrayAdapter.createFromResource(
                this@MainActivity,
                R.array.filter_options,
                R.layout.item_goldar
            )

            adapterGolonganDarah.setDropDownViewResource(R.layout.item_goldar)
            spGoldar.adapter = adapterGolonganDarah

            rvGoldar.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context)
            }

            btnDonor.setOnClickListener {
                val intent = Intent(this@MainActivity, DonorDarahActivity::class.java)
                startActivity(intent)

            }


        }

    }

    private fun fetchData() {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                drList.clear()
                if (snapshot.exists()) {
                    for (imtSnap in snapshot.children) {
                        val goldar =imtSnap.getValue(DataDarah::class.java)
                        drList.add(goldar!!)
                    }
                }
                val goldarAdapter = GoldarAdapter(drList)
                binding.rvGoldar.adapter = goldarAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}