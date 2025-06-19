package za.ac.iie.musicplaylist1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detailedview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailedview)

        val txtSummary=findViewById<TextView>(R.id.txtSummary)
        val txtAverage = findViewById<TextView>(R.id.txtAverage)
        val btnShowSongs = findViewById<Button>(R.id.btnShowSongs)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // arrays from mainactivity
        val songs = intent.getStringArrayExtra("songs")?: arrayOf("","","","")
        val artists=intent.getStringArrayExtra("artists")?:arrayOf("","","","")
        val comments = intent.getStringArrayExtra("comments")?:arrayOf("","","","")
        val ratings = intent.getIntArrayExtra("ratings")?: IntArray(4)

     // Show playlist summary
    btnShowSongs.setOnClickListener {
    var summary = ""

    for (i in 0 until songs.size){
    if (songs[i].isNotEmpty()) {
    summary += "Song${i+1}\n"
    summary +="Title: ${songs[i]}\n"
    summary +="Artist:${artists[i]}\n"
    summary +="Rating:${ratings[i]}\n"
    summary +="Comment:${comments[i]}\n\n"
    }
     }
    txtSummary.text = summary


    //Average Rating
    btnAverage.setOnClickListener {
     var total = 0coc
     var count = 0

     for ( i in ratings.indices){
     if (ratings[i]>0) {
     total += ratings[i]
     count ++

    }
     }
     if (count>0) {
     val average = total / count
     txtAverage.text="Average Rating: $average"
    } else {
     txtAverage.text= "No ratings yet"
    }
     // Back to the main screen
    btnBack.setOnClickListener {
    val intent= Intent(this, MainActivity::class.java)
    startActivity(intent)
    }










        }
    }
}}