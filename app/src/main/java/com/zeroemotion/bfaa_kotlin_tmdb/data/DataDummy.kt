package com.zeroemotion.bfaa_kotlin_tmdb.data

import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow

object DataDummy {
    fun generateDummyMovies(): ArrayList<Movie> {

        return arrayListOf(
            Movie(
                1,
                "Alita : Battle Angel",
                "Robert Rodriguez",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "R.drawable.poster_alita",
                "2019",
                "67"
            ),
            Movie(
                2,
                "Aquaman",
                "James Wan",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "R.drawable.poster_aquaman",
                "17 Agustus 1945",
                "9.5"
            ),
            Movie(
                3,
                "Bohemian Rhapsody",
                "",
                "lSinger Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "R.drawable.poster_bohemian",
                "17 Agustus 1945",
                "9.5"
            ),
            Movie(
                4,
                "Creed II",
                "Steven Caple Jr.",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                " R.drawable.poster_creed",
                "17 Agustus 1945",
                "9.5"
            ),
            Movie(
                5,
                "The Crimes Of Grindelwald",
                "David Yates",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                " R.drawable.poster_crimes",
                "2018",
                "9.5"
            )
        )
    }

    fun generateDummyTvShow(): ArrayList<TvShow> {

        return arrayListOf(
            TvShow(
                1,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "R.drawable.poster_arrow",
                "2012",
                "58"
            ),
            TvShow(
                2,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "R.drawable.poster_doom_patrol",
                "17 Agustus 1945",
                "9.5"
            )
        )
    }

    fun getMovieById(id: Int?): Movie? {
        for (movie in generateDummyMovies()) {
            if (id == movie.id) {
                return movie
            }
        }
        return null
    }

    fun getTvShowById(id: Int?): TvShow? {

        for (movie in generateDummyTvShow()) {
            if (id == movie.id) {
                return movie
            }
        }
        return null
    }
}