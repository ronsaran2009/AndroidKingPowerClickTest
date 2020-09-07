package Android.androidkingpowerclicktest

import Android.androidkingpowerclicktest.R.id.view_image_image
import Android.androidkingpowerclicktest.adapters.ImageAdapter
import Android.androidkingpowerclicktest.models.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_image_layout.*

class ViewImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_image_layout)
        val imageUrl: String = intent.getStringExtra("imageUrl")
        val imageTitle: String = intent.getStringExtra("imageTitle")
        supportActionBar!!.title = imageTitle
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        if(imageUrl != null && imageUrl != "") Picasso.get().load(imageUrl)
            .error(R.drawable.ic_image_error)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(view_image_image, object: com.squareup.picasso.Callback {
                override fun onSuccess() {
                }

                override fun onError(e: java.lang.Exception?) {
                    if (e != null) {
                        e.message?.let { showCustomAlert(getString(R.string.error), it) }
                    }
                }
            }) else{
            showCustomAlert(getString(R.string.alert), getString(R.string.msg_can_not_load_image))
            Picasso.get().load(R.drawable.ic_image_error)
                .error(R.drawable.ic_image_error)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(view_image_image)
        }
}
    interface CellClickListener {
        fun onCellClickListener(data: Image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showCustomAlert(title: String, message: String) {
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        dialogView.findViewById<TextView>(R.id.dialog_title).text = title
        dialogView.findViewById<TextView>(R.id.dialog_message).text = message
        val customDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .show()
        Log.i(title, "Message : $message")
        val btDismiss = dialogView.findViewById<Button>(R.id.btDismissCustomDialog)
        btDismiss.setOnClickListener {
            customDialog.dismiss()
        }
    }

}