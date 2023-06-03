package com.gdsdevtec.voteapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.gdsdevtec.voteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val votes = intArrayOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listeners()
    }

    private fun listeners() = with(binding) {
        btnPolling.setOnClickListener {
            message(
                "R10:${votes[0]} -- Kb:${votes[1]} -- Lula:${votes[2]}"
            )
        }
        btnVote.setOnClickListener {
            checkVote()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuBlue -> defineBackgroundColor(R.color.blue)

            R.id.mnuRed -> defineBackgroundColor(R.color.red)
            R.id.mnuGreen -> defineBackgroundColor(R.color.green)
            R.id.mnuYellow -> defineBackgroundColor(R.color.yellow)
            R.id.mnuClearVote -> clearVotes()
        }
        return true
    }

    private fun clearVotes() {
        votes.forEach { it == 0 }
    }

    private fun defineBackgroundColor(@ColorRes color: Int) {

        binding.root.setBackgroundColor(resources.getColor(color))
    }

    private fun checkVote() = with(binding) {
        when (mainRadioGr.checkedRadioButtonId) {
            R.id.radioButtonRonaldinho -> computeVote(0, radioButtonRonaldinho.text.toString())
            R.id.radioButtonKb -> computeVote(1, radioButtonKb.text.toString())
            R.id.radioButtonLula -> computeVote(2, radioButtonLula.text.toString())
            else -> message(getString(R.string.voto_obrigatorio))
        }
        mainRadioGr.clearCheck()
    }

    private fun computeVote(position: Int, candidato: String) = with(binding) {
        votes[position]++
        message(String.format(getString(R.string.vote_success), candidato))
    }
}