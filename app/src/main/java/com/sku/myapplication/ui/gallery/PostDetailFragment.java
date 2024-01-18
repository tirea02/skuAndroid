package com.sku.myapplication.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.sku.myapplication.model.Post;
import com.sku.myapplication.network.ApiInterface;
import com.sku.myapplication.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.sku.myapplication.databinding.FragmentPostDetailBinding;

public class PostDetailFragment extends Fragment {

    private FragmentPostDetailBinding binding;
    private int postId; // Variable to store post ID

    // Method to create new instance of PostDetailFragment with post ID
    public static PostDetailFragment newInstance(int postId) {
        PostDetailFragment fragment = new PostDetailFragment();
        Bundle args = new Bundle();
        args.putInt("postId", postId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Get post ID from arguments
        if (getArguments() != null) {
            postId = getArguments().getInt("postId");
            fetchPostDetails(postId);
        }

        return view;
    }

    private void fetchPostDetails(int postId) {
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getPostDetails(postId).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Post post = response.body();
                    displayPostDetails(post);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void displayPostDetails(Post post) {
        binding.titleTextView.setText(post.getTitle());
        binding.contentTextView.setText(post.getContent());
        if (post.getImgFile() != null && !post.getImgFile().isEmpty()) {
            String imageUrl = "http://10.0.2.2:8080/img/" + post.getImgFile(); // Adjust base URL as needed
            Glide.with(this)
                    .load(imageUrl)
                    .into(binding.postImageView);
        }
        // Set other post details in the views
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
