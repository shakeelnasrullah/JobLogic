package com.pak.joblogicproblem.views.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.pak.joblogicproblem.R
import com.pak.joblogicproblem.databinding.BuyRecyclerviewItemBinding
import com.pak.joblogicproblem.databinding.CallRecyclerviewItemBinding
import com.pak.joblogicproblem.databinding.FragmentContactsBinding
import com.pak.joblogicproblem.db.DBConnection
import com.pak.joblogicproblem.factory.ContactViewModelFactory
import com.pak.joblogicproblem.fragments.ContactsFragmentArgs
import com.pak.joblogicproblem.models.BuyerItem
import com.pak.joblogicproblem.models.PhoneContact
import com.pak.joblogicproblem.models.PhoneContactItem
import com.pak.joblogicproblem.repositories.ContactRepositoryImpl
import com.pak.joblogicproblem.utils.BaseRecyclerViewAdapter
import com.pak.joblogicproblem.utils.Response
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContactsFragment : Fragment() {
    lateinit var binding: FragmentContactsBinding
    lateinit var viewModel : ContactsViewModel
    lateinit var listType: String
    lateinit var dbConnection : DBConnection

    @Inject
    lateinit var contactRepository: ContactRepositoryImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = ContactsFragmentArgs.fromBundle(it)
            listType = safeArgs.listType.toString()
            // Set RecyclerView on the go
            if (listType.equals("call", true)) {
               viewModel.invokeCallAPI()
            } else if (listType.equals("buy", true)) {
                // call api to get buyer list
                viewModel.invokeBuyAPI()

            } else {
                 viewModel.getSellingItemsFromDB(requireContext())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false)
        val root = binding.root

        viewModel =
            ViewModelProvider(this, ContactViewModelFactory(contactRepository))
                .get(ContactsViewModel::class.java)

        binding.contactRecyclerview.setHasFixedSize(true)
        binding.contactRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        dbConnection = DBConnection.getDatabase(requireContext())
        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Response.Success -> { // Show RecyclerView
                    it.data?.let { phoneContact -> setUpRecyclerView(phoneContact) }
                }
                is Response.Failure -> { // Show Error
                    Log.d("@@", it.errorMessage.toString())
                }
                is Response.Loading -> { // Show loading Dialog

                }
            }
        })
        viewModel.buyList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Response.Success -> { // Show RecyclerView
                    it.data?.let { buyerList -> setUpBuyRecyclerView(buyerList) }
                }
                is Response.Failure -> { // Show Error
                    Log.d("@@", it.errorMessage.toString())
                }
                is Response.Loading -> { // Show loading Dialog

                }
            }
        })
        viewModel.list.observe(viewLifecycleOwner, Observer {
            setUpBuyRecyclerView(it as ArrayList<BuyerItem>)
        })
        return root
    }

    private fun setUpRecyclerView(phoneContact: PhoneContact) {
        val adapter =
            object : BaseRecyclerViewAdapter.BaseRecyclerViewBindingInterface<PhoneContactItem> {
                override fun bindData(item: PhoneContactItem, view: ViewBinding) {
                    val binding = view as CallRecyclerviewItemBinding
                    binding.contact = item
                    binding.executePendingBindings()
                }

            }
        binding.contactRecyclerview.adapter =
            BaseRecyclerViewAdapter(phoneContact, R.layout.call_recyclerview_item, adapter)


    }
    private fun setUpBuyRecyclerView(buyerItem: ArrayList<BuyerItem>) {
        val adapter = object : BaseRecyclerViewAdapter.BaseRecyclerViewBindingInterface<BuyerItem>{
            override fun bindData(item: BuyerItem, view: ViewBinding) {

                val binding = view as BuyRecyclerviewItemBinding
                binding.buyItem = item
                binding.executePendingBindings()
            }
        }
        binding.contactRecyclerview.adapter =
            BaseRecyclerViewAdapter(buyerItem, R.layout.buy_recyclerview_item, adapter)


    }
}