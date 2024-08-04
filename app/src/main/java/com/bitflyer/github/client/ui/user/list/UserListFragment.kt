package com.bitflyer.github.client.ui.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bitflyer.github.client.databinding.FragmentUserListBinding
import com.bitflyer.github.client.domain.util.Utility
import com.bitflyer.github.client.ui.state.ApiStatus
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var userListAdapter: UserListAdapter

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListAdapter = UserListAdapter()
        binding.userList.adapter = userListAdapter
        binding.userList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.userList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                Utility.hideKeyboard(recyclerView.context, binding.root)
            }
        })

        viewModel.userList.observe(viewLifecycleOwner) { userList ->
            when (userList.status) {
                ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                ApiStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    userListAdapter.submitList(userList.data)
                }
                ApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    userListAdapter.submitList(null)
                    Snackbar.make(binding.root, userList.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getUserList(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}
