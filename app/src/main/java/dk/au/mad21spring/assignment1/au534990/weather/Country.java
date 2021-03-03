package dk.au.mad21spring.assignment1.au534990.weather;

import android.os.Parcel;
import android.os.Parcelable;

/*Inspiration
 * https://guides.codepath.com/android/using-parcelable
 * https://developer.android.com/reference/android/os/Parcelable
 * */

public class Country implements Parcelable {

    public static final String COUNTRY_LIST = "COUNTRY_LIST";
    public static final String RETURN_COUNTRY_LIST = "RETURN_COUNTRY_LIST";
    public static final String COUNTRY_DETAILED = "COUNTRY_SPECIFIC";
    public static final String RETURN_COUNTRY_DETAILED = "RETURN_COUNTRY_SPECIFIC";

    public String cityName;
    public String country;
    public String weather;
    public String userNotes = "";
    public int temperatureValue;
    public int humidityValue;
    public int imageId;
    public double userRating;

    //Country constructor er lavet med inspiration fra ListandGrids applikationen
    public Country(String cityName, String country, int temperature, int humidity, String weather) {
        this.cityName = cityName;
        this.country = country;
        this.temperatureValue = temperature;
        this.humidityValue = humidity;
        this.weather = weather;
        setFlagImage();
    }

    private void setFlagImage() {
        if ("AE".equals(country)) imageId = R.drawable.ae;
        else if ("AU".equals(country)) imageId = R.drawable.au;
        else if ("DK".equals(country)) imageId = R.drawable.dk;
        if ("FI".equals(country)) imageId = R.drawable.fi;
        else if ("FJ".equals(country)) imageId = R.drawable.fj;
        else if ("FO".equals(country)) imageId = R.drawable.fo;
        else if ("JP".equals(country)) imageId = R.drawable.jp;
        else if ("NA".equals(country)) imageId = R.drawable.na;
        else if ("RU".equals(country)) imageId = R.drawable.ru;
        else if ("SG".equals(country)) imageId = R.drawable.sg;
        else if ("US".equals(country)) imageId = R.drawable.us;
    }

    protected Country(Parcel in) {
        cityName = in.readString();
        country = in.readString();
        weather = in.readString();
        userNotes = in.readString();
        temperatureValue = in.readInt();
        humidityValue = in.readInt();
        imageId = in.readInt();
        userRating = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityName);
        dest.writeString(country);
        dest.writeString(weather);
        dest.writeString(userNotes);
        dest.writeInt(temperatureValue);
        dest.writeInt(humidityValue);
        dest.writeInt(imageId);
        dest.writeDouble(userRating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
