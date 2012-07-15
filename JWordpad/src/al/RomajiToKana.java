package al;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alaix
 */
public class RomajiToKana
{
    //Todas las consonantes pueden duplicarse menos las suaves g, d, j, z ademas de la r y la y.
    //ー、。
    
    public StringBuffer result;
    private static String tabla[][] = 
    {
        //{"", ""},
        {"a", "あ", "ア"},
        {"i", "い", "イ"},
        {"u", "う", "ウ"},
        {"e", "え", "エ"},
        {"o", "お", "オ"},
        {"ka", "か", "カ"},
        {"ki", "き", "キ"},
        {"ku", "く", "ク"},
        {"ke", "け", "ケ"},
        {"ko", "こ", "コ"},
        {"ga", "が", "ガ"},
        {"gi", "ぎ", "ギ"},
        {"gu", "ぐ", "グ"},
        {"ge", "げ", "ゲ"},
        {"go", "ご", "ゴ"},
        {"sa", "さ", "サ"},
        {"shi", "し", "シ"},
        {"su", "す", "ス"},
        {"se", "せ", "セ"},
        {"so", "そ", "ソ"},
        {"za", "ざ", "ザ"},
        {"ji", "じ", "ジ"},
        {"zu", "ず", "ズ"},
        {"ze", "ぜ", "ゼ"},
        {"zo", "ぞ", "ゾ"},
        {"ta", "た", "タ"},
        {"chi", "ち", "チ"},
        {"tsu", "つ", "ツ"},
        {"te", "て", "テ"},
        {"to", "と", "ト"},
        {"da", "だ", "ダ"},
        {"dzi", "ぢ", "ヂ"},
        {"dzu", "づ", "ヅ"},
        {"de", "で", "デ"},
        {"do", "ど", "ド"},
        {"na", "な", "ナ"},
        {"ni", "に", "ニ"},
        {"nu", "ぬ", "ヌ"},
        {"ne", "ね", "ネ"},
        {"no", "の", "ノ"},
        {"ha", "は", "ハ"},
        {"hi", "ひ", "ヒ"},
        {"fu", "ふ", "フ"},
        {"he", "へ", "ヘ"},
        {"ho", "ほ", "ホ"},
        {"ba", "ば", "バ"},
        {"bi", "び", "ビ"},
        {"bu", "ぶ", "ブ"},
        {"be", "べ", "ベ"},
        {"bo", "ぼ", "ボ"},
        {"pa", "ぱ", "パ"},
        {"pi", "ぴ", "ピ"},
        {"pu", "ぷ", "プ"},
        {"pe", "ぺ", "ペ"},
        {"po", "ぽ", "ポ"},
        {"ma", "ま", "マ"},
        {"mi", "み", "ミ"},
        {"mu", "む", "ム"},
        {"me", "め", "メ"},
        {"mo", "も", "モ"},
        {"ya", "や", "ヤ"},
        {"yu", "ゆ", "ユ"},
        {"yo", "よ", "ヨ"},
        {"ra", "ら", "ラ"},
        {"ri", "り", "リ"},
        {"ru", "る", "ル"},
        {"re", "れ", "レ"},
        {"ro", "ろ", "ロ"},
        {"wa", "わ", "ワ"},
        {"wo", "を", "ヲ"},
        {"n", "ん", "ン"},
        {"kya", "きゃ", "キャ"},
        {"kyu", "きゅ", "キュ"},
        {"kyo", "きょ", "キョ"},
        {"gya", "ぎゃ", "ギャ"},
        {"gyu", "ぎゅ", "ギュ"},
        {"gyo", "ぎょ", "ギョ"},
        {"sha", "しゃ", "シャ"},
        {"shu", "しゅ", "シュ"},
        {"sho", "しょ", "ショ"},
        {"ja", "じゃ", "ジャ"},
        {"ju", "じゅ", "ジュ"},
        {"jo", "じょ", "ジョ"},
        {"cha", "ちゃ", "チャ"},
        {"chu", "ちゅ", "チュ"},
        {"cho", "ちょ", "チョ"},
        {"hya", "ひゃ", "ヒャ"},
        {"hyu", "ひゅ", "ヒュ"},
        {"hyo", "ひょ", "ヒョ"},
        {"bya", "びゃ", "ビャ"},
        {"byu", "びゅ", "ビュ"},
        {"byo", "びょ", "ビョ"},
        {"pya", "ぴゃ", "ピャ"},
        {"pyu", "ぴゅ", "ピュ"},
        {"pyo", "ぴょ", "ピョ"},
        {"nya", "にゃ", "ニャ"},
        {"nyu", "にゅ", "ニュ"},
        {"nyo", "にょ", "ニョ"},
        {"mya", "みゃ", "ミャ"},
        {"myu", "みゅ", "ミュ"},
        {"myo", "みょ", "ミョ"},
        {"rya", "りゃ", "リャ"},
        {"ryu", "りゅ", "リュ"},
        {"ryo", "りょ", "リョ"},
        {"wi", "ゐ", "ヰ"},
        {"we", "ゑ", "ヱ"},
        //she, je, tsa, xa, xka, xke, di, zi, fa, kye, nye, rye
        {"-", "ー", "ー"},
        /*
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        */
    };

    public RomajiToKana()
    {
        result = new StringBuffer();
    }
    
    public int Convert(String s, boolean HiraganaKatakana)
    {
        int kana;
        if(HiraganaKatakana)
            kana = 2;
        else
            kana = 1;
        
        result.setLength(0);
        for (int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            int v=0;
            //Vocales
            for(; v<5; v++)
                if(tabla[v][0].charAt(0) == c)
                {
                    result.append(tabla[v][kana]);
                    break;
                }
            if(v != 5)
                continue;
            //Consonantes
            if(i+1 < s.length())
                if(c == s.charAt(i+1))
                {
                    if(HiraganaKatakana)
                        result.append('ッ');
                    else
                        result.append('っ');
                    i++;
                }
            for(; v<tabla.length; v++)
                if(tabla[v][0].charAt(0) == c)
                {
                    StringBuffer sb = new StringBuffer();
                    sb.append(c);
                    i++;
                    for(; i<s.length(); i++)
                    {
                        sb.append(s.charAt(i));
                        int cv = v;
                        for(; cv<tabla.length; cv++)
                            if(tabla[cv][0].startsWith(sb.toString()))
                                break;
                        if(cv == tabla.length)
                        {
                            sb.deleteCharAt(sb.length()-1);
                            i--;
                            break;
                        }
                    }
                    for(; v<tabla.length; v++)
                        if(tabla[v][0].equals(sb.toString()))
                        {
                            result.append(tabla[v][kana]);
                            break;
                        }
                    if(v == tabla.length && i == s.length())
                        return 1;
                    break;
                }
            if(v==tabla.length)
                return -1;
        }
        return 0;
    }
}
