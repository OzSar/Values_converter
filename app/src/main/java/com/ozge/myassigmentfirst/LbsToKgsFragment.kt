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

class LbsToKgsFragment : Fragment() {
    private lateinit var edtLbs: EditText
    private lateinit var txtKgs: TextView
    private lateinit var btnSave: Button
    private lateinit var txtError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lbs_to_kgs, container, false)

        val toolbar: Toolbar = view.findViewById(R.id.toolbarLbsToKgs)
        toolbar.setTitleTextColor(android.graphics.Color.WHITE)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        edtLbs = view.findViewById(R.id.edtLbs)
        txtKgs = view.findViewById(R.id.txtKgs)
        btnSave = view.findViewById(R.id.btnSaveLbsToKgs)
        txtError = view.findViewById(R.id.txtErrorLbs)

        edtLbs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val lbsStr = s.toString()
                if (lbsStr.isNotEmpty()) {
                    val lbs = lbsStr.toDouble()
                    val kgs = lbs * 0.453592
                    txtKgs.text = String.format("%.2f KGs", kgs)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnSave.setOnClickListener {
            val lbs = edtLbs.text.toString()
            if (lbs.isEmpty() || lbs.toDouble() !in 50.0..1000.0 || lbs.toDouble() % 50.0 != 0.0) {
                txtError.text = "Invalid value. Must be between 50 and 1000 lbs and a multiple of 50."
                txtError.visibility = View.VISIBLE
                Toast.makeText(activity, "Invalid value entered", Toast.LENGTH_SHORT).show()
            } else {
                txtError.visibility = View.GONE
                // Save values to SharedPreferences
                val sharedPreferences = activity?.getSharedPreferences("ValuesConverterPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("lbs", lbs)
                editor?.putString("kgs", txtKgs.text.toString())
                editor?.apply()
                Toast.makeText(activity, "Value saved successfully", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}

