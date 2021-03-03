package dk.au.mad21spring.assignment1.au534990.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

//Code Inspieration
    /*Jeg har brugt løsningsforslaget fra AndroidUIdemo og ListandGrids fra
     Lektion 3, samt Clickerapp  fra Lektion 2 som hovedinspiration til at implementere denne klasse.
     I stedet for at læse weather data fra en scv fil, har jeg hardcoded dataen i createCountry*/

public class EditActivity extends AppCompatActivity {

    //constant
    public static final String SAVED = "SAVED";
    private Country country;
    private double userRating;

    //ui widgets
    private ImageView imgCountryEdit;
    private TextView txtCityNameEdit, txtMyRating;
    private EditText txtMyNotes;
    private Button btnOK, btnCancel;
    private SeekBar seekBarEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (savedInstanceState != null) {
            country = savedInstanceState.getParcelable(SAVED);
        } else{
            Intent intent = getIntent();
            country = intent.getParcelableExtra(Country.COUNTRY_DETAILED);
        }
        saveEditCountry();
        setEditData();
    }

    private void saveEditCountry() {
        imgCountryEdit = findViewById(R.id.imgCountryEdit);
        txtCityNameEdit = findViewById(R.id.txtCityNameEdit);
        txtMyRating = findViewById(R.id.txtMyRatingValue);
        txtMyNotes = findViewById(R.id.myNotesEdit);
        seekBarEdit = findViewById(R.id.seekBarEdit);
        seekBarEdit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                userRating = (double) progress / 10;
                txtMyRating.setText("My Rating: " + String.valueOf(userRating));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnOK = findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country.userRating = userRating;
                country.userNotes = txtMyNotes.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(Country.RETURN_COUNTRY_DETAILED, country);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void setEditData() {
        imgCountryEdit.setImageResource(country.imageId);
        txtCityNameEdit.setText(country.cityName);
        txtMyRating.setText("Rating: " + Double.toString(country.userRating));
        txtMyNotes.setText(country.userNotes);

        Double rating = country.userRating * 10;
        int ratingToInt = rating.intValue();
        seekBarEdit.setProgress(ratingToInt);
    }


}