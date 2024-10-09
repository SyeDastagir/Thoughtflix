package dev.syed.thoughtflix.util


const val W500 = "w500"
fun String.getImageFullUrl(width:String = "original"): String {
    return "https://image.tmdb.org/t/p/$width/$this"
}