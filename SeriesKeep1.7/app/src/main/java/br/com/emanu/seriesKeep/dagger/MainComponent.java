package br.com.emanu.seriesKeep.dagger;

import javax.inject.Singleton;

import br.com.emanu.seriesKeep.activities.Homepage_Activity;
import br.com.emanu.seriesKeep.activities.SerieDetails_Activity;
import br.com.emanu.seriesKeep.activities.SearchSerie_Activity;
import br.com.emanu.seriesKeep.adapters.AdapterSerie;
import dagger.Component;

@Singleton
@Component(
        modules = {
                MainModule.class,
        })
public interface MainComponent {

    void inject(Homepage_Activity homepageActivity);

    void inject(SerieDetails_Activity activitySerie);

    void inject(SearchSerie_Activity searchSerieActivity);

    void inject(AdapterSerie adapterSerie);
}




