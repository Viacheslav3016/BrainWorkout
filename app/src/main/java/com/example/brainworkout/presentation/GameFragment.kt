package com.example.brainworkout.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brainworkout.R
import com.example.brainworkout.databinding.FragmentGameBinding
import com.example.brainworkout.domain.entity.GameResult
import com.example.brainworkout.domain.entity.GameSettings
import com.example.brainworkout.domain.entity.Level

class GameFragment : Fragment() {
    private lateinit var level: Level
    private var _binding:FragmentGameBinding?=null
    private val binding:FragmentGameBinding
    get() = _binding ?: throw RuntimeException("binding==null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOptions1.setOnClickListener{
            launchFinishedFragment(GameResult(true, 0, 0, GameSettings(10, 5, 6, 1)))
        }
    }

    private fun launchFinishedFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }
    private fun parseArgs(){
       requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
           level = it
       }
    }

    companion object{
         const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level):GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}