package dk.au.mad21spring.assignment1.au534990.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//Code Inspieration
    /*
     Jeg har brugt løsningsforslaget fra AndroidUIdemo og ListandGrids fra
     Lektion 3, samt Clickerapp  fra Lektion 2 som hovedinspiration til at implementere denne klasse.
     I stedet for at læse weather data fra en scv fil, har jeg hardcoded dataen i createCountry
     Eller er brugt:
     https://developer.android.com/training/basics/intents/result
     https://stackoverflow.com/questions/20114485/use-onactivityresult-android
     https://developer.android.com/reference/android/content/Intent
     */

public class DetailsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 102;
    public static final String SAVED = "SAVED";

    private Country country;
    private ImageView imgCountryDetails;
    private TextView txtCityNameDetials, txtHumidityDetials, txtTempDetails,
            txtWeatherDetails, txtUserRatingDetails, txtUserNotesDetails;
    private Button btnBack, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(savedInstanceState != null) {
            country = savedInstanceState.getParcelable(SAVED);
        }else {
            Intent intent = getIntent();
            country = intent.getParcelableExtra(Country.COUNTRY_LIST);
        }
        saveDetailsCountry();
        setDetailsData();
    }

    private void setDetailsData() {
        imgCountryDetails.setImageResource(country.imageId);
        txtCityNameDetials.setText(country.cityName);
        txtTempDetails.setText(String.format("%s", country.temperatureValue));
        txtHumidityDetials.setText(String.format("%s", country.humidityValue));
        txtWeatherDetails.setText(country.weather);
        txtUserRatingDetails.setText(String.format("%.1f", country.userRating));
        txtUserNotesDetails.setText(country.userNotes);
    }

    private void saveDetailsCountry() {
        imgCountryDetails = findViewById(R.id.imgCountryDetails);
        txtCityNameDetials = findViewById(R.id.txtCityNameDetails);
        txtTempDetails = findViewById(R.id.txtTempDetailsValue);
        txtHumidityDetials = findViewById(R.id.txtHumidityDetailsValue);
        txtWeatherDetails = findViewById(R.id.txtWeatherDetailsValue);
        txtUserRatingDetails = findViewById(R.id.txtUserRatingDetailsValue);
        txtUserNotesDetails = findViewById(R.id.txtUserNotesDetailsValue);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Country.RETURN_COUNTRY_LIST, country);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, EditActivity.class);
                intent.putExtra(Country.COUNTRY_DETAILED, country);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(SAVED, country);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            country = data.getParcelableExtra(Country.RETURN_COUNTRY_DETAILED);
            Intent intent = new Intent();
            intent.putExtra(Country.RETURN_COUNTRY_LIST, country);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}