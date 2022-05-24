package org.d3if2132.kalkulatorbmr.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2132.kalkulatorbmr.R
import org.d3if2132.kalkulatorbmr.databinding.FragmentHitungBinding
import org.d3if2132.kalkulatorbmr.db.BmrDb
import org.d3if2132.kalkulatorbmr.model.HasilBmr

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = BmrDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)

        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button.setOnClickListener { hitungBmr() }

        val items = listOf("sedikit beraktivitas, tidak berolahraga", "olahraga ringan 1 – 3 kali dalam seminggu", "olahraga ringan 6 - 7 kali dalam seminggu", "olahraga berat 1 atau 2 kali dalam sehari", "olahraga berat 2 kali atau lebih dalam sehari")
        val adapter = ArrayAdapter(requireActivity(), R.layout.list_item, items)
        val material_spinner = binding.materialSpinner
        material_spinner.setAdapter(adapter)

        binding.resetButton.setOnClickListener {
            reset()
            material_spinner.setAdapter(adapter)
        }

        binding.tabelKaloriButton.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_makananFragment
            )
        }

        binding.bagikanButton.setOnClickListener { shareData() }

        viewModel.getHasilBmr().observe(requireActivity(), { showResult(it) })

    }

    private fun hitungBmr() {
        val berat = binding.beratBadanInp.text.toString()

        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val tinggi = binding.tinggiBadanInp.text.toString()

        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val umur = binding.umurInp.text.toString()

        if (TextUtils.isEmpty(umur)) {
            Toast.makeText(context, R.string.umur_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedIdJK = binding.radioGroup.checkedRadioButtonId

        if (selectedIdJK == -1) {
            Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedFA = binding.materialSpinner.text.toString()
        if (TextUtils.isEmpty(selectedFA)) {
            Toast.makeText(context, R.string.activity_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungBmr(
            berat.toFloat(),
            tinggi.toFloat(),
            umur.toFloat(),
            selectedFA,
            selectedIdJK == R.id.priaRadioButton
        )
    }

    private fun reset(){
        binding.beratBadanInp.getText()?.clear()
        binding.tinggiBadanInp.getText()?.clear()
        binding.umurInp.getText()?.clear()
        binding.radioGroup.clearCheck()
        binding.hasilBmrTextView.setText("")
        binding.hasiltdeeTextView.setText("")
        binding.card.visibility = View.INVISIBLE
        binding.card2.visibility = View.INVISIBLE
        binding.resetButton.visibility = View.INVISIBLE
        binding.bagikanButton.visibility =  View.INVISIBLE

        binding.divider2.visibility = View.INVISIBLE
        binding.textViewFoodTitle.visibility = View.INVISIBLE
        binding.textViewFoodCat1.visibility = View.INVISIBLE
        binding.textViewFoodCat2.visibility = View.INVISIBLE
        binding.textViewFoodCat3.visibility = View.INVISIBLE
        binding.textViewFoodCat4.visibility = View.INVISIBLE
        binding.textViewFoodCat5.visibility = View.INVISIBLE
        binding.textViewFoodCat6.visibility = View.INVISIBLE
        binding.textViewFoodCat7.visibility = View.INVISIBLE
        binding.textViewFoodCat8.visibility = View.INVISIBLE
        binding.tabelKaloriButton.visibility =  View.INVISIBLE
    }

    private fun showResult(result: HasilBmr?){
        if (result == null) return

        binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, result.bmr)
        binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee,result.tdee)

        binding.resetButton.visibility = View.VISIBLE
        binding.card.visibility = View.VISIBLE
        binding.card2.visibility = View.VISIBLE
        binding.bagikanButton.visibility =  View.VISIBLE

        binding.divider2.visibility =  View.VISIBLE
        binding.textViewFoodTitle.visibility = View.VISIBLE
        binding.textViewFoodCat1.visibility = View.VISIBLE
        binding.textViewFoodCat2.visibility = View.VISIBLE
        binding.textViewFoodCat3.visibility = View.VISIBLE
        binding.textViewFoodCat4.visibility = View.VISIBLE
        binding.textViewFoodCat5.visibility = View.VISIBLE
        binding.textViewFoodCat6.visibility = View.VISIBLE
        binding.textViewFoodCat7.visibility = View.VISIBLE
        binding.textViewFoodCat8.visibility = View.VISIBLE
        binding.tabelKaloriButton.visibility =  View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }

            R.id.menu_about -> {
                    findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                    return true
                }
            }
                return super.onOptionsItemSelected(item)
    }

    private fun shareData() {
        val selectedIdJK = binding.radioGroup.checkedRadioButtonId
        val gender = if (selectedIdJK == R.id.priaRadioButton)
            getString(R.string.pria)
        else
            getString(R.string.wanita)
        val aktivitasFisik =  binding.materialSpinner.text.toString()
        val message = getString(R.string.bagikan_text,
            binding.beratBadanInp.text,
            binding.tinggiBadanInp.text,
            binding.umurInp.text,
            aktivitasFisik,
            gender,
            binding.hasilBmrTextView.text,
            binding.hasiltdeeTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }


}