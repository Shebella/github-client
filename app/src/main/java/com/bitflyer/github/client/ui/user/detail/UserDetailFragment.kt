package com.bitflyer.github.client.ui.user.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bitflyer.github.client.databinding.FragmentUserDetailBinding
import com.bitflyer.github.client.domain.util.Utility
import com.bitflyer.github.client.ui.state.ApiStatus
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var adapter: UserDetailAdapter

    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserDetailAdapter()
        binding.userDetailList.adapter = adapter
        binding.userDetailList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.userDetailList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                Utility.hideKeyboard(recyclerView.context, binding.root)
            }
        })

        viewModel.detailList.observe(viewLifecycleOwner) { detailList ->
            when (detailList.status) {
                ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                ApiStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(detailList.data)
                }
                ApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(null)
                    Snackbar.make(binding.root, detailList.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        arguments?.getString(USERNAME)?.let { username ->
            viewModel.getUserDetail(username)
        }
    }

    companion object {
        const val USERNAME = "username"
    }
}
