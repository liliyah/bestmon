package my.mummyapp.bestmom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import my.mummyapp.bestmom.R;
import my.mummyapp.bestmom.ui.models.DataModel;

import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<DataModel> mlist ;
    private Context context;

    public CalenderAdapter(List<DataModel> daysOfTheMonth , Context context) {
        this.mlist = daysOfTheMonth;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item_unchecked,null);
        MyViewHolder viewHolder= new MyViewHolder(view) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel model = mlist.get(position);

           holder.textDaynumber.setText(String.valueOf(model.getMdayNumber()));

          //Log.d("imageresourcenumber", "onBindViewHolder: "+String.valueOf(model.getImageresource()));
            if (model.getIs_task_done()==1 &&model.getIs_task_not_done()==0){
                holder.image.setImageResource(R.drawable.greenchecked);
                holder.layout.setBackgroundResource(R.drawable.lightpista);
            } if (model.getIs_task_done()==0 &&model.getIs_task_not_done()==1){
                holder.image.setImageResource(R.drawable.unchecked);
            holder.layout.setBackgroundResource(R.drawable.light_peach_bg);
            }
        if (model.getIs_task_done()==0 &&model.getIs_task_not_done()==0){
            holder.image.setVisibility(View.INVISIBLE);
            holder.layout.setBackgroundResource(R.drawable.light_yellow_bg);
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

}
class MyViewHolder extends RecyclerView.ViewHolder{
public TextView textDaynumber;
public LinearLayout layout;
public ImageView image ;
    public MyViewHolder(@NonNull View itemView)
    {
        super(itemView);
        textDaynumber =(TextView)itemView.findViewById(R.id.text_calendar_day);
        layout= (LinearLayout) itemView.findViewById(R.id.linear_cardview);
        image= (ImageView) itemView.findViewById(R.id.imagechecked_unchecked);
    }
}
