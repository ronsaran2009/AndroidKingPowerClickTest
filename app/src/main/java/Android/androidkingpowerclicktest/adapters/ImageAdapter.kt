package Android.androidkingpowerclicktest.adapters

import Android.androidkingpowerclicktest.R
import Android.androidkingpowerclicktest.ViewImageActivity
import Android.androidkingpowerclicktest.models.Image
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.image_item.view.*

data class ImageAdapter(private val context: Context, private val images: List<Image>, private val cellClickListener: ViewImageActivity.CellClickListener): RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.image_item, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.image_item_name.text = images[position].title


        if(images[position].thumbnailUrl != null && images[position].thumbnailUrl != "") {
            Picasso.get().load(images[position].thumbnailUrl)
                .error(R.drawable.ic_image_error)
                .placeholder(R.drawable.ic_image_placeholder)
                .fit()
                .centerCrop()
                .into(holder.itemView.image_item_image)
        }else{
            Picasso.get().load(R.drawable.ic_image_error)
                .error(R.drawable.ic_image_error)
                .placeholder(R.drawable.ic_image_placeholder)
                .fit()
                .centerCrop()
                .into(holder.itemView.image_item_image)
        }
        holder.itemView.image_item_image.setOnClickListener(View.OnClickListener { v ->
            cellClickListener.onCellClickListener(images[position])
            Log.i("ImagesClick", "Image_ID : "+images[position].id)
        })
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)

}