package com.pak.joblogicproblem.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseRecyclerViewAdapter<T : Any>(
    private val data: ArrayList<T>, @LayoutRes val layoutID: Int,
    private val bindingInterface: BaseRecyclerViewBindingInterface<T>
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun <T : Any> bind(
            item: T,
            bindingInterface: BaseRecyclerViewBindingInterface<T>
        ) = bindingInterface.bindData(item, binding)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ViewBinding =  DataBindingUtil.inflate(inflater, layoutID, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, bindingInterface)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface BaseRecyclerViewBindingInterface<T : Any> {
        fun bindData(item: T, view: ViewBinding)
    }
    /**
     * IMPLEMENTATION OF INTERFACE IN ACTIVITY OR FRAGMENT
    val bindingInterface = object: GenericSimpleRecyclerBindingInterface<String>{
    fun bindData(item:String,view:View){
    val textView: TextView = view.findViewById(R.id.textView)
    textView.text = item
    }
    }
     */
}