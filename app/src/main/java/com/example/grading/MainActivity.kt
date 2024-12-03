package com.example.grading

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.grading.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val recipientEditText = findViewById<EditText>(R.id.etRecipientEmail)
        val subjectEdittext = findViewById<EditText>(R.id.etSubject)
        val messageEditText = findViewById<EditText>(R.id.etMessage)
        val sendEmailButton = findViewById<Button>(R.id.btnSendEmail)

        sendEmailButton.setOnClickListener{
            val recipient = recipientEditText.text.toString().trim()
            val subject = subjectEdittext.text.toString().trim()
            val message = messageEditText.text.toString().trim()

            val mIntent = Intent(Intent.ACTION_SEND)//This line initializes a new Intent object with the action Intent.ACTION_SEND, which indicates that the app wants to send an email
            mIntent.data = Uri.parse("mailto:")//This line specifies that the intent is for sending an email.
                                                       // The mailto: URI scheme tells the system that the user should be presented with email clients.
            mIntent.type = "text/plain"//Here, the intent type is set to "text/plain", indicating that the content being sent is plain text.for email bodies
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))//Specifies the recipient(s) of the email. It's passed as an array to allow multiple recipients.
            mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)// Sets the subject line of the email.
            mIntent.putExtra(Intent.EXTRA_TEXT, message)//Sets the body text of the email.

            try {
                startActivity(Intent.createChooser(mIntent, "Send Email"))//This line launches the email client.
                                                                               // The createChooser method displays a dialog that allows the user to choose which email app they want to use to send the email, with "Send Email" as the dialog title
            }catch (e: Exception){
                Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
            }

        }
    }
}

