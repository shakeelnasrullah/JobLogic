package com.pak.joblogicproblem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pak.joblogicproblem.R
import com.pak.joblogicproblem.databinding.CallRecyclerviewItemBinding
import com.pak.joblogicproblem.models.PhoneContact
import com.pak.joblogicproblem.models.PhoneContactItem

class ContactListAdapter(private val phoneContact: PhoneContact) :
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CallRecyclerviewItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.call_recyclerview_item,
                parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(phoneContact[position])
    }

    override fun getItemCount(): Int {
        return phoneContact.size
    }


    // ViewHolder for Contact
    class ContactViewHolder(private val binding: CallRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phoneContactItem: PhoneContactItem) {
            binding.contact = phoneContactItem
            binding.executePendingBindings()

        }
    }
}