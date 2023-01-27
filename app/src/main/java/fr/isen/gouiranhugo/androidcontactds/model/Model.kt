package fr.isen.gouiranhugo.androidcontactds.model

import com.google.gson.annotations.SerializedName


data class Model (

  @SerializedName("results" ) var data : ArrayList<Results> = arrayListOf(),
  @SerializedName("info"    ) var info    : Info?              = Info()

):java.io.Serializable