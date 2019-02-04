package com.example.surakum2.moviefinder.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.surakum2.moviefinder.R;
import com.example.surakum2.moviefinder.model.MovieListModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class movieListAdapter extends RecyclerView.Adapter<movieListAdapter.MovieListViewHolder> {

    private  final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";
    private List<MovieListModel> movieListModels;
    private int rowLayout;
    private Context context;

    Bitmap moviePoster;

    public movieListAdapter(List<MovieListModel> movieList, int rowLayout, Context context){
        this.setMovieListModels(movieList);
        this.setRowLayout(rowLayout);
        this.setContext(context);

    }

    public static class MovieListViewHolder extends RecyclerView.ViewHolder{
        LinearLayout movieList;
        ImageView avatar;
        TextView title;
        TextView review;
        TextView releasedDate;
        public MovieListViewHolder(@NonNull View v) {
            super(v);
            movieList = v.findViewById(R.id.movie_item_layout);
            avatar = v.findViewById(R.id.movieImage);
        }
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout,viewGroup,false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        String image_path = movieListModels.get(position).getMovieImage();
        String imageUrl = IMAGE_BASE_URL + image_path;
        Picasso.get().load(imageUrl).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return movieListModels.size();
    }

    public List<MovieListModel> getMovieListModels() {
        return movieListModels;
    }

    public void setMovieListModels(List<MovieListModel> movieListModels) {
        this.movieListModels = movieListModels;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

        //ImageDownloader task = new ImageDownloader(holder.avatar);
        //task.execute(imageurl);
        private final WeakReference<ImageView> containerImageView;

        public ImageDownloader(ImageView imageView) {
            this.containerImageView = new WeakReference<ImageView>(imageView);
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                Log.d("Image URL",url.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return  bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView imageView = this.containerImageView.get();
            if (imageView != null) {
                imageView.setImageBitmap(result);
            }
        }
    }
}
