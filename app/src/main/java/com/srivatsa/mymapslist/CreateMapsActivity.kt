package com.srivatsa.mymapslist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar

private const val TAG="CreateMapsActivity"
class CreateMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var markers:MutableList<Marker> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_maps)

        supportActionBar?.title =intent.getStringExtra(EXTRA_MAP_TITLE)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapFragment.view?.let {
            Snackbar.make(it,"Long press to add a marker!", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK",{})
                .setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
                .show()
        }
    }

     override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnInfoWindowClickListener {markerToDelete ->
            Log.i(TAG,"setOnInfoWindowClickListener -> delete this marker")
            markers.remove(markerToDelete)
            markerToDelete.remove()
        }
        mMap.setOnMapLongClickListener{latLng ->
            Log.i(TAG,"setOnMapClickListener")
            showAlertDialog(latLng)
        }

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun showAlertDialog(latLng: LatLng) {
        val placeFormView=LayoutInflater.from(this).inflate(R.layout.dialog_create_place, null)
        val dialog=
            AlertDialog.Builder(this)
            .setTitle("Create a marker")
            .setView(placeFormView)
            .setNegativeButton("Cancle",null)
            .setPositiveButton("Ok", null)
            .show()


        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val title = placeFormView.findViewById<EditText>(R.id.etTitle).text.toString()
            val description = placeFormView.findViewById<EditText>(R.id.etDescription).text.toString()
            if (title.trim().isEmpty() || description.trim().isEmpty())
            {
                Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
        }

            val marker=mMap.addMarker(MarkerOptions().position(latLng).title(title).snippet(description))

            markers.add(marker)
            dialog.dismiss()
        }



    }
}
