package com.solidict.poc.ui.main_activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solidict.poc.R
import com.solidict.poc.databinding.MenuListItemBinding
import com.solidict.poc.databinding.PackagesListItemBinding
import com.solidict.poc.vo.response_modals.MenuItem
import com.solidict.poc.vo.response_modals.Subscription

class MainMenuListAdapter(var menuItems: List<com.solidict.poc.vo.response_modals.MenuItem>) :
    RecyclerView.Adapter<MainMenuListAdapter.MainMenuListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuListItemViewHolder {
        return MainMenuListItemViewHolder(
                MenuListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

        )
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: MainMenuListItemViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }


    inner class MainMenuListItemViewHolder : RecyclerView.ViewHolder {

        private var binding: MenuListItemBinding

        constructor(binding: MenuListItemBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(menuItem: MenuItem) {
            binding.menuItem = menuItem
            binding.executePendingBindings()
        }
    }

}