package id.or.tauhid.doadandzikir;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> implements Filterable {

    private Context context;
    private List<HomeModel> homeModelList;
    private List<HomeModel> homeModelListFiltered;
    private ContactsAdapterListener listener;


    public HomeAdapter(Context context, List<HomeModel> homeModelList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.homeModelList = homeModelList;
        this.homeModelListFiltered = homeModelList;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_home, parent, false);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, final int position) {
        final HomeModel homeModel = homeModelListFiltered.get(position);
        holder.name.setText(homeModel.getName());

        Glide.with(context)
                .load(homeModel.getImage())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return homeModelListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    homeModelListFiltered = homeModelList;
                } else {
                    List<HomeModel> filteredList = new ArrayList<>();
                    for (HomeModel row : homeModelList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    homeModelListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = homeModelListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                homeModelListFiltered = (ArrayList<HomeModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(HomeModel homeModel);
    }
}
