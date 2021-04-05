package id.or.tauhid.doadandzikir;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

public class TidurAdapter extends RecyclerView.Adapter<TidurViewHolder> implements Filterable {

    private Context context;
    private List<TidurModel> tidurModelList;
    private List<TidurModel> tidurModelListFiltered;
    private ContactsAdapterListener listener;

    public TidurAdapter(Context context, List<TidurModel> tidurModelList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.tidurModelList = tidurModelList;
        this.tidurModelListFiltered = tidurModelList;
    }

    @Override
    public TidurViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_row_item_tidur, parent, false);
        return new TidurViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TidurViewHolder holder, final int position) {
        final TidurModel tidurModel = tidurModelListFiltered.get(position);
        holder.name.setText(tidurModel.getName());
        holder.nomor.setText(tidurModel.getNomor());
    }

    @Override
    public int getItemCount() {
        return tidurModelListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    tidurModelListFiltered = tidurModelList;
                } else {
                    List<TidurModel> filteredList = new ArrayList<>();
                    for (TidurModel row : tidurModelList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getNomor().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    tidurModelListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tidurModelListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tidurModelListFiltered = (ArrayList<TidurModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(TidurModel tidurModel);
    }
}
