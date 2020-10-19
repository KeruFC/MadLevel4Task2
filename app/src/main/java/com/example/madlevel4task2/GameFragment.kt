package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

const val ROCK = 0
const val PAPER = 1
const val SCISSORS = 2

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private var playerOption : Int = 0
    private var result : String = " "
    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        initViews()

        view.findViewById<FloatingActionButton>(R.id.btHistory).setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_gamesPlayedFragment2)
        }
    }

    private fun initViews(){
        ivRock.setOnClickListener{
            playerOption = ROCK
            ivYou.setImageResource(R.drawable.rock)
            rockPaperScissors(playerOption)
        }
        ivPaper.setOnClickListener{
            playerOption = PAPER
            ivYou.setImageResource(R.drawable.paper)
            rockPaperScissors(playerOption)
        }
        ivScissors.setOnClickListener{
            playerOption = SCISSORS
            ivYou.setImageResource(R.drawable.scissors)
            rockPaperScissors(playerOption)
        }
    }

    private fun rockPaperScissors(chosenOption: Int){
        val random = (0..2).random()

        when(random) {
            0 -> ivComputer.setImageResource(R.drawable.rock)
            1 -> ivComputer.setImageResource(R.drawable.paper)
            2 -> ivComputer.setImageResource(R.drawable.scissors)
        }

        when(chosenOption){
            ROCK -> {
                when(random){
                    ROCK -> {
                        tvResult.text = getString(R.string.draw)
                        result = "draw"
                    }
                    PAPER -> {
                        tvResult.text = getString(R.string.you_lose)
                        result = "lose"
                    }
                    SCISSORS -> {
                        tvResult.text = getString(R.string.you_win)
                        result = "win"
                    }
                }
            }

            PAPER -> {
                when(random){
                    ROCK -> {
                        tvResult.text = getString(R.string.you_win)
                        result = "win"
                    }
                    PAPER -> {
                        tvResult.text = getString(R.string.draw)
                        result = "draw"
                    }
                    SCISSORS -> {
                        tvResult.text = getString(R.string.you_lose)
                        result = "lose"
                    }
                }
            }

            SCISSORS -> {
                when(random){
                    ROCK -> {
                        tvResult.text = getString(R.string.you_lose)
                        result = "lose"
                    }
                    PAPER -> {
                        tvResult.text = getString(R.string.you_win)
                        result = "win"
                    }
                    SCISSORS -> {
                        tvResult.text = getString(R.string.draw)
                        result = "draw"
                    }
                }
            }
        }

        mainScope.launch {
            val date = Date().toString()

            val game = Game(
                playerMove = playerOption,
                computerMove = random,
                result = result,
                matchDate = date
            )

            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }

            val allGames = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
        }
    }
}