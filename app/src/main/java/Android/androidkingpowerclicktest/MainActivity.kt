package Android.androidkingpowerclicktest

import Android.androidkingpowerclicktest.adapters.ImageAdapter
import Android.androidkingpowerclicktest.models.Image
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler

class MainActivity : AppCompatActivity(), ViewImageActivity.CellClickListener {

    var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = getString(R.string.photo)
        if (isOnline(this)){
            recyclerView = findViewById<RecyclerView>(R.id.image_item_list)  as RecyclerView
            recyclerView!!.layoutManager =  GridLayoutManager(this, 2)
            val url = "https://jsonplaceholder.typicode.com/"
            val postsApi = API.create(url)
            val response = postsApi.getAllImage()
            response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
                recyclerView!!.adapter = ImageAdapter(this, it, this)

            }
        }else{
            showCustomAlert(
                getString(R.string.alert),
                getString(R.string.msg_pls_check_internet_connection)
            )
        }
    }

    override fun onCellClickListener(data: Image) {
        val intent = Intent(this@MainActivity, ViewImageActivity::class.java)
        intent.putExtra("imageUrl", data.url)
        intent.putExtra("imageTitle", data.title)

        startActivity(intent)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
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
            refresh()
        }
    }

    private fun refresh(){
        val refresh = Intent(this, MainActivity::class.java)
        startActivity(refresh)
        finish()
        Log.i("refresh", "MainActivity : refresh")
    }

}




