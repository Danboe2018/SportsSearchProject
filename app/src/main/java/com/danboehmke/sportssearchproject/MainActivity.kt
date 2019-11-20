package com.danboehmke.sportssearchproject

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var stringBuilder: StringBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stringBuilder = StringBuilder()

        val searchBut = findViewById<Button>(R.id.searchBut)
        val searchText = findViewById<EditText>(R.id.searchText)

        searchBut.setOnClickListener(View.OnClickListener {
            stringBuilder!!.clear()
            searchTeams(searchText.text.toString())
            Toast.makeText(this,"Loading Results.",Toast.LENGTH_LONG).show()
        })
    }

    fun searchTeams(text: String): String {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        var resp = ""

        val url = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=$text"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                val jsonObject = JSONObject(response)
                val teamsJson = jsonObject.getJSONArray("teams")
                for (i in 0 until teamsJson.length()) {
                    val first = teamsJson.getJSONObject(i)
                    searchHistory(first.getInt("idTeam"))
                }
                resp = response
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        return resp
    }

    fun searchHistory(num: Int) : String {
        val textView = findViewById<TextView>(R.id.outputText)
        var resp = ""

        val queue = Volley.newRequestQueue(this)

        val url = "https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id=$num"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                val jsonObject = JSONObject(response)
                if (!jsonObject.get("results").equals(null)) {
                    val resultsJson = jsonObject.getJSONArray("results")
                    for (i in 0 until resultsJson.length()) {
                        val first = resultsJson.getJSONObject(i)
                        stringBuilder!!.append("\n ${first.get("strEvent")} On ${first.get("dateEvent")}")
                    }
                    stringBuilder!!.append("\n")
                }
                resp = response

                textView.text = "Last 5 Games: ${stringBuilder!!}"
                textView.setMovementMethod(ScrollingMovementMethod())
//                textView.text = "Response is: ${response}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        return resp
    }
}