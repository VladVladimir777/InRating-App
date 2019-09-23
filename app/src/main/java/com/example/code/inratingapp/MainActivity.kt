package com.example.code.inratingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.code.inratingapp.adapterUsers.UsersDataSourceFactory
import com.example.code.inratingapp.api.LIKES_VALUE
import com.example.code.inratingapp.appSet.AppSet
import com.example.code.inratingapp.mainFragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appSet: AppSet


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Application).appComponent.inject(this)
        setContentView(R.layout.activity_main)

        // Toolbar
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                appSet.currentPostId = s.toString().trim()
            }

        })

        btnSearch.setOnClickListener {
            val currentId = etSearch.text.toString().trim()
            if (currentId.isNotEmpty()) {
                (supportFragmentManager.findFragmentById(R.id.flMain) as MainFragment).invalidateData()
            } else {
                Toast.makeText(this, R.string.search_field_empty, Toast.LENGTH_SHORT).show()
            }
            hideKeyboardFrom(applicationContext, it)
        }


        setFragment(MainFragment.FRAGMENT_MAIN)

    }


    override fun onResume() {
        super.onResume()
        etSearch.setText(appSet.currentPostId)
    }


    private fun setFragment(key: String) {
        val frTransaction = supportFragmentManager.beginTransaction()
        when (key) {
            MainFragment.FRAGMENT_MAIN ->
                if (supportFragmentManager.findFragmentById(R.id.flMain) !is MainFragment) {
                    frTransaction.replace(R.id.flMain, MainFragment())
                }
        }
        frTransaction.commit()
    }
}
