import java.util.HashMap;

public class L3 {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = init();
        System.out.println(hm);
    }








     static HashMap<String, Integer> init(){
        HashMap<String, Integer> hm = new HashMap<>();

        hm.put("Hello", 0);
        hm.put("Bye", 0);
        hm.put("Cat", 0);
        hm.put("Stone", 0);
        hm.put("Lego", 0);
        hm.put("Ball", 0);
        hm.put("Fish", 0);
        hm.put("Water", 0);
        hm.put("Cake", 0);
        hm.put("Finger", 0);
        return hm;
    }
}
