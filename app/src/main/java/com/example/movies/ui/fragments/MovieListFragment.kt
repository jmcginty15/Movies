package com.example.movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.movies.R
import com.example.movies.databinding.MovieListFragmentBinding
import com.example.movies.ui.MainViewModel
import com.example.movies.ui.adapters.MovieListAdapter

class MovieListFragment : Fragment() {
    private lateinit var binding: MovieListFragmentBinding
    private val mViewModel: MainViewModel by activityViewModels()
    private val movieListAdapter: MovieListAdapter by lazy {
        MovieListAdapter(this::showMovie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.movieList.observe(
            viewLifecycleOwner,
            { response -> movieListAdapter.addData(response.results) })

        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.movieListRecycler.layoutManager = layoutManager
        binding.movieListRecycler.adapter = movieListAdapter
    }

    private fun showMovie(movieId: Int) {
        mViewModel.getMovieById(movieId)
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.action_movie_list_fragment_to_movie_details_fragment)
    }
}