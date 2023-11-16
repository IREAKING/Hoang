package com.example.aphelios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentAdapter extends ArrayAdapter<Student> {

    StudentAdapter(Context context, ArrayList<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        CircleImageView studentImageView = convertView.findViewById(R.id.studentImage);
        TextView studentNameTextView = convertView.findViewById(R.id.studentName);
        TextView studentQueQuan = convertView.findViewById(R.id.hoang);
        TextView studentYearTextView = convertView.findViewById(R.id.year);

        Picasso.get().load(student != null ? student.getHinhanh() : "").into(studentImageView);
        studentNameTextView.setText(student != null ? student.getTen() : "");
        studentQueQuan.setText(student != null ? student.getQuequan() : "");
        studentYearTextView.setText("Tuổi: " + (student != null ? String.valueOf(student.getTuoi()) : ""));

        return convertView;
    }
}
