package org.d3if2132.kalkulatorbmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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

        val tinggiCm = tinggi.toFloat() / 100
        val selectedIdJK = binding.radioGroup.checkedRadioButtonId

        if (selectedIdJK == -1) {
            Toast.makeText(this, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedIdFA = binding.radioGroup2.checkedRadioButtonId

        if (selectedIdFA == -1) {
            Toast.makeText(this, R.string.activity_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val isMale = selectedIdJK == R.id.priaRadioButton
        val aktivitasRingan = selectedIdFA == R.id.aktivitasRingan
        val aktivitasSedang = selectedIdFA == R.id.aktivitasSedang
        val aktivitasBerat = selectedIdFA == R.id.aktivitasBerat
        val stringRes = if (isMale) {
            val bmr = (10 * berat.toFloat()) + (6.5 * tinggiCm) - ()+ 5
        } else {
            val bmr = berat.toFloat() / (tinggiCm * tinggiCm)
        }

        binding.hasilBmrTextView.text = getString(R.string.hasil_bmr, bmr)
    }


}