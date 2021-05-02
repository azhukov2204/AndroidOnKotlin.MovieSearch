package ru.androidlearning.moviesearch.view

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.androidlearning.moviesearch.R
import ru.androidlearning.moviesearch.databinding.MainFragmentBinding
import ru.androidlearning.moviesearch.model.MovieDetails
import ru.androidlearning.moviesearch.viewmodel.AppState
import ru.androidlearning.moviesearch.viewmodel.MovieDetailViewModel
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val mainFragmentBinding get() = _binding!!

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return mainFragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        viewModel.getMovieDetailsLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getMovieDetailsFromLocalSource()

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> onSuccessAction(appState.movieDetails)
            is AppState.Error -> onErrorAction(appState.error.message)
            is AppState.Loading -> onLoadingAction()
        }
    }

    private fun onLoadingAction() {
        mainFragmentBinding.loadingLayout.visibility = View.VISIBLE
    }

    private fun onErrorAction(message: String?) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder
            .setTitle(getString(R.string.attentionWord))
            //.setMessage(getString(R.string.errorLoadingMovieDetailsMessage))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.tryToReloadButtonText)) { dialog, which -> run { viewModel.getMovieDetailsFromLocalSource() } }
            .setNegativeButton(getString(R.string.resurtnToPrevScreenButtonText)) { dialog, which ->
                run {
                    //fragmentManager?.popBackStack()
                }
            }
            .create().show()
    }

    private fun onSuccessAction(movieDetails: MovieDetails) {
        mainFragmentBinding.loadingLayout.visibility = View.GONE
        setData(movieDetails)
    }


    private fun setData(movieDetails: MovieDetails) {
        mainFragmentBinding.movieName.text = movieDetails.movie.name

        mainFragmentBinding.movieGenre.text =
            String.format(Locale.getDefault(), getString(R.string.genreWord) + movieDetails.genre)

        mainFragmentBinding.movieDuration.text = String.format(
            Locale.getDefault(),
            getString(R.string.durationWord) + movieDetails.durationInMinutes.toString() + " " + getString(
                R.string.minutesWord
            )
        )

        mainFragmentBinding.movieRating.text = String.format(
            Locale.getDefault(),
            getString(R.string.ratingWord) + movieDetails.movie.rating.toString()
        )

        mainFragmentBinding.movieReleaseDate.text =
            String.format(
                Locale.getDefault(),
                getString(R.string.releaseDateWord) + movieDetails.movie.releaseDate?.let {
                    getStringFromDate(
                        it
                    )
                })

        mainFragmentBinding.movieDescription.text = movieDetails.description

    }


    private fun getStringFromDate(date: Date): String {
        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return sdf.format(date)
    }

}