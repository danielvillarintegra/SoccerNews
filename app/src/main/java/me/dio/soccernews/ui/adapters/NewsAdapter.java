package me.dio.soccernews.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import me.dio.soccernews.R;
import me.dio.soccernews.databinding.NewsItemBinding;
import me.dio.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news, FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Context context = viewHolder.itemView.getContext();

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        News news = this.news.get(position);
        viewHolder.binding.tvTitle.setText(news.title);
        viewHolder.binding.tvDescription.setText(news.description);
        Picasso.get().load(news.image).into(viewHolder.binding.ivThumbnail);
        // Implementação da funcionalidade de "Abrir Link"
        viewHolder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            Objects.requireNonNull(viewHolder).itemView.getContext().startActivity(i);
        });

        // Implementação da funcionalidade de "Compartilhar"
        viewHolder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT , news.title);
            i.putExtra(Intent.EXTRA_TEXT , news.link);
            viewHolder.itemView.getContext().startActivity(Intent.createChooser(i, "Share"));
        });
        // Implementação da funcionalidade de "Favoritar" (O evento será instanciado pelo Fragment())
        viewHolder.binding.ivFavorite.setOnClickListener(view -> {
            news.favorite = !news.favorite;
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });
        int favoriteColor = news.favorite ? R.color.favorite_active : R.color.favorite_inactive;
        viewHolder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}