package com.example.madlevel4task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_games_played.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GamesPlayedFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games_played, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        initRv()
        getGamesPlayedFromDatabase()
        view.findViewById<FloatingActionButton>(R.id.btDelete).setOnClickListener {
            clearGamesPlayedHistory()
        }
    }

    private fun initRv() {
        rvGames.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        rvGames.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
    }

    private fun getGamesPlayedFromDatabase() {
        mainScope.launch {
            val productList = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@GamesPlayedFragment.games.clear()
            this@GamesPlayedFragment.games.addAll(productList)
            this@GamesPlayedFragment.gameAdapter.notifyDataSetChanged()
        }
    }

    private fun clearGamesPlayedHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO){
                gameRepository.deleteAllGames()
            }
            getGamesPlayedFromDatabase()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu)
    }
}