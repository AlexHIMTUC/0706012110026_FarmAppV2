package com.training.a0706012110026_farmapp2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.training.a0706012110026_farmapp2.Adapter.AnimalListAdapter
import com.training.a0706012110026_farmapp2.DB.AnimalDatabase.Companion.animalArr
import com.training.a0706012110026_farmapp2.Interface.CardClick
import com.training.a0706012110026_farmapp2.Interface.CardToastClick
import com.training.a0706012110026_farmapp2.Model.Animal
import com.training.a0706012110026_farmapp2.databinding.ActivityAnimalListBinding


class AnimalListActivity : AppCompatActivity(), CardClick, CardToastClick {
    private lateinit var binding : ActivityAnimalListBinding
    private lateinit var adapter : AnimalListAdapter
    private var curCategory : String = "All"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Data Animal"
        adapter = AnimalListAdapter(animalArr, this, this)
        listener()
        setupRecyclerView()
        checker()
        CheckPermissions()
    }

    override fun onCardClick(type: String, animal: Animal) {
        if(type === "edit"){
            val intent = Intent(this, AnimalFormActivity::class.java).apply{

                putExtra("position", animalArr.indexOf(animal))
            }
            startActivityForResult(intent, 10)
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda yakin ingin menghapus data ini ?")
                .setPositiveButton("Iya") { dialog, id ->
                    animalArr.remove(animal)

                    val snackBar = Snackbar.make(binding.root, "Hewan telah dihapus", Snackbar.LENGTH_SHORT)
                    snackBar.setAction("Dismiss", View.OnClickListener {
                        snackBar.dismiss()
                    }).setAnchorView(binding.fabAddAnimal).show()
                    checker()
                    updateListAnimal(curCategory)

                }
                .setNegativeButton("Tidak") { dialog, id ->
                    val snackBar = Snackbar.make(binding.root, "Hewan tidak jadi dihapus", Snackbar.LENGTH_SHORT)
                    snackBar.setAction("Dismiss", View.OnClickListener {
                        snackBar.dismiss()
                    }).setAnchorView(binding.fabAddAnimal).show()
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()

        }
    }

    private fun listener(){
        binding.fabAddAnimal.setOnClickListener{
            val intent = Intent(this, AnimalFormActivity::class.java)
            startActivityForResult(intent, 10)
        }

        binding.filterAll.setOnClickListener{
            resetAllBtn()
            it.setBackgroundColor(Color.parseColor("#2CEC0A"))
            updateListAnimal("All")
            curCategory = "All"
        }

        binding.filterKambing.setOnClickListener{
            resetAllBtn()
            it.setBackgroundColor(Color.parseColor("#2CEC0A"))
            updateListAnimal("Kambing")
            curCategory = "Kambing"
        }

        binding.filterAyam.setOnClickListener{
            resetAllBtn()
            it.setBackgroundColor(Color.parseColor("#2CEC0A"))
            updateListAnimal("Ayam")
            curCategory = "Ayam"
        }

        binding.filterSapi.setOnClickListener{
            resetAllBtn()
            it.setBackgroundColor(Color.parseColor("#2CEC0A"))
            updateListAnimal("Sapi")
            curCategory = "Sapi"
        }

    }


    private fun updateListAnimal(type : String){
        var result = ArrayList<Animal>()
        if(type == "All"){
            result = animalArr
        }

        for(animal in animalArr){
            if(animal.type.equals(type)){
                result.add(animal)
            }
        }
        adapter = AnimalListAdapter(result, this, this)
        setupRecyclerView()
        adapter.notifyDataSetChanged()
    }



    private fun CheckPermissions() {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }

    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        binding.recyclerViewAnimalList.layoutManager = layoutManager   // Set layout
        binding.recyclerViewAnimalList.adapter = adapter   // Set adapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (!grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val snackBar = Snackbar.make(binding.root, "Permission is denied", Snackbar.LENGTH_SHORT)
            snackBar.setAction("Dismiss", View.OnClickListener {
                snackBar.dismiss()
            }).show()
        }

    }

    fun checker(){
        if(animalArr.size > 0){
            binding.recyclerViewAnimalList.visibility = View.VISIBLE
            binding.addAnimalText.visibility = View.GONE
            binding.spinnerAnimal.visibility = View.VISIBLE
        }else{
            binding.recyclerViewAnimalList.visibility = View.GONE
            binding.addAnimalText.visibility = View.VISIBLE
            binding.spinnerAnimal.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10){
            if(resultCode == 1){
                val snackBar = Snackbar.make(binding.root, "Hewan telah ditambahkan", Snackbar.LENGTH_SHORT)
                snackBar.setAction("Dismiss", View.OnClickListener {
                    snackBar.dismiss()
                }).setAnchorView(binding.fabAddAnimal).show()

            }else if(resultCode == 2){
                val snackBar = Snackbar.make(binding.root, "Hewan telah diupdate", Snackbar.LENGTH_SHORT)
                snackBar.setAction("Dismiss", View.OnClickListener {
                    snackBar.dismiss()
                }).setAnchorView(binding.fabAddAnimal).show()
            }
        }

    }

    fun resetAllBtn(){
        binding.filterAll.setBackgroundColor(Color.parseColor("#E6DBDB"))

        binding.filterKambing.setBackgroundColor(Color.parseColor("#E6DBDB"))

        binding.filterAyam.setBackgroundColor(Color.parseColor("#E6DBDB"))

        binding.filterSapi.setBackgroundColor(Color.parseColor("#E6DBDB"))
    }

    override fun onResume() {
        super.onResume()
        checker()
        updateListAnimal(curCategory)
    }

    override fun onToastCardClick(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}