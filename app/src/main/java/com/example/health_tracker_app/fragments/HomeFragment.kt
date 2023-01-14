import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.health_tracker_app.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Add a description of mental health
        val mentalHealthDescription = "Mental health is a state of well-being in which an individual realizes his or her own abilities, can cope with the normal stresses of life, can work productively and is able to make a contribution to his or her community. See more information about mental health on Wikipedia https://en.wikipedia.org/wiki/Mental_health"
        view.findViewById<TextView>(R.id.text_view_mental_health_description).text = mentalHealthDescription

        // Add the videos
        val videoList = listOf(
            Video(R.drawable.sleepicon, "Introduction to mental health", "https://youtu.be/-OAjfrhuwRk"),
            Video(R.drawable.nutritionicon,"Sleep and mental health", "https://youtu.be/98V1q5k8x5E"),
            Video(R.drawable.nutritionicon,"Nutrition and mental health", "https://youtu.be/xyQY8a-ng6g"),
            Video(R.drawable.nutritionicon,"Stress and mental health", "https://youtu.be/DxIDKZHW3-E"),
            Video(R.drawable.nutritionicon, "Alcohol and mental health", "https://youtu.be/hzcZd08PqSQ")
        )

        val videoListView = view.findViewById<RecyclerView>(R.id.recycler_view_videos)
        videoListView.layoutManager = LinearLayoutManager(requireContext())
        videoListView.adapter = VideoAdapter(videoList)
        return view
    }
}

data class Video(
    @DrawableRes val picture: Int, val title: String, val url: String)




class VideoAdapter(private val videoList: List<Video>) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_row_layout, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    override fun getItemCount() = videoList.size
}




class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(video: Video) {
        val myImageView = itemView.findViewById<ImageView>(R.id.rowImage)
        myImageView.setImageResource(video.picture)
        val title = itemView.findViewById<TextView>(R.id.rowName)
        title.text = video.title
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.url))
            itemView.context.startActivity(intent)
        }
    }
}

