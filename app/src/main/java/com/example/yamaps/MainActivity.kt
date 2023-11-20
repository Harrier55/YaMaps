package com.example.yamaps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Cluster
import com.yandex.mapkit.map.ClusterListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


const val API_KEY = "81d5d4e5-58fb-4f81-a0e5-7b0858ec79da"
private val TARGET_LOCATION: Point = Point(59.945933, 30.320045)

class MainActivity : AppCompatActivity(), ClusterListener {

    private lateinit var mapView: MapView

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

//        mapView.map.mapObjects.addPlacemark(TARGET_LOCATION, ImageProvider.fromResource(this,R.drawable.ic_location_24)) // не заработал

        val bitmap = this.getBitmapFromVectorDrawable(R.drawable.geometka)

        /** функция добавления иконки на карту работает ТОЛЬКО с Bitmap
         * поэтому нужно преобразовывать**/
        mapView.map.mapObjects.addPlacemark(TARGET_LOCATION, ImageProvider.fromBitmap(bitmap))


    }
    /** функция преобразования Vector to Bitmap */
    private fun Context.getBitmapFromVectorDrawable(drawableId: Int): Bitmap? {
        var drawable = ContextCompat.getDrawable(this, drawableId) ?: return null

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        ) ?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onClusterAdded(p0: Cluster) {
        //  TODO("Not yet implemented")
    }
}