package com.solidict.poc.ui.package_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solidict.poc.databinding.PackagesListItemBinding
import com.solidict.poc.vo.response_modals.Subscription

class PackageItemListAdapter(val subscriptions: List<Subscription>) :
    RecyclerView.Adapter<PackageItemListAdapter.PackageItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageItemViewHolder {
        return PackageItemViewHolder(
            PackagesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return subscriptions.size
    }

    override fun onBindViewHolder(holder: PackageItemViewHolder, position: Int) {
        holder.bind(subscriptions[position])
    }


    inner class PackageItemViewHolder : RecyclerView.ViewHolder {

        private var binding: PackagesListItemBinding

        constructor(binding: PackagesListItemBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(subscription: Subscription) {
            binding.subscription = subscription
            binding.executePendingBindings()
        }
    }

}