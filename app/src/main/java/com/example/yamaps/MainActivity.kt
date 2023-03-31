package com.example.yamaps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView


const val API_KEY = "81d5d4e5-58fb-4f81-a0e5-7b0858ec79da"
private val TARGET_LOCATION: Point = Point(59.945933, 30.320045)

class MainActivity : AppCompatActivity() {

    private lateinit var  mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey(API_KEY)

        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapview)

        mapView.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5F),
            null
        )
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }
}