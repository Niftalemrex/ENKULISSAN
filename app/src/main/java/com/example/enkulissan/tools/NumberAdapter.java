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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberAdapter extends BaseAdapter {

    private Context context;
    private List<NumberItem> numberList;
    private LayoutInflater inflater;

    public NumberAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        initNumbers();
    }

    private void initNumbers() {
        numberList = new ArrayList<>();

        String[] ethiopicUnits = {"", "፩", "፪", "፫", "፬", "፭", "፮", "፯", "፰", "፱"};
        String[] ethiopicTens = {"", "፲", "፳", "፴", "፵", "፶", "፷", "፸", "፹", "፺"};
        Map<String, String> translitMap = getTransliterationMap();

        for (int i = 1; i < 100; i++) {
            int tens = i / 10;
            int units = i % 10;

            StringBuilder ethiopic = new StringBuilder();
            if (tens > 0) ethiopic.append(ethiopicTens[tens]);
            if (units > 0 || i == 10) ethiopic.append(ethiopicUnits[units]);

            String symbol = ethiopic.toString();
            String translit = translitMap.getOrDefault(symbol, ""); // fallback

            numberList.add(new NumberItem(symbol, String.valueOf(i), translit));
        }

        numberList.add(new NumberItem("፻", "100", "meeto"));
    }

    @Override
    public int getCount() {
        return numberList.size();
    }

    @Override
    public Object getItem(int position) {
        return numberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_number, parent, false);
            holder = new ViewHolder();
            holder.tvNumber = convertView.findViewById(R.id.tv_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NumberItem item = numberList.get(position);
        holder.tvNumber.setText(item.ethiopic);

        // Single tap → Arabic number
        holder.tvNumber.setOnClickListener(v -> showPopup(v, item.arabic));

        // Long press → sound + transliteration
        holder.tvNumber.setOnLongClickListener(v -> {
            showPopup(v, item.translit);

            int resId = getSoundResourceId(item.ethiopic);
            if (resId != 0) {
                MediaPlayer mp = MediaPlayer.create(context, resId);
                mp.setOnCompletionListener(MediaPlayer::release);
                mp.start();
            } else {
                Toast.makeText(context, "No sound for " + item.ethiopic, Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        return convertView;
    }

    private void showPopup(View anchorView, String text) {
        View popupView = inflater.inflate(R.layout.popup_number, null);
        TextView tvPopup = popupView.findViewById(R.id.tv_popup_number);
        tvPopup.setText(text);

        final PopupWindow popupWindow = new PopupWindow(
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

    private int getSoundResourceId(String symbol) {
        switch (symbol) {
            case "፩": return R.raw.one;
            case "፪": return R.raw.two;
            case "፫": return R.raw.three;
            case "፬": return R.raw.four;
            case "፭": return R.raw.five;
            case "፮": return R.raw.six;
            case "፯": return R.raw.seven;
            case "፰": return R.raw.eight;
            case "፱": return R.raw.nine;
            case "፲": return R.raw.ten;
            // Add more sound resources if you have them
            default: return 0;
        }
    }

    private Map<String, String> getTransliterationMap() {
        Map<String, String> map = new HashMap<>();
        map.put("፩", "and");   map.put("፪", "hulät");
        map.put("፫", "sost");  map.put("፬", "arat");
        map.put("፭", "ammest");map.put("፮", "sidist");
        map.put("፯", "säbat"); map.put("፰", "sämint");
        map.put("፱", "zät’äñ");map.put("፲", "äser");
        map.put("፻", "meeto");
        // Add more if needed
        return map;
    }

    static class ViewHolder {
        TextView tvNumber;
    }

    static class NumberItem {
        String ethiopic;
        String arabic;
        String translit;

        NumberItem(String ethiopic, String arabic, String translit) {
            this.ethiopic = ethiopic;
            this.arabic = arabic;
            this.translit = translit;
        }
    }
}
