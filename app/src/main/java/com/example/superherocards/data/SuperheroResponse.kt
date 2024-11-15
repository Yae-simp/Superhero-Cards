package com.example.superherocards.data

import com.google.gson.annotations.SerializedName

data class SuperheroResponse (
    @SerializedName ("response") val response: String,
    @SerializedName ("results-for") val resultsFor: String,
    @SerializedName ("results") val results: List<Superhero>
    )

data class Superhero (
    @SerializedName ("id") val id: String,
    @SerializedName ("name") val name: String,
    @SerializedName ("image") val image: Image,
    @SerializedName ("biography") val biography: Biography,
    @SerializedName ("powerstats") val power: Power,
    @SerializedName ("work") val work: Work,
    )

data class Image (
    @SerializedName ("url") val url: String
)

data class Biography (
    @SerializedName ("full-name") val fullName: String,
    @SerializedName ("alter-egos") val alterEgo: String,
    @SerializedName ("aliases") val aliases: List<String>?,
    @SerializedName ("place-of-birth") val birthPlace: String,
    @SerializedName ("first-appearance") val firstAppearance: String,
    @SerializedName ("publisher") val publisher: String,
    @SerializedName ("alignment") val alignment: String
    )

data class Power (
    @SerializedName ("intelligence") val int: Int,
    @SerializedName ("strength") val str: Int,
    @SerializedName ("speed") val sp: Int,
    @SerializedName ("durability") val dur: Int,
    @SerializedName ("power") val pow: Int,
    @SerializedName ("combat") val com: Int,
    )

data class Work (
    @SerializedName ("occupation") val occupation: String,
    @SerializedName ("base") val base: String,
    )


