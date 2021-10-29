package com.example.myapplication.ui.shoppingList

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.data.db.entities.ShoppingItem
import com.example.myapplication.databinding.ActivityShoppingBinding
import com.example.myapplication.other.ShoppingItemAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    private lateinit var binding: ActivityShoppingBinding

    override val kodein: Kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val viewModel = ViewModelProviders.of(this, factory)[ShoppingViewModel::class.java]
        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.rvShoppingItems.adapter = adapter
        viewModel.getAllShoppingItems().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClick(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}