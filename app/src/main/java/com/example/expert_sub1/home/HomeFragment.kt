package com.example.expert_sub1.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expert_sub1.R
import com.example.expert_sub1.core.data.Resource
import com.example.expert_sub1.core.ui.MovieAdapter
import com.example.expert_sub1.databinding.FragmentHomeBinding
import com.example.expert_sub1.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.movies.observe(viewLifecycleOwner, { dataMovie ->
                if (dataMovie != null) {
                    when (dataMovie) {
                        is Resource.Loading -> {
                            binding.homeProgressBar.visibility = View.VISIBLE
                            binding.homeRv.visibility = View.GONE
                            binding.homeError.root.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.homeProgressBar.visibility = View.GONE
                            movieAdapter.setData(dataMovie.data)
                            if (dataMovie.data?.isEmpty() != false) {
                                binding.homeRv.visibility = View.GONE
                                binding.homeError.root.visibility = View.VISIBLE
                                binding.homeError.message.text = getString(R.string.default_empty)
                            }
                            binding.homeRv.visibility = View.VISIBLE
                            binding.homeError.root.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            binding.homeProgressBar.visibility = View.GONE
                            binding.homeRv.visibility = View.GONE
                            binding.homeError.root.visibility = View.VISIBLE
                            binding.homeError.message.text =
                                dataMovie.message ?: getString(R.string.default_error)
                        }
                    }
                }
            })

            with(binding.homeRv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
