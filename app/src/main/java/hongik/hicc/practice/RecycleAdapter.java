package hongik.hicc.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<Holder> {
    ArrayList<String> titleList, dateList;

    RecycleAdapter(ArrayList<String> tList, ArrayList<String> dList) {
        this.titleList = tList;
        this.dateList = dList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.title.setText(titleList.get(position));
        holder.date.setText(dateList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}

class Holder extends RecyclerView.ViewHolder {
    TextView title, date;

    public Holder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleTxt);
        date = itemView.findViewById(R.id.dateTxt);

    }
}