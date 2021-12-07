package my.mummyapp.bestmom.ui.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import my.mummyapp.bestmom.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar C = Calendar.getInstance();
        int hour = C.get(Calendar.HOUR_OF_DAY);
        int minutes = C.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), R.style.TimePickerTheme,(TimePickerDialog.OnTimeSetListener)getActivity(),hour
                ,minutes, DateFormat.is24HourFormat(getActivity()));
    }
}
