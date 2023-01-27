package fr.isen.gouiranhugo.androidcontactds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.gouiranhugo.androidcontactds.databinding.ActivityDetailBinding
import fr.isen.gouiranhugo.androidcontactds.databinding.ActivityMainBinding
import fr.isen.gouiranhugo.androidcontactds.model.Results

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var contact: Results


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contact = intent.getSerializableExtra("Detail") as Results

        binding.usename.text= contact.name?.first + "" + contact.name?.last
        binding.address.text= contact.location?.street?.number.toString() + " " + contact.location?.street?.name + " " + contact.location?.state + " " + contact.location?.city
        binding.email.text=contact.email
        binding.birth.text=contact.dob?.date

        if (contact.picture?.large!!.isNotEmpty()) {
            Picasso.get().load(contact.picture?.large).into(binding.pp)
        }
    }
}