package org.d3if2132.kalkulatorbmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import org.d3if2132.kalkulatorbmr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungBmr() }

        val items = listOf("sedikit beraktivitas, tidak berolahraga", "olahraga ringan 1 – 3 kali dalam seminggu", "olahraga ringan 6 - 7 kali dalam seminggu", "olahraga berat 1 atau 2 kali dalam sehari", "olahraga berat 2 kali atau lebih dalam sehari")
        val adapter = ArrayAdapter(this@MainActivity, R.layout.list_item, items)
        val material_spinner = binding.materialSpinner
        material_spinner.setAdapter(adapter)
    }

    private fun hitungBmr() {
        val berat = binding.beratBadanInp.text.toString()

        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(this, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val tinggi = binding.tinggiBadanInp.text.toString()

        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val umur = binding.umurInp.text.toString()

        if (TextUtils.isEmpty(umur)) {
            Toast.makeText(this, R.string.umur_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedIdJK = binding.radioGroup.checkedRadioButtonId

        if (selectedIdJK == -1) {
            Toast.makeText(this, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedFA = binding.materialSpinner.text.toString()
        if (TextUtils.isEmpty(selectedFA)) {
            Toast.makeText(this, R.string.activity_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val isMale = selectedIdJK == R.id.priaRadioButton
        val sedentary = selectedFA == "sedikit beraktivitas, tidak berolahraga"
        val lighlyActive = selectedFA == "olahraga ringan 1 – 3 kali dalam seminggu"
        val moderatelyActive = selectedFA == "olahraga ringan 6 - 7 kali dalam seminggu"
        val veryActive = selectedFA == "olahraga berat 1 atau 2 kali dalam sehari"
        val extremeActive = selectedFA == "olahraga berat 2 kali atau lebih dalam sehari"


        if (isMale) {
            val bmr = (10 * berat.toFloat()) + (6.25 * tinggi.toFloat()) - (5 * umur.toFloat()) + 5
            binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, bmr)

            when {
                sedentary -> {
                    val tdee = 1.2 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                lighlyActive -> {
                    val tdee = 1.375 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                moderatelyActive -> {
                    val tdee = 1.55 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                veryActive -> {
                    val tdee = 1.725 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                extremeActive -> {
                    val tdee = 1.9 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
            }
        } else {
            val bmr = (10 * berat.toFloat()) + (6.25 * tinggi.toFloat()) - (5 * umur.toFloat()) - 161
            binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, bmr)

            when {
                sedentary -> {
                    val tdee = 1.2 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                lighlyActive -> {
                    val tdee = 1.375 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                moderatelyActive -> {
                    val tdee = 1.55 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                veryActive -> {
                    val tdee = 1.725 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
                extremeActive -> {
                    val tdee = 1.9 * bmr
                    binding.hasiltdeeTextView.text = getString(R.string.hasil_tdee, tdee)
                }
            }
        }
/*
        val selectedIdFA = binding.radioGroup2.checkedRadioButtonId

        if (selectedIdFA == -1) {
            Toast.makeText(this, R.string.activity_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val isMale = selectedIdJK == R.id.priaRadioButton
        val aktivitasRingan = selectedIdFA == R.id.aktivitasRingan
        val aktivitasSedang = selectedIdFA == R.id.aktivitasSedang
        val aktivitasBerat = selectedIdFA == R.id.aktivitasBerat

        if (isMale) {
            val bmr = (10 * berat.toFloat()) + (6.5 * tinggi.toFloat()) - (5 * umur.toFloat()) + 5
            if (aktivitasRingan) {
                val tdee = 1.6 * bmr
                binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, tdee)
            }else if (aktivitasSedang){
                val tdee = 1.7 * bmr
                binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, tdee)
            }else if (aktivitasBerat){
                val tdee = 1.8 * bmr
                binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, tdee)
            }
        } else {
            val bmr = (10 * berat.toFloat()) + (6.5 * tinggi.toFloat()) - (5 * umur.toFloat()) - 161
            if (aktivitasRingan) {
                val tdee = 1.5 * bmr
                binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, tdee)
            }else if (aktivitasSedang){
                val tdee = 1.6 * bmr
                binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, tdee)
            }else if (aktivitasBerat){
                val tdee = 1.8 * bmr
                binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, tdee)
            }
        }

 */
    }


}