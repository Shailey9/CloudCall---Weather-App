package com.example.cloudcall
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 17794894ec6a0ef55cb768d60d61fa17
// d705c9294cef9c5f734749da3eeab5eb

// git remote add origin https://github.com/Sahil4-Jain/Android_Practice.git
//git branch -M main
//git push -u origin main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAPI("London")

        searchbar.setOnEditorActionListener {
                textView, keyCode, keyEvent ->
            val DONE = 6

            var location = textView.text.toString()

            setAPI(location)

            false
        }

    }

    fun setAPI( location : String){

        var retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        var JSONapi = retrofit.create(Data_Collector::class.java)
        var call = JSONapi.getPost(location , "d705c9294cef9c5f734749da3eeab5eb", "metric")

        call.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error :" + t?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                if (!response!!.isSuccessful) {
                    println(response.message())
                    println("*************** Error")
                    return
                }
                showWeather(response.body())

            }
        })

    }

    fun showWeather(myData: WeatherInfo?) {
                    city.setText(myData?.name)
                    temp.setText("" + (myData?.main!!.temp!!).toInt() + "°C")
                    longitude.setText("Longitude : " + myData.coord!!.lon)
                    latitude.setText("Latitude : " + myData.coord!!.lat)
                    sky.setText("Sky : " + myData.weather[0].description)
                    real_feel.setText("Real Feel : " + myData?.main!!.feelsLike + "°C")
                    humidity.setText("Humidity : " + myData?.main!!.humidity + "%")
                    wind_speed.setText(
                        "Wind Speed : " + String.format(
                            "%.2f",
                            myData.wind!!.speed!! + 5
                        ) + "km/h"
                    )
                    pressure.setText("Pressure : " + myData?.main!!.pressure + "mbar")
                }
}

