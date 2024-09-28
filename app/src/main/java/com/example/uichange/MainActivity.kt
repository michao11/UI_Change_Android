package com.example.uichange

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var index: Int = 0
    private val list = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val image: ImageView = findViewById(R.id.imageView)
        val leftButton: Button = findViewById(R.id.left_button)
        val rightButton: Button = findViewById(R.id.right_button)
        val randomButton: Button = findViewById(R.id.random_button)
        val sizeTextView: TextView = findViewById(R.id.size_textView)
        val seekBar: SeekBar = findViewById(R.id.seekBar)
        val themeSwitch: Switch = findViewById(R.id.theme_switch)
        val layout: ConstraintLayout = findViewById(R.id.main)
        val listView: ListView = findViewById(R.id.listView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        val images = mutableListOf(
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six
        )

        //image.setImageResource(images[index])
        //sizeTextView.textSize = 30F
        //sizeTextView.setBackgroundColor(resources.getColor(R.color.black))
        //layout.setBackgroundColor(resources.getColor(R.color.red))

        leftButton.setOnClickListener {
            if (index <= 0) {
                index = 5
            }
            else {
                index -= 1
            }
            image.setImageResource(images[index])
        }

        randomButton.setOnClickListener {
            val randomIndex = Random.nextInt(0, 6)
            image.setImageResource(images[randomIndex])

            list.add(randomIndex + 1)
            adapter.notifyDataSetChanged()
        }

        rightButton.setOnClickListener {
            if (index >= 5) {
                index = 0
            }
            else {
                index += 1
            }
            image.setImageResource(images[index])
        }

        seekBar.min = 1
        seekBar.max = 50

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sizeTextView.textSize = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        themeSwitch.setOnClickListener {
            if (themeSwitch.isChecked) {
                layout.setBackgroundColor(resources.getColor(R.color.black))
                sizeTextView.setTextColor(resources.getColor(R.color.white))
                listView.setBackgroundColor(resources.getColor(R.color.white))
                leftButton.setBackgroundResource(R.drawable.dark_mode_button)
                randomButton.setBackgroundResource(R.drawable.dark_mode_button)
                rightButton.setBackgroundResource(R.drawable.dark_mode_button)
                leftButton.setTextColor(resources.getColor(R.color.black))
                randomButton.setTextColor(resources.getColor(R.color.black))
                rightButton.setTextColor(resources.getColor(R.color.black))

            }
            else {
                layout.setBackgroundColor(resources.getColor(R.color.white))
                sizeTextView.setTextColor(resources.getColor(R.color.black))
                leftButton.setBackgroundResource(R.drawable.light_mode_button)
                randomButton.setBackgroundResource(R.drawable.light_mode_button)
                rightButton.setBackgroundResource(R.drawable.light_mode_button)
                leftButton.setTextColor(resources.getColor(R.color.white))
                randomButton.setTextColor(resources.getColor(R.color.white))
                rightButton.setTextColor(resources.getColor(R.color.white))
            }
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            list.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
}
