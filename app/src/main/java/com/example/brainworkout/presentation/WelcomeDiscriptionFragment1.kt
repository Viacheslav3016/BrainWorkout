package com.example.brainworkout.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brainworkout.R
import com.example.brainworkout.databinding.FragmentWelcomeDiscription1Binding

class WelcomeDiscriptionFragment1 : Fragment() {

    private var _binding: FragmentWelcomeDiscription1Binding? = null
    private val binding: FragmentWelcomeDiscription1Binding
        get() = _binding ?: throw RuntimeException("binding==null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeDiscription1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener {
            launchChooseLevelfragment()
        }
    }
    private fun launchChooseLevelfragment(){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, ChooseLevelFragment.newInstance())
            .addToBackStack(ChooseLevelFragment.NAME)
            .commit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}