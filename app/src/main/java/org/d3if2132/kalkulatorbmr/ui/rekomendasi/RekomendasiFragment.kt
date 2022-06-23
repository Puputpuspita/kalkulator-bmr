package org.d3if2132.kalkulatorbmr.ui.rekomendasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2132.kalkulatorbmr.databinding.FragmentRekomendasiBinding
import org.d3if2132.kalkulatorbmr.network.ApiStatus

class RekomendasiFragment : Fragment() {

    private val viewModel: RekomendasiViewModel by lazy {
        ViewModelProvider(this).get(RekomendasiViewModel::class.java)
    }

    private lateinit var binding: FragmentRekomendasiBinding
    private lateinit var myAdapter: RekomendasiAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRekomendasiBinding.inflate(layoutInflater, container, false)
        myAdapter = RekomendasiAdapter()

        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })

        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })

        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

}