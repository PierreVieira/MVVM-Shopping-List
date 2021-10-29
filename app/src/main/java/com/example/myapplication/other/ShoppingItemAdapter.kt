package com.example.myapplication.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.db.entities.ShoppingItem
import com.example.myapplication.databinding.ShoppingItemBinding
import com.example.myapplication.ui.shoppingList.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShoppingViewHolder(
        ShoppingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.bind(currentShoppingItem)
    }

    override fun getItemCount() = items.size

    inner class ShoppingViewHolder(private val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {
            binding.apply {
                tvName.text = item.name
                tvAmount.text = "${item.amount}"
                ivDelete.setOnClickListener { viewModel.delete(item) }
                ivPlus.setOnClickListener {
                    item.amount++
                    viewModel.upsert(item)
                }
                ivMinus.setOnClickListener {
                    if (item.amount > 0) {
                        item.amount--
                        viewModel.upsert(item)
                    }
                }
            }
        }
    }
}
