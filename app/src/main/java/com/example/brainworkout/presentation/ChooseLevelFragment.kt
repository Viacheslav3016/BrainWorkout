package com.example.brainworkout.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brainworkout.R
import com.example.brainworkout.databinding.FragmentChooseLevelBinding
import com.example.brainworkout.domain.entity.Level


class ChooseLevelFragment : Fragment() {
    private var _binding:FragmentChooseLevelBinding?=null
    private val binding:FragmentChooseLevelBinding
    get() = _binding?:throw RuntimeException("binding==null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelTest.setOnClickListener{
            launchGameFragment(Level.TEST)
        }
        binding.buttonLevelEasy.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.buttonLevelMedium.setOnClickListener {
            launchGameFragment(Level.MIDDLE)
        }
        binding.buttonLevelHard.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
    }
    private fun launchGameFragment(level: Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

companion object{

    const val NAME = "ChooseLevelFragment"
    fun newInstance():ChooseLevelFragment{
        return ChooseLevelFragment()
    }
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}