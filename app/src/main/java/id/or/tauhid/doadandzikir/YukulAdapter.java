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

public class YukulAdapter extends RecyclerView.Adapter<YukulViewHolder> implements Filterable {

    private Context context;
    private List<YukulModel> yukulModelList;
    private List<YukulModel> yukulModelListFiltered;
    private ContactsAdapterListener listener;

    public YukulAdapter(Context context, List<YukulModel> yukulModelList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.yukulModelList = yukulModelList;
        this.yukulModelListFiltered = yukulModelList;
    }

    @Override
    public YukulViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_row_item_yukul, parent, false);
        return new YukulViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(YukulViewHolder holder, final int position) {
        final YukulModel yukulModel = yukulModelListFiltered.get(position);
        holder.name.setText(yukulModel.getName());
        holder.nomor.setText(yukulModel.getNomor());
    }

    @Override
    public int getItemCount() {
        return yukulModelListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    yukulModelListFiltered = yukulModelList;
                } else {
                    List<YukulModel> filteredList = new ArrayList<>();
                    for (YukulModel row : yukulModelList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getNomor().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    yukulModelListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = yukulModelListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                yukulModelListFiltered = (ArrayList<YukulModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(YukulModel yukulModel);
    }
}
