package dk.au.mad21spring.assignment1.au534990.weather;

//Code Inspieration
    /*Jeg har brugt løsningsforslaget fra ListandGrids fra
     Lektion 3 som hovedinspiration til at implementere denne klasse.*/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    //interface for handling when and Person item is clicked
    public interface ICountryItemClickedListener{
        void onCountryClicked(int index);
    }

    //callback interface for user actions on each item
    private ICountryItemClickedListener listener;

    //data in the adapter
    private ArrayList<Country> countryList;

    //constructor
    public CountryAdapter(ICountryItemClickedListener listener){
        this.listener = listener;
    }

    //method for updating the list - causes the adapter/recyclerview to update
    public void updateCountryList(ArrayList<Country> lists){
        countryList = lists;
        notifyDataSetChanged();
    }

    //override this method to create the viewholder object the first time they are requested
    //use the inflater from parent (Activity's viewgroup) to get the view and then use view holders constructor
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view, listener);
        return countryViewHolder;
    }

    //override this to fill in data from Person object at position into the view holder
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.txtCityName.setText(countryList.get(position).cityName);
        String temperatureValue = String.format("%s", countryList.get(position).temperatureValue);
        holder.txtTemperature.setText(temperatureValue);
        holder.txtUserRating.setText(Double.toString(countryList.get(position).userRating));
        holder.imgIcon.setImageResource(countryList.get(position).imageId);
    }

    //override this to return size of list
    @Override
    public int getItemCount() {
        return countryList.size();
    }

    //The ViewHolder class for holding information about each list item in the RecyclerView
    public static class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //viewholder ui widget references
        ImageView imgIcon;
        TextView txtCityName, txtTemperature, txtUserRating;

        //custom callback interface for user actions done the view holder item
        ICountryItemClickedListener listener;

        public CountryViewHolder(@NonNull View itemView, ICountryItemClickedListener countryItemClickedListener) {
            super(itemView);

            //get references from the layout file
            imgIcon = itemView.findViewById(R.id.imgCountry);
            txtCityName = itemView.findViewById(R.id.txtCityName);
            txtTemperature = itemView.findViewById(R.id.txtTempValue);
            txtUserRating = itemView.findViewById(R.id.txtUserRatingValue);
            listener = countryItemClickedListener;


            //set click listener for whole list item
            itemView.setOnClickListener(this);
        }

        //react to user clicking the listitem (implements OnClickListener)
        @Override
        public void onClick(View v) {
            listener.onCountryClicked(getAdapterPosition());
        }
    }
}
