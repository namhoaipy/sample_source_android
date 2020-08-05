package nam.com.rsa_demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nam.com.rsa_demo.R;
import nam.com.rsa_demo.model.SimpleRSAModel;

public class SimpleListRSAAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> list = new ArrayList<>();
    private Context context;

    public SimpleListRSAAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_simple_rsa_item_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Holder h = (Holder) holder;
            SimpleRSAModel model = (SimpleRSAModel) list.get(position);
            h.simple_text.setText(model.getVal());
        }
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 ? list.size() : 0;
    }

    public void addList(List<Object> list) {
        Log.e("20202020",list.size()+"");
        if (list != null){
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView simple_text;

        public Holder(@NonNull View itemView) {
            super(itemView);
            simple_text = itemView.findViewById(R.id.simple_text);
        }
    }
}
