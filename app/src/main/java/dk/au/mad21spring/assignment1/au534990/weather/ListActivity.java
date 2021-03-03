package dk.au.mad21spring.assignment1.au534990.weather;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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

public class ListActivity extends AppCompatActivity implements CountryAdapter.ICountryItemClickedListener {

    private RecyclerView recyclerView;
    private Button btnExit;
    private CountryAdapter countryAdapter;
    private CountryViewModel countryViewModel;

    public static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        countryViewModel.getCountries().observe(this, new Observer<ArrayList<Country>>() {
            @Override
            public void onChanged(ArrayList<Country> countries) {
                countryAdapter.updateCountryList(countries);
            }
        });

        //set up recyclerview with adapter and layout manager
        countryAdapter = new CountryAdapter(this);
        recyclerView = findViewById(R.id.RecycleViewCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(countryAdapter);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //When country is clicked start DetailsActivity
    @Override
    public void onCountryClicked(int index) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Country.COUNTRY_LIST, countryViewModel.getCountry(index));
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Country country = data.getParcelableExtra(Country.RETURN_COUNTRY_LIST);
            countryViewModel.updateCountry(country);
        }
    }
}