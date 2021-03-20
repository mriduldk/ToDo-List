package `in`.codingstudio.to_dolist

import `in`.codingstudio.to_dolist.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.db.williamchart.view.DonutChartView

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val animations : ArrayList<Float> = ArrayList<Float>(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val colors : IntArray = intArrayOf(resources.getColor(R.color.baby_blue), resources.getColor(R.color.baby_blue))
         val colors2 : IntArray = intArrayOf(resources.getColor(R.color.orange), resources.getColor(R.color.orange))
         val colors3 : IntArray = intArrayOf(resources.getColor(R.color.light_blue), resources.getColor(R.color.light_blue))

        animations.add(0f)
        animations.add(70f)

        binding.donutChart1.donutColors = colors3
        binding.donutChart1.animate(animations)


        animations[1] = 40f
        binding.donutChart2.donutColors = colors2
        binding.donutChart2.animate(animations)

        animations[1] = 80f
        binding.donutChart3.donutColors = colors
        binding.donutChart3.animate(animations)

        binding.progressBar1.progress = 40
        binding.progressBar2.progress = 60

        binding.calenderButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        })

        val intent = Intent(this, CalendarActivity::class.java)
        startActivity(intent)

    }
}