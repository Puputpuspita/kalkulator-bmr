package org.d3if2132.kalkulatorbmr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if2132.kalkulatorbmr.databinding.FragmentMakananBinding

class MakananFragment : Fragment(){
    private lateinit var binding: FragmentMakananBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMakananBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}