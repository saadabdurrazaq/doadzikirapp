package id.or.tauhid.doadandzikir;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAboutUs extends ArrayAdapter<String> {
    private String[] names;
    private Integer[] imageid;
    private Activity context;

    public ListAboutUs(Activity context, String[] names, Integer[] imageid) {
        super(context, R.layout.list_about_us, names);
        this.context = context;
        this.names = names;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_about_us, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);

        textViewName.setText(names[position]);
        image.setImageResource(imageid[position]);

        return  listViewItem;
    }

}
