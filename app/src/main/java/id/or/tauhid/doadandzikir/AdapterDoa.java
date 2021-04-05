package id.or.tauhid.doadandzikir;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static id.or.tauhid.doadandzikir.ModelDoa.BACA_DOA;

public class AdapterDoa extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<ModelDoa> mList;

    public AdapterDoa(List<ModelDoa> list) {

        this.mList = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case BACA_DOA:
                View vieu = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_doa, parent, false);
                DoaViewHolder rcv = new DoaViewHolder(vieu, this); //this, code untuk hapus row
                return rcv;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ModelDoa object = mList.get(position);

        if (object != null) {

            switch (object.getType()) {

                case BACA_DOA:
                    ((DoaViewHolder) holder).mTitle.setText(object.getName());
                    ((DoaViewHolder) holder).mLatin.setText(object.getLatin());
                    ((DoaViewHolder) holder).mArti.setText(object.getArti());
                    ((DoaViewHolder) holder).mSumber.setText(object.getSumber());
                    ((DoaViewHolder) holder).tombolbaca.setText(object.ambilName());
                    break;
            }
        }
    }

    // Baca clickcount
    public ModelDoa getItem(int position) {
        if (position > -1   && position < getItemCount()) {
            return this.mList.get(position);
        } else {
            return null;
        }
    }

    public void deleteItem(int position) {
        mList.remove(position); // hapus list
        notifyItemRemoved(position);
        // notifyItemRangeChanged( position, mList.size());
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            ModelDoa object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }


}