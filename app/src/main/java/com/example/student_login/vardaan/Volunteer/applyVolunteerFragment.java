package com.example.student_login.vardaan.Volunteer;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.student_login.vardaan.ApiUtlis;
import com.example.student_login.vardaan.Contact.APIService;
import com.example.student_login.vardaan.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.text.DateFormat.FULL;

/**
 * A simple {@link Fragment} subclass.
 */
public class applyVolunteerFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    Spinner spinner,spinner1;
    EditText BDate,Fname,Lname,Email,Cnumber,city;
    private String firstName,lastName,City,emailID,cNumber,userGender,userProfession;
    Button submitBtn,clearBtn;
    RadioGroup RbGender,RGProfession;
    RadioButton genderMale,genderFemale,rbStudent,rbCorporatePerson;


    private AddAPIService vAPIService;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_apply_volunteer, container, false);

        spinner=view.findViewById(R.id.Preference);
        spinner1=view.findViewById(R.id.bloodGroup);

        BDate=view.findViewById(R.id.DateBox);
        Fname=view.findViewById(R.id.FirstName);
        Lname=view.findViewById(R.id.LastName);
        Email=view.findViewById(R.id.EmailID);
        Cnumber=view.findViewById(R.id.ContactNumber);
        city=view.findViewById(R.id.City);

        RbGender=view.findViewById(R.id.RbGender);
        genderMale=view.findViewById(R.id.genderMale);
        genderFemale=view.findViewById(R.id.genderFemale);

        RGProfession=view.findViewById(R.id.RBProfession);
        rbStudent=view.findViewById(R.id.rbStudent);
        rbCorporatePerson=view.findViewById(R.id.rbCorporatePerson);





        submitBtn=view.findViewById(R.id.btnmSubmit);
        clearBtn=view.findViewById(R.id.btnClear);

        vAPIService = (AddAPIService) ApiUtlis.getAddAPIService();

        RbGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.genderMale:
                            userGender="Male";

                        break;
                    case R.id.genderFemale:
                            userGender="Female";
                        break;
                }
            }
        });

        RGProfession.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbStudent:
                            userProfession="Student";
                        break;
                    case R.id.rbCorporatePerson:
                            userProfession="Corporate Person";

                        break;
                }
            }
        });




        BDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker=new DatePickerFragment();
                datepicker.show(getFragmentManager(),"Date Picker");
            }
        });



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.preference, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.bloodGroup, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    submit();
                Fname.setText("");
                Lname.setText("");
                city.setText("");
                Email.setText("");
                Cnumber.setText("");
                BDate.setText("");
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fname.setText("");
                Lname.setText("");
                city.setText("");
                Email.setText("");
                Cnumber.setText("");
                BDate.setText("");


            }
        });





        return view;
    }


    private void submit() {
        initialize();
        if(!validate()){
            Toast.makeText(getContext(),"Submit is not successfull",Toast.LENGTH_LONG).show();
        }
        else{
            onSubmitSuccess();
        }
    }

    private void sendPost(String fName, String lName, String email_id, String contact_number, String city, String availbility, String gender,String birth_date,String blood_group,String profession) {
        Call<String> call=vAPIService.getStringScalar(fName,lName,email_id,contact_number,city,availbility,gender,birth_date,blood_group,profession);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               // Log.i("Response",response.body());

                    Toast.makeText(getContext(), response.body(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("EX",t.toString());
                Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
            }
        });
    }


    private boolean validate() {
        boolean valid=true;
        if(firstName.isEmpty()||firstName.length()>32){
            Fname.setError("Please Enter Valid First Name");
            valid=false;
        }
        if(lastName.isEmpty()||lastName.length()>32){
            Lname.setError("Please Enter Valid Last Name");
            valid=false;
        }
        if(City.isEmpty()||City.length()>32){
            city.setError("Please Enter Valid City Name");
            valid=false;
        }
        if(emailID.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(emailID).matches()){
            Email.setError("Please Enter Valid Email Address");
            valid=false;
        }
        if(cNumber.isEmpty()||cNumber.length()>10){
            Cnumber.setError("Please Enter Valid Contact Number");
            valid=false;
        }


        return valid;
    }


    private void onSubmitSuccess() {
        sendPost(Fname.getText().toString(), Lname.getText().toString(),Email.getText().toString(), Cnumber.getText().toString(),city.getText().toString(),spinner.getSelectedItem().toString(),userGender,BDate.getText().toString(),spinner1.getSelectedItem().toString(),userProfession);
    }


    private void initialize() {
        firstName=Fname.getText().toString().trim();
        lastName=Lname.getText().toString().trim();
        City=city.getText().toString().trim();
        emailID=Email.getText().toString().trim();
        cNumber=Cnumber.getText().toString().trim();


    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString =DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        BDate.setText(currentDateString);
    }
}
