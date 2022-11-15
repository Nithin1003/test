package uk.ac.tees.w9544151;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import uk.ac.tees.w9544151.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    private EditText emailTextView, passwordTextView,nameTextView,mobileTextView;
    private AppCompatTextView Btn;
    //private ProgressBar progressbar;
   // private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        binding = FragmentLoginBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etUsername.getText().toString().isEmpty()) {
                    binding.etUsername.setError("Please Enter Username");
                } else if (binding.etPassword.getText().toString().isEmpty()) {
                    binding.etUsername.setError("Enter Password");
                } else {


                    final ProgressDialog progressDoalog = new ProgressDialog(requireContext());
                    progressDoalog.setMessage("Checking....");
                    progressDoalog.setTitle("Please wait");
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.show();
                    if (binding.etUsername.getText().toString().equals("admin"))
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_adminHomeFragment);
                    else if (binding.etUsername.getText().toString().equals("passenger"))
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_foodHomeFragment2);
                    else if (binding.etUsername.getText().toString().equals("dboy"))
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_DBoyHomeFragment);


                    progressDoalog.dismiss();
                    binding.etUsername.setText("");
                    binding.etPassword.setText("");
                }

            }
        });
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
}

/**
 MD5: 61:55:AE:63:CD:D1:34:1E:C2:C3:17:6C:84:2D:D3:D9
 SHA1: DB:EB:79:F4:BB:86:51:DA:AE:01:A6:50:B9:5F:E5:2C:50:19:79:BF
 SHA-256: E2:9F:45:9C:B8:CF:86:A4:1B:38:34:23:32:82:B5:5F:D4:02:E7:97:96:66:5F:CB:B2:0D:57:D3:90:90:3C:7C

 */