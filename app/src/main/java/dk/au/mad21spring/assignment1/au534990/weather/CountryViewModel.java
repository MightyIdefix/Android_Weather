package dk.au.mad21spring.assignment1.au534990.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

//Code Inspieration
    /*Jeg har brugt løsningsforslaget fra ClickerApp fra
     Lektion 2 som hovedinspiration til at implementere denne klasse.
     I stedet for at læse weather data fra en scv fil, har jeg hardcoded dataen i createCountry*/

public class CountryViewModel extends ViewModel {
    MutableLiveData<ArrayList<Country>> countries;

    public LiveData<ArrayList<Country>> getCountries() {
        if (countries == null) {
            createCountry();
        }
        return countries;
    }

    public Country getCountry(int index){
        ArrayList<Country> countryArrayList = countries.getValue();
        Country country = countryArrayList.get(index);
        if (countries != null) return country;
        createCountry();
        return country;
    }

    public void updateCountry(Country country){
        for (int i = 0; i < countries.getValue().size(); i++){
            if (countries.getValue().get(i).country.equals(country.country)){
                ArrayList<Country> countryArrayList = countries.getValue();
                countryArrayList.get(i).userRating = country.userRating;
                countryArrayList.get(i).userNotes = country.userNotes;
                countries.setValue(countryArrayList);
            }
        }
    }

    private void createCountry() {
        ArrayList<Country> countryArrayList = new ArrayList<>();
        countryArrayList.add(new Country("Aarhus", "DK", 2, 100, "Snow. Light breeze"));
        countryArrayList.add(new Country("Helsinki", "FI", -17, 97, "Light snow. Gentle breeze"));
        countryArrayList.add(new Country("New York", "US", -1, 31, "Rain and snow. Gentle Breeze"));
        countryArrayList.add(new Country("Melbourne", "AU", 34, 27, "Light rain. Gentle Breeze"));
        countryArrayList.add(new Country("Windhoek", "NA", 28, 25, "Clear sky. Light breeze"));
        countryArrayList.add(new Country("Singapore", "SG", 27, 66, "Overcast clouds. Moderate breeze"));
        countryArrayList.add(new Country("Novosibirsk", "RU", -17, 96, "Snow. Light breeze"));
        countryArrayList.add(new Country("Dubai", "AE", 23, 54, "Scattered clouds. Gentle Breeze"));
        countryArrayList.add(new Country("Thorshavn", "FO", 8, 83, "Light rain. Fresh Breeze"));
        countryArrayList.add(new Country("San Francisco", "US", 16, 39, "Clear sky. Light breeze"));
        countryArrayList.add(new Country("Suva", "FJ", 26, 85, "Moderate rain. Moderate breeze"));
        countryArrayList.add(new Country("Tokyo", "JP", 7, 46, "Clear sky. Gentle Breeze"));
        countries = new MutableLiveData<ArrayList<Country>>();
        countries.setValue(countryArrayList);
    }

}
