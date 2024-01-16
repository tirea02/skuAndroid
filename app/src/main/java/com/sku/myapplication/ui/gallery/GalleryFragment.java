package com.sku.myapplication.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.sku.myapplication.adapter.PostsAdapter;
import com.sku.myapplication.databinding.FragmentGalleryBinding;
import com.sku.myapplication.model.Post;
import com.sku.myapplication.network.ApiInterface;
import com.sku.myapplication.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private PostsAdapter postsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set up RecyclerView
        binding.postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the adapter with an empty list
        postsAdapter = new PostsAdapter(new ArrayList<>());
        binding.postsRecyclerView.setAdapter(postsAdapter);


        // Fetch posts
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("GalleryFragment", "Posts fetched: " + response.body().size());
                    postsAdapter.updatePosts(response.body());
                }else {

                    Log.e("GalleryFragment", "Response unsuccessful or null");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("GalleryFragment", "Error fetching posts: " + t.getMessage());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
