package dev.thec0dec8ter.googleleaderboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderAdapter extends  RecyclerView.Adapter<LeaderAdapter.LeaderVieHolder>{

    private ArrayList<Leader> leaders = new ArrayList<>();
    private int resource;

    public LeaderAdapter(int resource){
        this.resource = resource;
    }

    @NonNull
    @Override
    public LeaderVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new LeaderVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderVieHolder holder, int position) {
        Leader leader = leaders.get(position);
        holder.bind(leader);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class LeaderVieHolder extends RecyclerView.ViewHolder{
        ImageView badge;
        TextView txtName;
        TextView txtInfo;

        public LeaderVieHolder(@NonNull View itemView) {
            super(itemView);
            badge = itemView.findViewById(R.id.badge);
            txtName = itemView.findViewById(R.id.leader_name);
            txtInfo = itemView.findViewById(R.id.leader_info);
        }

        public void bind(Leader leader){
            if(resource == R.layout.item_learning_leader){
                showBadge(leader.getBadgeUrl());
                txtName.setText(leader.getName());
                txtInfo.setText(leader.getHours() + " learnng hours, " + leader.getCountry());

            }else if(resource == R.layout.item_skill_leader){
                showBadge(leader.getBadgeUrl());
                txtName.setText(leader.getName());
                txtInfo.setText(leader.getScore() + " skill IQ Score, " + leader.getCountry());
            }
        }

        private  void showBadge(String url){
            Picasso.get()
                    .load(url)
                    .into(badge);
        }
    }

    public void setLeaders(ArrayList<Leader> leaders){
        this.leaders = leaders;
        Collections.sort(this.leaders);
        Collections.reverse(this.leaders);
        notifyDataSetChanged();
    }
}
