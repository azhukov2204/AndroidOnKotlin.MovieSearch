package ru.androidlearning.moviesearch.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Movie(
    //val moveId: Int,  //вероятно в будущем это свойство пригодится
    val name: String = "Криминальное чтиво",
    val releaseDate: Date? = getDateFromString("1995/12/27"),
    val rating: Float = 80f,
    val posterUri: Uri? = null,
    val genre: String? = "Триллер/криминал",
    val durationInMinutes: Int? = 154,
    val description: String? = "Двое бандитов Винсент Вега и Джулс Винфилд проводят время в философских беседах " +
            "в перерыве между разборками и «решением проблем» с должниками своего криминального босса " +
            "Марселласа Уоллеса. Параллельно разворачивается три истории. В первой из них Винсент присматривает " +
            "за женой Марселласа Мией и спасает ее от передозировки наркотиков. Во второй рассказывается о Бутче Кулидже," +
            " боксере, нанятом Уоллесом, чтобы сдать бой, но обманувшим его.",
    val category: String = "popular" //возможно потом заменю на enum или sealed class
): Parcelable
{
    companion object {
        const val MOVIE_BUNDLE_KEY = "Movie"
    }
}

fun getDateFromString(dateStr: String): Date? {
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    return simpleDateFormat.parse(dateStr)
}

fun getMovies(): List<Movie> = listOf(
    Movie(
        "Криминальное чтиво", getDateFromString("1995/12/27"), 80f, null, "Триллер/криминал", 154,
        "Двое бандитов Винсент Вега и Джулс Винфилд проводят время в философских беседах " +
                "в перерыве между разборками и «решением проблем» с должниками своего криминального босса " +
                "Марселласа Уоллеса. Параллельно разворачивается три истории. В первой из них Винсент присматривает " +
                "за женой Марселласа Мией и спасает ее от передозировки наркотиков. Во второй рассказывается о Бутче Кулидже," +
                " боксере, нанятом Уоллесом, чтобы сдать бой, но обманувшим его.", "top100"
    ),

    Movie(
        "Шрек 2",
        getDateFromString("2004/08/19"),
        71f,
        null,
        "Мультфильм, семейный, комедия, фэнтези, приключения",
        93,
        "Шрэк и Фиона возвращаются после медового месяца и находят письмо от родителей Фионы с приглашением на ужин. " +
                "Однако те не подозревают, что их дочь тоже стала огром! Вместе с Осликом счастливая пара отправляется в путешествие, " +
                "полное неожиданностей, и попадает в круговорот событий, во время которых приобретает множество друзей…",
        "popular"
    ),

    Movie(
        "Сумерки",
        getDateFromString("2008/11/20"),
        63f,
        null,
        "фэнтези, драма, мелодрама",
        122,
        "Семнадцатилетняя девушка Белла переезжает к отцу в небольшой городок Форкс. Она влюбляется в загадочного одноклассника, " +
                "который, как оказалось, происходит из семьи вампиров, отказавшихся от нападений на людей. Влюбиться в вампира. " +
                "Это страшно? Это романтично, это прекрасно и мучительно, но это не может кончиться добром, особенно в вечном противостоянии " +
                "вампирских кланов, где малейшее отличие от окружающих уже превращает вас во врага.",
        "popular"
    ),

    Movie(
        "Film1", getDateFromString("2008/01/01"), 90f, null, "drama", 80,
        "Film description", "top100"
    ),

    Movie(
        "Film2", getDateFromString("2008/01/01"), 90f, null, "drama", 80,
        "Film2 description", "top100"
    ),

    Movie(
        "Film3", getDateFromString("2008/01/01"), 90f, null, "drama", 80,
        "Film3 description", "top100"
    ),

    Movie(
        "Film1", getDateFromString("2008/01/01"), 90f, null, "drama", 80,
        "Film description", "top200"
    ),

    Movie(
        "Film2", getDateFromString("2008/01/01"), 90f, null, "drama", 80,
        "Film2 description", "top200"
    ),

    Movie(
        "Film3", getDateFromString("2008/01/01"), 90f, null, "drama", 80,
        "Film3 description", "top200"
    )
)
