package com.example.movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.models.CountryDTO
import com.example.movies.data.models.ShortGenreDTO
import com.example.movies.data.models.ShortProductionCompanyDTO
import com.example.movies.databinding.MovieDetailsFragmentBinding
import com.example.movies.ui.MainViewModel

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: MovieDetailsFragmentBinding
    private val mViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = mViewModel.loadingState.value
        binding.tagline.text = mViewModel.loadingState.value
        binding.releaseDate.text = mViewModel.loadingState.value
        binding.runtime.text = mViewModel.loadingState.value
        binding.genres.text = mViewModel.loadingState.value
        binding.overview.text = mViewModel.loadingState.value
        binding.productionCountriesValue.text = mViewModel.loadingState.value
        binding.productionCompaniesValue.text = mViewModel.loadingState.value
        binding.backButton.setOnClickListener { navigateBack() }

        mViewModel.movie.observe(viewLifecycleOwner, { movie ->
            binding.title.text = movie.title
            binding.tagline.text = movie.tagline
            Glide.with(binding.root)
                .load(resources.getString(R.string.poster_url, movie.posterPath))
                .into(binding.poster)
            binding.releaseDate.text = parseReleaseDate(movie.releaseDate)
            binding.runtime.text = resources.getString(R.string.runtime, movie.runtime.toString())
            binding.genres.text = parseGenres(movie.genres)
            binding.overview.text = movie.overview
            binding.budgetValue.text = parseMoneyValue(movie.budget)
            binding.revenueValue.text = parseMoneyValue(movie.revenue)
            binding.productionCountriesValue.text =
                parseProductionCountries(movie.productionCountries)
            binding.productionCompaniesValue.text =
                parseProductionCompanies(movie.productionCompanies)
        })
    }

    private fun parseMoneyValue(value: Int): String {
        var valueString = value.toString()

        if (valueString.length > 3) {
            var outputString = valueString.slice(valueString.length - 3 until valueString.length)
            valueString = valueString.slice(0 until valueString.length - 3)

            while (valueString.isNotEmpty()) {
                if (valueString.length >= 3) {
                    outputString =
                        "${valueString.slice(valueString.length - 3 until valueString.length)},$outputString"
                    valueString = valueString.slice(0 until valueString.length - 3)
                } else {
                    outputString = "$valueString,$outputString"
                    valueString = ""
                }
            }

            return "$$outputString"
        }
        return "N/A"
    }

    private fun parseReleaseDate(date: String): String {
        val year = date.slice(0 until 4)
        val monthNum = date.slice(5 until 7).toInt()
        val day = date.slice(8 until 10).toInt()

        val month = when (monthNum) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> "You done goofed"
        }

        return "$month $day, $year"
    }

    private fun parseGenres(genreList: List<ShortGenreDTO>): String {
        var outputString = genreList[0].name
        for ((index, genre) in genreList.withIndex()) {
            if (index > 0) outputString = "$outputString, ${genre.name}"
        }
        return outputString
    }

    private fun parseProductionCountries(countryList: List<CountryDTO>): String {
        if (countryList.isNotEmpty()) {
            var outputString = countryList[0].name
            for ((index, country) in countryList.withIndex()) {
                if (index > 0) outputString = "$outputString\n${country.name}"
            }
            return outputString
        }
        return "N/A"
    }

    private fun parseProductionCompanies(companyList: List<ShortProductionCompanyDTO>): String {
        if (companyList.isNotEmpty()) {
            var outputString = companyList[0].name
            for ((index, company) in companyList.withIndex()) {
                if (index > 0) outputString = "$outputString\n${company.name}"
            }
            return outputString
        }
        return "N/A"
    }

    private fun navigateBack() {
        mViewModel.setLoadingState("Loading...")
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.action_movie_fragment_pop)
    }
}