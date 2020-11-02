package com.example.giroskop

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), SensorEventListener {

    var manager:SensorManager?=null
    var current_degree:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // присвоили менеджеру работу с серсором
        manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        //регистрируем сенсоры в объекты сенсора
        manager?.registerListener(this,manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        //говорим что данные будем получать из этого окласса
        super.onPause()
        manager?.unregisterListener(this)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    @SuppressLint("DefaultLocale")
    override fun onSensorChanged(p0: SensorEvent?) {
       val vectorX:Int=p0?.values?.get(0)?.toInt()!!
        val vectorY:Int= p0.values?.get(1)?.toInt()!!
        val vectorZ:Int= p0.values?.get(2)?.toInt()!!

        val vectors =
                "X: " +  vectorX.toString()+"\n"+
                "Y: " + vectorY.toString()+"\n"+
                "Z: " + vectorZ.toString()
            izmer.text = vectors

            val rotationAnim = RotateAnimation(current_degree.toFloat(), (-vectorX).toFloat(), Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotationAnim.duration = 210
            rotationAnim.fillAfter = true
            current_degree = -vectorX
            image.startAnimation(rotationAnim)

    }
}

