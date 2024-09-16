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

class FeetToInchesFragment : Fragment() {
    private lateinit var edtFeet: EditText
    private lateinit var txtInches: TextView
    private lateinit var btnSave: Button
    private lateinit var txtError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feet_to_inches, container, false)

        val toolbar: Toolbar = view.findViewById(R.id.toolbarFeetToInches)
        toolbar.setTitleTextColor(android.graphics.Color.WHITE)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        edtFeet = view.findViewById(R.id.edtFeet)
        txtInches = view.findViewById(R.id.txtInches)
        btnSave = view.findViewById(R.id.btnSaveFeetToInches)
        txtError = view.findViewById(R.id.txtErrorFeet)

        edtFeet.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val feetStr = s.toString()
                if (feetStr.isNotEmpty()) {
                    val feet = feetStr.toDouble()
                    val inches = feet * 12
                    txtInches.text = String.format("%.2f Inches", inches)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnSave.setOnClickListener {
            val feet = edtFeet.text.toString()
            if (feet.isEmpty()) {
                txtError.text = "Feet value must be entered."
                txtError.visibility = View.VISIBLE
                Toast.makeText(activity, "Please enter a value in feet", Toast.LENGTH_SHORT).show()
            } else {
                txtError.visibility = View.GONE
                // Save values to SharedPreferences
                val sharedPreferences = activity?.getSharedPreferences("ValuesConverterPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("feet", feet)
                editor?.putString("inches", txtInches.text.toString())
                editor?.apply()
                Toast.makeText(activity, "Value saved successfully", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
