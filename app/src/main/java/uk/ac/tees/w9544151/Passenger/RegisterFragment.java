package uk.ac.tees.w9544151.Passenger;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Random;

import uk.ac.tees.w9544151.Models.UserModel;
import uk.ac.tees.w9544151.R;
import uk.ac.tees.w9544151.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db;
    FragmentRegisterBinding binding;
    private EditText emailTextView, passwordTextView,nameTextView,mobileTextView;
    private AppCompatTextView Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=FirebaseStorage.getInstance().getReference();
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(getView()).navigateUp();
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();



        // initialising all views through id defined above
        emailTextView = binding.etEmail;
        passwordTextView =binding.etPassword;
        nameTextView =binding.etName;
        mobileTextView =binding.etMobile;
        Btn = binding.btnAddUser;
        progressbar = binding.progressbar;
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email,name,mobile,password;
                email=binding.etEmail.getText().toString();
                password=binding.etPassword.getText().toString();
                name=binding.etName.getText().toString();
                mobile=binding.etMobile.getText().toString();
                fireStoreDatabase : FirebaseFirestore.getInstance();
                UserModel obj=new UserModel("user",name,mobile,email,password);
                db=FirebaseFirestore.getInstance();
                db.collection("User").add(obj).
                        addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Snackbar.make(requireView(),"User added Successfully",Snackbar.LENGTH_LONG).show();
                            }
                        }).
                        addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(),"Creation failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                //registerNewUser();

            }
        });
        /*binding.btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int number = random.nextInt(100000);
                Log.d("@@", "User-"+number+"");
                if (binding.etName.getText().toString().isEmpty())
                    binding.etName.setError("Enter your name");
                else if (binding.etMobile.getText().toString().isEmpty())
                    binding.etMobile.setError("Enter mobile number");
                else if (binding.etEmail.getText().toString().isEmpty())
                    binding.etEmail.setError("Enter your username or password");
                else if (binding.etPassword.getText().toString().isEmpty())
                    binding.etPassword.setError("Enter your password");
                else {
                    //set the long seed value using Random constructor

                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                }
            }
        });*/
    }

    private void registerNewUser()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            binding.progressbar.setVisibility(View.GONE);
                           // progressBar.setVisibility(View.GONE);

                            // if the user created intent to login activity
                            Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                            getContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            binding.progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
