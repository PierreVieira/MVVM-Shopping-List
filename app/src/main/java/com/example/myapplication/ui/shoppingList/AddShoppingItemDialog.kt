package com.example.myapplication.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.myapplication.data.db.entities.ShoppingItem
import com.example.myapplication.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    private lateinit var binding: DialogAddShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()

            if (name.isBlank() || amount.isBlank()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClick(item)
            dismiss()
        }
        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}