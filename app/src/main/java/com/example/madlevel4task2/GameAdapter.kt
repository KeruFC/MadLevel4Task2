package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.item_game.view.*
import kotlinx.android.synthetic.main.item_game.view.tvResult

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game){
            itemView.tvResult.text = game.result
            itemView.tvDate.text = game.matchDate

            if(game.playerMove == ROCK){
                itemView.ivYouItem.setImageResource(R.drawable.rock)
            } else if(game.playerMove == PAPER) {
                itemView.ivYouItem.setImageResource(R.drawable.paper)
            } else if(game.playerMove == SCISSORS) {
                itemView.ivYouItem.setImageResource(R.drawable.scissors)
            }

            if(game.computerMove == ROCK){
                itemView.ivComputerItem.setImageResource(R.drawable.rock)
            } else if(game.computerMove == PAPER) {
                itemView.ivComputerItem.setImageResource(R.drawable.paper)
            } else if(game.computerMove == SCISSORS) {
                itemView.ivComputerItem.setImageResource(R.drawable.scissors)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.databind(games[position])
    }

}