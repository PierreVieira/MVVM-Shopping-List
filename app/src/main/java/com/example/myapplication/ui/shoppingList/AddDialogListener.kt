package com.example.myapplication.ui.shoppingList

import com.example.myapplication.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClick(item: ShoppingItem)
}