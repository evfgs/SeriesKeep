package br.com.emanu.seriesKeep.activities

import br.com.emanu.seriesKeep.R
import br.com.emanu.seriesKeep.SeriesKeep

class Factory_Activity {
    //Inicia os atributos base(menu; titulo; icone) para as outras activities
    companion object {
        fun getInstance(activity: Any): ActivitySetup {
            val result = ActivitySetup()

            when (activity) {
                is Homepage_Activity -> homePageSetup(result)
                is SerieDetails_Activity -> movieDetailsSetup(result)
                is SearchSerie_Activity -> searchMovieSetup(result)
            }
            return result
        }

        private fun homePageSetup(result: ActivitySetup) {
            result.menuFile = R.menu.mnu_act_homepage
            result.contentView = R.layout.act_homepage
            result.title = SeriesKeep.getContext().getString(R.string.str_act_homepage_label)
            result.toolbarIcon = R.drawable.ic_menu_white_24dp
        }

        private fun movieDetailsSetup(result: ActivitySetup) {
            result.menuFile = R.menu.mnu_act_serie_dtls
            result.contentView = R.layout.act_serie_details
            result.title = ""
            result.toolbarIcon = R.drawable.ic_arrow_left_white_24dp
        }

        private fun searchMovieSetup(result: ActivitySetup) {
            result.menuFile = null
            result.contentView = R.layout.act_search_serie
            result.title = ""
            result.toolbarIcon = R.drawable.ic_arrow_left_white_24dp
        }




    }
}
