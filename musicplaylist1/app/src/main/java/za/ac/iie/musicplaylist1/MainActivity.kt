package za.ac.iie.musicplaylist1

import android.content.Intent
import android.media.Rating
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Array

class MainActivity : AppCompatActivity() {

    //Declarations:Arrays
    val songTitles = Array(4) { "" }
    val artistNames = Array(4) { "" }
    val ratings = Array(4) { "" }
    val comments = Array(4) { "" }

    var index = 0 //to keep track of position

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val InputTitle = findViewById<EditText>(R.id.InputTitle)
        val InputArtist = findViewById<EditText>(R.id.InputArtist)
        val InputRating = findViewById<EditText>(R.id.InputRating)
        val InputComment = findViewById<EditText>(R.id.InputComment)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnExit = findViewById<Button>(R.id.btnExit)

        //Adding to the playlist
        btnAdd.setOnClickListener {
            if (index < 4) {
                val title = InputTitle.text.toString()
                val artist = InputArtist.text.toString()
                val ratingText = InputRating.text.toString()
                val comment = InputComment.text.toString()

                if (title.isNotEmpty() && ratingText.isNotEmpty() && comment.isNotEmpty()) {
                    val rating = ratingText.toIntOrNull()

                    if (rating != null && rating in 1..5) {
                        //Save to the arrays
                        songTitles[index] = title
                        artistNames[index] = artist
                        ratings[index] = ratingText
                        comments[index] = comment
                        index++

                        Toast.makeText(this, "song added!", Toast.LENGTH_SHORT).show()

                        //Inputs

                        InputTitle.setText("")
                        InputArtist.setText("")
                        InputRating.setText("")
                    } else {
                        Toast.makeText(this, "Rating should be between 1&5", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this, "Please fill in all field", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Playlist full only 4 songs max", Toast.LENGTH_SHORT).show()
            }
        }

        //Viewing Playlist(second screen)
        btnView.setOnClickListener {
            val intent = Intent(this,detailedview::class.java)

            intent.putExtra("songs",songTitles)
            intent.putExtra("artists",artistNames)
            intent.putExtra("comments",comments)
            intent.putExtra("ratings",ratings)

            startActivity(intent)
        }
        //Exiting the app
        btnExit.setOnClickListener {
            finish()
        }


}}

