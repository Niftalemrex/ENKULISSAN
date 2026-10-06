package com.example.enkulissan.tools;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enkulissan.R;

import java.util.ArrayList;
import java.util.List;

public class DayMonthAdapter extends BaseAdapter {

    private Context context;
    private List<DayMonthItem> items;
    private LayoutInflater inflater;

    public DayMonthAdapter(Context context, boolean isWeekdays) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        items = new ArrayList<>();

        if (isWeekdays) {
            String[] fidel = {"ሰኞ", "ማክሰኞ", "ረቡዕ", "ሐሙስ", "አርብ", "ቅዳሜ", "እሁድ"};
            String[] english = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            String[] translit = {"sego", "maksegno", "rebu", "hamus", "arb", "kidame", "ehud"};
            for (int i = 0; i < fidel.length; i++) {
                items.add(new DayMonthItem(fidel[i], english[i], translit[i]));
            }
        } else {
            String[] fidel = {
                    "መስከረም", "ጥቅምት", "ኅዳር", "ታኅሣሥ",
                    "ጥር", "የካቲት", "መጋቢት", "ሚያዝያ",
                    "ግንቦት", "ሰኔ", "ሐምሌ", "ነሐሴ", "ጳጉሜ"
            };
            String[] english = {
                    "September", "October", "November", "December",
                    "January", "February", "March", "April",
                    "May", "June", "July", "August", "Pagume"
            };
            String[] translit = {
                    "Meskerem", "Tikimt", "Hidar", "Tahsas",
                    "Tir", "Yekatit", "Megabit", "Miyazya",
                    "Ginbot", "Sene", "Hamle", "Nehase", "Pagume"
            };
            for (int i = 0; i < fidel.length; i++) {
                items.add(new DayMonthItem(fidel[i], english[i], translit[i]));
            }
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_day_month, parent, false);
            holder = new ViewHolder();
            holder.tv = convertView.findViewById(R.id.tv_day_month);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DayMonthItem item = items.get(position);
        holder.tv.setText(item.fidel);

        // 🔹 Single tap → show English
        holder.tv.setOnClickListener(v -> showPopup(v, item.english));

        // 🔸 Long press → show transliteration + sound
        holder.tv.setOnLongClickListener(v -> {
            showPopup(v, item.translit);
            int soundRes = getSoundResourceId(item.fidel);
            if (soundRes != 0) {
                MediaPlayer mp = MediaPlayer.create(context, soundRes);
                mp.setOnCompletionListener(MediaPlayer::release);
                mp.start();
            } else {
                Toast.makeText(context, "No sound for " + item.fidel, Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        return convertView;
    }

    private void showPopup(View anchorView, String text) {
        View popupView = inflater.inflate(R.layout.popup_day_month, null);
        TextView tvPopup = popupView.findViewById(R.id.tv_popup_day_month);
        tvPopup.setText(text);

        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        popupWindow.setBackgroundDrawable(context.getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);

        popupWindow.showAsDropDown(anchorView, 0, -anchorView.getHeight() * 2);
        popupView.postDelayed(popupWindow::dismiss, 2000);
    }

    private int getSoundResourceId(String fidel) {
        switch (fidel) {
          //  case "ሰኞ": return R.raw.monday;
         //   case "ማክሰኞ": return R.raw.tuesday;
        //    case "ረቡዕ": return R.raw.wednesday;
        //    case "ሐሙስ": return R.raw.thursday;
        //    case "አርብ": return R.raw.friday;
       //     case "ቅዳሜ": return R.raw.saturday;
       //     case "እሁድ": return R.raw.sunday;

      //      case "መስከረም": return R.raw.meskerem;
      //      case "ጥቅምት": return R.raw.tikimt;
     //       case "ኅዳር": return R.raw.hidar;
    //        case "ታኅሣሥ": return R.raw.tahsas;
    //        case "ጥር": return R.raw.tir;
    //        case "የካቲት": return R.raw.yekatit;
    //        case "መጋቢት": return R.raw.megabit;
      //      case "ሚያዝያ": return R.raw.miyazya;
      //      case "ግንቦት": return R.raw.ginbot;
      //      case "ሰኔ": return R.raw.sene;
      //      case "ሐምሌ": return R.raw.hamle;
        //    case "ነሐሴ": return R.raw.nehase;
     //       case "ጳጉሜ": return R.raw.pagume;
            default: return 0;
        }
    }

    static class ViewHolder {
        TextView tv;
    }

    private static class DayMonthItem {
        String fidel;
        String english;
        String translit;

        DayMonthItem(String fidel, String english, String translit) {
            this.fidel = fidel;
            this.english = english;
            this.translit = translit;
        }
    }
}
