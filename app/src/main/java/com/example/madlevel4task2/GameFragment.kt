package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

const val ROCK = 0
const val PAPER = 1
const val SCISSORS = 2

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private var playerOption : Int = 0
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
//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun initViews(){
        ivRock.setOnClickListener{
            ivYou.setImageResource(R.drawable.rock)
            rockPaperScissors(ROCK)
        }
        ivPaper.setOnClickListener{
            ivYou.setImageResource(R.drawable.paper)
            rockPaperScissors(PAPER)
        }
        ivScissors.setOnClickListener{
            ivYou.setImageResource(R.drawable.scissors)
            rockPaperScissors(SCISSORS)
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
                    ROCK -> tvResult.text = getString(R.string.draw)
                    PAPER -> tvResult.text = getString(R.string.you_lose)
                    SCISSORS -> tvResult.text = getString(R.string.you_win)
                }
            }

            PAPER -> {
                when(random){
                    ROCK -> tvResult.text = getString(R.string.you_win)
                    PAPER -> tvResult.text = getString(R.string.draw)
                    SCISSORS -> tvResult.text = getString(R.string.you_lose)
                }
            }

            SCISSORS -> {
                when(random){
                    ROCK -> tvResult.text = getString(R.string.you_lose)
                    PAPER -> tvResult.text = getString(R.string.you_win)
                    SCISSORS -> tvResult.text = getString(R.string.draw)
                }
            }
        }

    }
}