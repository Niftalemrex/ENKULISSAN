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
import java.util.HashMap;

public class AlphabetAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;

    private final String[] ethiopianAlphabet = {
            "ሀ", "ሁ", "ሂ", "ሃ", "ሄ", "ህ", "ሆ",
            "ለ", "ሉ", "ሊ", "ላ", "ሌ", "ል", "ሎ",
            "መ", "ሙ", "ሚ", "ማ", "ሜ", "ም", "ሞ",
            "ሠ", "ሡ", "ሢ", "ሣ", "ሤ", "ሥ", "ሦ",
            "ረ", "ሩ", "ሪ", "ራ", "ሬ", "ር", "ሮ",
            "ሰ", "ሱ", "ሲ", "ሳ", "ሴ", "ስ", "ሶ",
            "ሸ", "ሹ", "ሺ", "ሻ", "ሼ", "ሽ", "ሾ",
            "ቀ", "ቁ", "ቂ", "ቃ", "ቄ", "ቅ", "ቆ",
            "በ", "ቡ", "ቢ", "ባ", "ቤ", "ብ", "ቦ",
            "ተ", "ቱ", "ቲ", "ታ", "ቴ", "ት", "ቶ",
            "ቸ", "ቹ", "ቺ", "ቻ", "ቼ", "ች", "ቾ",
            "ኀ", "ኁ", "ኂ", "ኃ", "ኄ", "ኅ", "ኆ",
            "ነ", "ኑ", "ኒ", "ና", "ኔ", "ን", "ኖ",
            "ኘ", "ኙ", "ኚ", "ኛ", "ኜ", "ኝ", "ኞ",
            "አ", "ኡ", "ኢ", "ኣ", "ኤ", "እ", "ኦ",
            "ከ", "ኩ", "ኪ", "ካ", "ኬ", "ክ", "ኮ",
            "ኸ", "ኹ", "ኺ", "ኻ", "ኼ", "ኽ", "ኾ",
            "ወ", "ዉ", "ዊ", "ዋ", "ዌ", "ው", "ዎ",
            "ዐ", "ዑ", "ዒ", "ዓ", "ዔ", "ዕ", "ዖ",
            "ዘ", "ዙ", "ዚ", "ዛ", "ዜ", "ዝ", "ዞ",
            "ዠ", "ዡ", "ዢ", "ዣ", "ዤ", "ዥ", "ዦ",
            "የ", "ዩ", "ዪ", "ያ", "ዬ", "ይ", "ዮ",
            "ደ", "ዱ", "ዲ", "ዳ", "ዴ", "ድ", "ዶ",
            "ጀ", "ጁ", "ጂ", "ጃ", "ጄ", "ጅ", "ጆ",
            "ገ", "ጉ", "ጊ", "ጋ", "ጌ", "ግ", "ጎ",
            "ጠ", "ጡ", "ጢ", "ጣ", "ጤ", "ጥ", "ጦ",
            "ጨ", "ጩ", "ጪ", "ጫ", "ጬ", "ጭ", "ጮ",
            "ጰ", "ጱ", "ጲ", "ጳ", "ጴ", "ጵ", "ጶ",
            "ጸ", "ጹ", "ጺ", "ጻ", "ጼ", "ጽ", "ጾ",
            "ፀ", "ፁ", "ፂ", "ፃ", "ፄ", "ፅ", "ፆ",
            "ፈ", "ፉ", "ፊ", "ፋ", "ፌ", "ፍ", "ፎ",
            "ፐ", "ፑ", "ፒ", "ፓ", "ፔ", "ፕ", "ፖ"
    };



    private final HashMap<String, String> amharicToEnglishMap;

    public AlphabetAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.amharicToEnglishMap = createMapping();
    }

    private HashMap<String, String> createMapping() {
        HashMap<String, String> map = new HashMap<>();

        map.put("ሀ", "H");
        map.put("ሁ", "Hu");
        map.put("ሂ", "Hi");
        map.put("ሃ", "Ha");
        map.put("ሄ", "He");
        map.put("ህ", "Hə");
        map.put("ሆ", "Ho");

        map.put("ለ", "L");
        map.put("ሉ", "Lu");
        map.put("ሊ", "Li");
        map.put("ላ", "La");
        map.put("ሌ", "Le");
        map.put("ል", "Lə");
        map.put("ሎ", "Lo");

        map.put("መ", "M");
        map.put("ሙ", "Mu");
        map.put("ሚ", "Mi");
        map.put("ማ", "Ma");
        map.put("ሜ", "Me");
        map.put("ም", "Mə");
        map.put("ሞ", "Mo");

        map.put("ሠ", "S’");
        map.put("ሡ", "S’u");
        map.put("ሢ", "S’i");
        map.put("ሣ", "S’a");
        map.put("ሤ", "S’e");
        map.put("ሥ", "S’ə");
        map.put("ሦ", "S’o");

        map.put("ረ", "R");
        map.put("ሩ", "Ru");
        map.put("ሪ", "Ri");
        map.put("ራ", "Ra");
        map.put("ሬ", "Re");
        map.put("ር", "Rə");
        map.put("ሮ", "Ro");

        map.put("ሰ", "S");
        map.put("ሱ", "Su");
        map.put("ሲ", "Si");
        map.put("ሳ", "Sa");
        map.put("ሴ", "Se");
        map.put("ስ", "Sə");
        map.put("ሶ", "So");

        map.put("ቀ", "Q");
        map.put("ቁ", "Qu");
        map.put("ቂ", "Qi");
        map.put("ቃ", "Qa");
        map.put("ቄ", "Qe");
        map.put("ቅ", "Qə");
        map.put("ቆ", "Qo");

        map.put("በ", "B");
        map.put("ቡ", "Bu");
        map.put("ቢ", "Bi");
        map.put("ባ", "Ba");
        map.put("ቤ", "Be");
        map.put("ብ", "Bə");
        map.put("ቦ", "Bo");

        map.put("ተ", "T");
        map.put("ቱ", "Tu");
        map.put("ቲ", "Ti");
        map.put("ታ", "Ta");
        map.put("ቴ", "Te");
        map.put("ት", "Tə");
        map.put("ቶ", "To");

        map.put("ነ", "N");
        map.put("ኑ", "Nu");
        map.put("ኒ", "Ni");
        map.put("ና", "Na");
        map.put("ኔ", "Ne");
        map.put("ን", "Nə");
        map.put("ኖ", "No");

        map.put("አ", "A");
        map.put("ኡ", "U");
        map.put("ኢ", "I");
        map.put("ኣ", "A");
        map.put("ኤ", "E");
        map.put("እ", "Ə");
        map.put("ኦ", "O");

        map.put("ከ", "K");
        map.put("ኩ", "Ku");
        map.put("ኪ", "Ki");
        map.put("ካ", "Ka");
        map.put("ኬ", "Ke");
        map.put("ክ", "Kə");
        map.put("ኮ", "Ko");

        map.put("ወ", "W");
        map.put("ዉ", "Wu");
        map.put("ዊ", "Wi");
        map.put("ዋ", "Wa");
        map.put("ዌ", "We");
        map.put("ው", "Wə");
        map.put("ዎ", "Wo");

        map.put("ዘ", "Z");
        map.put("ዙ", "Zu");
        map.put("ዚ", "Zi");
        map.put("ዛ", "Za");
        map.put("ዜ", "Ze");
        map.put("ዝ", "Zə");
        map.put("ዞ", "Zo");

        map.put("ገ", "G");
        map.put("ጉ", "Gu");
        map.put("ጊ", "Gi");
        map.put("ጋ", "Ga");
        map.put("ጌ", "Ge");
        map.put("ግ", "Gə");
        map.put("ጎ", "Go");

        map.put("ጠ", "Ṭ");
        map.put("ጡ", "Ṭu");
        map.put("ጢ", "Ṭi");
        map.put("ጣ", "Ṭa");
        map.put("ጤ", "Ṭe");
        map.put("ጥ", "Ṭə");
        map.put("ጦ", "Ṭo");

        map.put("ፐ", "P");
        map.put("ፑ", "Pu");
        map.put("ፒ", "Pi");
        map.put("ፓ", "Pa");
        map.put("ፔ", "Pe");
        map.put("ፕ", "Pə");
        map.put("ፖ", "Po");



        return map;
    }

    @Override
    public int getCount() {
        return ethiopianAlphabet.length;
    }

    @Override
    public Object getItem(int position) {
        return ethiopianAlphabet[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.alphabet_cell, parent, false);
            holder = new ViewHolder();
            holder.letterView = convertView.findViewById(R.id.tv_letter);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String letter = ethiopianAlphabet[position];
        holder.letterView.setText(letter);

        // 🔵 Single Tap: Show English (e.g. H)
        holder.letterView.setOnClickListener(v -> {
            String english = amharicToEnglishMap.getOrDefault(letter, "?");
            showEnglishPopup(v, english);
        });

        // 🔴 Long Press: Play sound and show English
        holder.letterView.setOnLongClickListener(v -> {
            int resId = getSoundResourceId(letter);
            if (resId != 0) {
                MediaPlayer mediaPlayer = MediaPlayer.create(context, resId);
                mediaPlayer.setOnCompletionListener(MediaPlayer::release);
                mediaPlayer.start();
            } else {
                Toast.makeText(context, "No sound for " + letter, Toast.LENGTH_SHORT).show();
            }

            String english = amharicToEnglishMap.getOrDefault(letter, "?");
            showEnglishPopup(v, english);
            return true;
        });

        return convertView;
    }

    private void showEnglishPopup(View anchorView, String englishLetter) {
        View popupView = inflater.inflate(R.layout.popup_letter, null);
        TextView tvPopup = popupView.findViewById(R.id.tv_popup_letter);
        tvPopup.setText(englishLetter);

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

    private int getSoundResourceId(String letter) {
        switch (letter) {
            case "ሀ": return R.raw.ha;
            case "ሁ": return R.raw.hu;
            case "ሂ": return R.raw.hi;
            case "ሃ": return R.raw.ha;
            case "ሄ": return R.raw.he;
            case "ህ": return R.raw.h;
            case "ሆ": return R.raw.ho;
            // Add more as needed
            default: return 0;
        }
    }

    private static class ViewHolder {
        TextView letterView;
    }
}
