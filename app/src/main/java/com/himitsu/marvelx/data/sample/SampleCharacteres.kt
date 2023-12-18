package com.himitsu.marvelx.data.sample

data class SampleCharacteres(
    val name: String,
    val thumbnail: thumbnail
)
data class thumbnail(
    val path: String,
    val extension: String
)


//fonte: https://cultura.uol.com.br/entretenimento/noticias/2021/03/25/715_super-herois-marvel-conheca-alguns-dos-personagens-mais-populares-da-editora.html

fun getlistSampleCharacteres() = listSampleCharacteres

val listSampleCharacteres = listOf(
    SampleCharacteres(
        name = "Spider-Man (Peter Parker)",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Hulk",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Captain America",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/3/50/537ba56d31087",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Wolverine",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/2/60/537bcaef0f6cf",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Deadpool",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/9/90/5261a86cacb99",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Scarlet Witch",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/6/70/5261a7d7c394b",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Iron Man",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Thor",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Ms. Marvel (Kamala Khan)",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/5/b0/548730dac2a40",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Black Panther",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/6/60/5261a80a67e7d",
            extension = "jpg" )),
    SampleCharacteres(
        name = "Vision",
        thumbnail = thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/9/d0/5111527040594",
            extension = "jpg" ))

)