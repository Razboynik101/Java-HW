import java.util.HashMap;
import java.util.HashSet;

public class L3 {
    public static void main(String[] args) {
        String[] st = {"Hello","Bye","Cat","Stone","Lego","Hello","Stone","Water","Stone","Finger"};
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < st.length ; i++) {
            hm.put(st[i], hm.getOrDefault(st[i],0)+1);
        }
        System.out.println(hm);

    }












}
