package uk.ac.tees.w9544151.admin;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uk.ac.tees.w9544151.Adapters.AdapterCallback;
import uk.ac.tees.w9544151.Adapters.CartAdapter;
import uk.ac.tees.w9544151.Adapters.HomeAdapter;
import uk.ac.tees.w9544151.Models.CartModel;
import uk.ac.tees.w9544151.Models.Foodmodel;
import uk.ac.tees.w9544151.R;
import uk.ac.tees.w9544151.databinding.FragmentCartListBinding;
import uk.ac.tees.w9544151.databinding.FragmentFoodListBinding;


public class FoodListFragment extends Fragment implements AdapterCallback {
    FragmentFoodListBinding binding;
    HomeAdapter adapter=new HomeAdapter(this);
    List<Foodmodel> foodList = new ArrayList();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback( this,new OnBackPressedCallback(true){
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(getView()).navigateUp();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentFoodListBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int i=0;i<5;i++) {
            foodList.add(new Foodmodel("f001","Coffee","20","nil"));

        }
        binding.rvFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.fooodList=foodList;
        binding.rvFood.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMethodCallback() {

    }
}