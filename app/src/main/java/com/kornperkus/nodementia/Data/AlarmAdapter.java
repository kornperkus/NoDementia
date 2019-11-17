package com.kornperkus.nodementia.Data;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private List<Alarm> alarms = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;

    public AlarmAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_alarm_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.alarmTitle.setText(alarm.getTitle());
        holder.alarmTime.setText(new SimpleDateFormat("HH:mm").format(alarm.getTime()));
        if (alarm.getStatus() == Contract.AlarmEntry.STATUS_ON) {
            holder.alarmToggle.setImageResource(R.drawable.ic_alarm_on);
        } else {
            holder.alarmToggle.setImageResource(R.drawable.ic_alarm_off);
        }
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
        notifyDataSetChanged();
    }

    public Alarm getAlarmAt(int position) {
        return alarms.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView alarmTitle;
        public TextView alarmTime;
        public ImageView alarmToggle;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            alarmTitle = itemView.findViewById(R.id.alarm_title_tv);
            alarmTime = itemView.findViewById(R.id.alarm_time_tv);
            alarmToggle = itemView.findViewById(R.id.alarm_toggle_img);

            alarmTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar calendar = Calendar.getInstance();
                    TimePickerDialog timePicker;
                    timePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calendar2.set(Calendar.MINUTE, minute);
                            calendar2.set(Calendar.SECOND, 0);

                            Alarm alarm = alarms.get(getAdapterPosition());
                            alarm.setTime(calendar2.getTimeInMillis());
                            if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                                listener.onTimeClick(calendar2, alarm);
                            }
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePicker.setTitle("Pick");
                    timePicker.show();
                }
            });

            alarmToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   int position = getAdapterPosition();
                   Alarm alarm = alarms.get(position);
                   Calendar c = Calendar.getInstance();
                   c.setTimeInMillis(alarm.getTime());
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onStatusClick(c, alarm);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onStatusClick(Calendar c, Alarm alarm);
        void onTimeClick(Calendar c, Alarm alarm);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}