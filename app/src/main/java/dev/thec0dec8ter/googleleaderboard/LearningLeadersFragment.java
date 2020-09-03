package dev.thec0dec8ter.googleleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {

    public static LearningLeadersFragment newInstance() {
        return new LearningLeadersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.learning_leaders_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView leaderRecycler = view.findViewById(R.id.leader_recycler);
        final LeaderAdapter leaderAdapter = new LeaderAdapter(R.layout.item_learning_leader);
        leaderRecycler.setAdapter(leaderAdapter);

        GetLearningLeaderService leaderService = RetrofitClientInstance.getRetrofitInstance().create(GetLearningLeaderService.class);
        Call<ArrayList<Leader>> call = leaderService.getAllLeaders();
        call.enqueue(new Callback<ArrayList<Leader>>() {
            @Override
            public void onResponse(Call<ArrayList<Leader>> call, Response<ArrayList<Leader>> response) {
                leaderAdapter.setLeaders(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Leader>> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}