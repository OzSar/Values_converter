package com.ozge.myassigmentfirst

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class KmToMilesFragment : Fragment() {

    private lateinit var edtKm: EditText
    private lateinit var edtMiles: EditText
    private lateinit var btnSave: Button
    private lateinit var txtError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_km_to_miles, container, false)

        val toolbar: Toolbar = view.findViewById(R.id.toolbarKmToMiles)
        toolbar.setTitleTextColor(android.graphics.Color.WHITE)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        edtKm = view.findViewById(R.id.edtKm)
        edtMiles = view.findViewById(R.id.edtMiles)
        btnSave = view.findViewById(R.id.btnSaveKmMiles)
        txtError = view.findViewById(R.id.txtErrorKmMiles)

        edtKm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val kmStr = s.toString()
                if (kmStr.isNotEmpty()) {
                    val km = kmStr.toDouble()
                    val miles = km * 0.621371
                    edtMiles.setText(String.format("%.2f", miles))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        edtMiles.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val milesStr = s.toString()
                if (milesStr.isNotEmpty()) {
                    val miles = milesStr.toDouble()
                    val km = miles / 0.621371
                    edtKm.setText(String.format("%.2f", km))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnSave.setOnClickListener {
            val km = edtKm.text.toString()
            val miles = edtMiles.text.toString()
            if (km.isEmpty() || miles.isEmpty()) {
                txtError.text = "Both fields must be filled."
                txtError.visibility = View.VISIBLE
                Toast.makeText(activity, "Please enter values in both fields", Toast.LENGTH_SHORT).show()
            } else {
                txtError.visibility = View.GONE
                // Save values to SharedPreferences
                val sharedPreferences = activity?.getSharedPreferences("ValuesConverterPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("km", km)
                editor?.putString("miles", miles)
                editor?.apply()
                Toast.makeText(activity, "Values saved successfully", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
