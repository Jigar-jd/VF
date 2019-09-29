package com.example.student_login.vardaan.Project;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.student_login.vardaan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailsFragment extends Fragment {


    public ProjectDetailsFragment() {
        // Required empty public constructor
    }

    View view;
    Context context;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView tvId,tvTitle,tvDate,tvPlace,tvCoordinator,tvDesc;

        context=getActivity();
        view=inflater.inflate(R.layout.fragment_project_details, container, false);
        Bundle bundle=getArguments();
        ProjectList list=bundle.getParcelable("PROJECT");

        tvId=view.findViewById(R.id.tvID);
        tvTitle=view.findViewById(R.id.tvTitle);
        tvDate=view.findViewById(R.id.tvDate);
        tvPlace=view.findViewById(R.id.tvPlace);
        tvCoordinator=view.findViewById(R.id.tvCoordinator);
        tvDesc=view.findViewById(R.id.tvDesc);


//        Toast.makeText(context, list.toString(), Toast.LENGTH_SHORT).show();
        tvId.setText(list.getID()+"");
        tvTitle.setText(list.getTitle());
        tvDate.setText(list.getDate()+"");
        tvPlace.setText(list.getPlace());
        tvCoordinator.setText(list.getCoordinator());
        tvDesc.setText(Html.fromHtml(list.getDescription()+ System.getProperty("line.separator")),null);




        return view;
    }

}
