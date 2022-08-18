package com.example.cloudcall
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// d705c9294cef9c5f734749da3eeab5eb

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAPI("London")

        searchbar.setOnEditorActionListener {
                textView, _, _ ->

            val location = textView.text.toString()
            setAPI(location)

            false
        }
    }

    private fun setAPI( location : String){

        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val jsonApi = retrofit.create(Data_Collector::class.java)
        val call = jsonApi.getPost(location , "d705c9294cef9c5f734749da3eeab5eb", "metric")

        call.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error :" + t?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                if (!response!!.isSuccessful) {
                    println(response.message())
                    return
                }
                showWeather(response.body())
            }
        })

    }

    @SuppressLint("SetTextI18n")
    fun showWeather(myData: WeatherInfo?) {
                    city.text = myData?.name
                    temp.text = ((myData?.main!!.temp!!).toInt().toString() + "°C")
                    longitude.text = ("Longitude : " + myData.coord!!.lon)
                    latitude.text = ("Latitude : " + myData.coord!!.lat)
                    sky.text = ("Sky : " + myData.weather[0].description)
                    real_feel.text = ("Real Feel : " + myData.main!!.feelsLike + "°C")
                    humidity.text = ("Humidity : " + myData.main!!.humidity + "%")
                    wind_speed.text = (
                        "Wind Speed : " + String.format(
                            "%.2f",
                            myData.wind!!.speed!! + 5
                        ) + "km/h"
                    )
                    pressure.text = ("Pressure : " + myData.main!!.pressure + "mbar")
                }
}

