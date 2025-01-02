package com.example.asynctaskapicall

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.Scanner
import kotlin.text.replace

class FetchFact(val textView: TextView, val progressBar: ProgressBar,) : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg p0: String?): String? {

         val link = p0.get(0)
         val url= URL(link)
         val urlConnection = url.openConnection() as HttpURLConnection
         val inputStream = urlConnection.inputStream

         val stringBuilder = StringBuilder()
         val scanner: Scanner = Scanner(inputStream)

         while (scanner.hasNext()){
             stringBuilder.append(scanner.nextLine())
         }
         return stringBuilder.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        textView.text = result
        val jsonObject = JSONObject(result)
        val fact = jsonObject.getString("value")
        val facttoid = fact.replace("Chuck Norris", "Sujesh")
        textView.text = facttoid
        progressBar.visibility = ProgressBar.INVISIBLE
        Log.d("vijayloging", "$fact")
    }

}