package hiendtt21020315.uet.mobile.admin.statis;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hiendtt21020315.uet.mobile.R;


public class StatisFragment extends Fragment {

    Button btn_tuNgay, btn_denNgay, btn_doanhThu;
    TextView tv_tuNgay, tv_denNgay, tv_doanhThu;
    StatisDAO statisDAO;
    private final Calendar myCalendar = Calendar.getInstance();

    public StatisFragment() {
        // Required empty public constructor
    }


    public static StatisFragment newInstance() {
        StatisFragment fragment = new StatisFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_statis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_tuNgay = view.findViewById(R.id.btn_tuNgay);
        btn_denNgay = view.findViewById(R.id.btn_denNgay);
        btn_doanhThu = view.findViewById(R.id.btn_doanhThu);
        tv_tuNgay = view.findViewById(R.id.tv_tuNgay);
        tv_denNgay = view.findViewById(R.id.tv_denNgay);
        tv_doanhThu = view.findViewById(R.id.tv_doanhThu);
        statisDAO = new StatisDAO(getContext());
        btn_tuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Date selectedDate = myCalendar.getTime();
                        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                                "dd/MM/yyyy");
                        tv_tuNgay.setText(dateFormatter.format(selectedDate));

                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btn_denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Date selectedDate = myCalendar.getTime();
                        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                                "dd/MM/yyyy");
                        tv_denNgay.setText(dateFormatter.format(selectedDate));

                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btn_doanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String tuNgay = tv_tuNgay.getText().toString();
                    String denNgay = tv_denNgay.getText().toString();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1 = sdf.parse(tuNgay);
                    Date date2 = sdf.parse(denNgay);
                    Date date = new Date();
                    int comparisonResult = date2.compareTo(date);
                    int compare =date2.compareTo(date1);
                    if(compare<0){
                        Toast.makeText(getContext(), "Chọn ngày thống kê không hợp lệ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (comparisonResult > 0) {
                        date2 = date;
                        String date3 = sdf.format(date2);
                        tv_doanhThu.setText(statisDAO.getDoanhThu(tuNgay, date3) + "" + " VND");
                    } else {
                        tv_doanhThu.setText(statisDAO.getDoanhThu(tuNgay, denNgay) + "" + " VND");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}