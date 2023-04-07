package com.arvioj.entregable2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton = findViewById<Button>(R.id.button)

        myButton.setOnClickListener {
            var url = "http://192.168.1.102/android/request.php"
            val edtNombre = findViewById<EditText>(R.id.editText)
            if(edtNombre.text.isNotBlank()){
                url += "?nombre=" + edtNombre.text
            }
            HTTPVolleyRequestGET(url)
        }

        val myButton2 = findViewById<Button>(R.id.button2)

        myButton2.setOnClickListener {
            val name = findViewById<EditText>(R.id.editText2).text.toString()
            val age = findViewById<EditText>(R.id.editText3).text.toString()
            val  email = findViewById<EditText>(R.id.editText4).text.toString()
            HTTPVolleyRequestPOST("http://192.168.1.102/android/request.php", name ,age , email)
        }
    }

    //metodo GET para conectar HTTP con Volley
        private fun HTTPVolleyRequestGET(url:String){
        val httpQueue = Volley.newRequestQueue(this)
        val httpRequest = StringRequest(Request.Method.GET, url, {
              response ->
                Log.d("HTTPVolleyRequestGET",response)
            Toast.makeText(this, response, Toast.LENGTH_LONG).show()
        }, {
                error ->
                Log.d("HTTPVolleyRequestGET",error.toString())
        })
        httpQueue.add(httpRequest)
}

    //metodo POST para conectar HTTP con Volley
    private fun HTTPVolleyRequestPOST(url:String, name:String, age:String, email:String){
        val httpQueue = Volley.newRequestQueue(this)
        val httpRequest = object : StringRequest(Method.POST, url, {
                response ->
            Log.d("HTTPVolleyRequestPOST",response)
            Toast.makeText(this, response, Toast.LENGTH_LONG).show()
        }, {
                error ->
            Log.d("HTTPVolleyRequestPOST",error.toString())
        })
        {
           override fun getParams(): Map<String, String>{
               val params : MutableMap<String, String> = HashMap()
               params["nombre"] = name
               params["edad"] = age
               params["email"] = email
               return params
           }
        }
        httpQueue.add(httpRequest)
    }

}