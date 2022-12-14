package uk.ac.tees.w9544151.admin;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import uk.ac.tees.w9544151.Models.TrainModel;
import uk.ac.tees.w9544151.Models.UserModel;
import uk.ac.tees.w9544151.R;
import uk.ac.tees.w9544151.databinding.FragmentRegisterBinding;
import uk.ac.tees.w9544151.databinding.FragmentRegisterTrainBinding;


public class RegisterTrainFragment extends Fragment {
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db;

    FragmentRegisterTrainBinding binding;
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
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=FirebaseStorage.getInstance().getReference();
        // Inflate the layout for this fragment
        binding = FragmentRegisterTrainBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnAddTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String id,trainName,trainNumber,start,destiny;
                trainName=binding.etTrainName.getText().toString();
                id="T"+binding.etTrainNumber.getText().toString();
                trainNumber=binding.etTrainNumber.getText().toString();
                start=binding.etStart.getText().toString();
                destiny=binding.etDestiny.getText().toString();
                fireStoreDatabase : FirebaseFirestore.getInstance();
                TrainModel obj=new TrainModel(id,trainNumber,trainName,start,destiny);
                db=FirebaseFirestore.getInstance();
                db.collection("Train").add(obj).
                        addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                binding.etTrainName.getText().clear();
                                binding.etTrainNumber.getText().clear();
                                binding.etStart.getText().clear();
                                binding.etDestiny.getText().clear();
                                Snackbar.make(requireView(),"Train added Successfully",Snackbar.LENGTH_LONG).show();
                            }
                        }).
                        addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(),"Creation failed", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
}