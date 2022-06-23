package org.d3if2132.kalkulatorbmr.ui.rekomendasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if2132.kalkulatorbmr.R
import org.d3if2132.kalkulatorbmr.databinding.DaftarRekomendasiBinding
import org.d3if2132.kalkulatorbmr.model.Makanan
import org.d3if2132.kalkulatorbmr.network.MakananApi

class RekomendasiAdapter: RecyclerView.Adapter<RekomendasiAdapter.ViewHolder>() {
    private val data = mutableListOf<Makanan>()
    fun updateData(newData: List<Makanan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DaftarRekomendasiBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(
        private val binding: DaftarRekomendasiBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(makanan: Makanan) = with(binding) {
            namaMakananTextView.text = makanan.nama
            deskripsiTextView.text = makanan.deskripsi
            Glide.with(imageView.context)
                .load(MakananApi.getMakananUrl(makanan.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)
        }
    }

}