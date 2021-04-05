package id.or.tauhid.doadandzikir;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class SizeLatinFont implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        ((TextView) view).setTextColor(Color.parseColor("#226169"));

        switch(pos) {
            case 0:
                Activity activity = (Activity) parent.getContext();
                TextView dgs = (TextView) activity.findViewById(R.id.tekzLatin);
                dgs.setTextSize(12);
                break;
            case 1:
                Activity activitys = (Activity) parent.getContext();
                TextView dgf = (TextView) activitys.findViewById(R.id.tekzLatin);
                dgf.setTextSize(14);
                break;
            case 2:
                Activity activityz = (Activity) parent.getContext();
                TextView dgy = (TextView) activityz.findViewById(R.id.tekzLatin);
                dgy.setTextSize(16);
                break;
            case 3:
                Activity activitye = (Activity) parent.getContext();
                TextView dgq = (TextView) activitye.findViewById(R.id.tekzLatin);
                dgq.setTextSize(18);
                break;
            case 4:
                Activity activityq = (Activity) parent.getContext();
                TextView dgp = (TextView) activityq.findViewById(R.id.tekzLatin);
                dgp.setTextSize(20);
                break;
            case 5:
                Activity activityc = (Activity) parent.getContext();
                TextView dgn = (TextView) activityc.findViewById(R.id.tekzLatin);
                dgn.setTextSize(22);
                break;
            case 6:
                Activity activitym = (Activity) parent.getContext();
                TextView dgb = (TextView) activitym.findViewById(R.id.tekzLatin);
                dgb.setTextSize(24);
                break;
            case 7:
                Activity activityo = (Activity) parent.getContext();
                TextView dgl = (TextView) activityo.findViewById(R.id.tekzLatin);
                dgl.setTextSize(26);
                break;

            default:
                Activity activityu = (Activity) parent.getContext();
                TextView dgx = (TextView) activityu.findViewById(R.id.tekzLatin);
                dgx.setTextSize(24);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
