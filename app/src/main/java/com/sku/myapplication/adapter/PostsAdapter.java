package com.sku.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.sku.myapplication.R;
import com.sku.myapplication.model.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private List<Post> posts;
    private OnItemClickListener listener;



    public PostsAdapter(List<Post> posts) {
        this.posts = posts;
    }

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = posts.get(position);
        Log.d("PostsAdapter", "Binding post: " + post.getTitle() + " at position: " + position);
        holder.titleTextView.setText(post.getTitle());
        holder.userIdTextView.setText(post.getUserId());

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(post);
            }
        });

        // Set other post details
    }

    @Override
    public int getItemCount() {
        Log.d("PostsAdapter", "Total posts: " + posts.size());
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView userIdTextView;
        // Other views

        PostViewHolder(View itemView) {
            super(itemView);
            Log.d("PostViewHolder", "Creating view holder");
            titleTextView = itemView.findViewById(R.id.titleTextView);
            userIdTextView = itemView.findViewById(R.id.userIdTextView);
            // Initialize other views
        }
    }

    public void updatePosts(List<Post> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
        notifyDataSetChanged();
    }
}
