package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews;

    public NewsViewModel() {
        this.mNews = new MutableLiveData<>();

    //TODO Remover Mock de Notícias
        List<News> mNews = new ArrayList<>();
        mNews.add(new News("Ferroviária Tem Desfalque Importante","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain..."));
        mNews.add(new News("Ferrinha Joga no Sábado","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"));
        mNews.add(new News("Copa do Mundo Feminina Está Terminada","There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain..."));
        mNews.add(new News("Futebol Feminino em Alta","There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain..."));

        this.mNews.setValue(mNews);
    }

    public LiveData<List<News>> getNews() {
        return this.mNews;
    }
}