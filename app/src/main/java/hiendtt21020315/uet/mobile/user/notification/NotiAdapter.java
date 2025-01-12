package hiendtt21020315.uet.mobile.user.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hiendtt21020315.uet.mobile.R;
import hiendtt21020315.uet.mobile.user.home.comment.Comment;
import hiendtt21020315.uet.mobile.user.home.comment.CommentAdapter;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {
    Context context;
    ArrayList<Noti> list;

    public NotiAdapter(Context context, ArrayList<Noti> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(ArrayList<Noti> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_noti, parent, false);
        return new NotiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTime.setText(list.get(position).getTime());
        holder.tvStatus.setText(list.get(position).getStatus());
        holder.tvcontent.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime, tvStatus,tvcontent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tv_item_noti_status);
            tvTime = itemView.findViewById(R.id.tv_item_noti_time);
            tvcontent=itemView.findViewById(R.id.tv_item_noti_content);
        }
    }
}
