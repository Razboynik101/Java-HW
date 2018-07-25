import java.util.HashMap;
import java.util.HashSet;

public class L3_2 {
    public static void main(String[] args) {

        Phones ph = new Phones();
        ph.add("Sergeev","111");
        ph.add("Sergeev","222");
        ph.add("Zarubin","333");
        ph.add("Sergeev","444");
        ph.add("Alexeev","555");
        ph.add("Alexeev","666");
        ph.findStr("Sergeev");
        ph.findStr("Alexeev");
        ph.findStr("Zarubin");
        ph.findStr("Burkin");

    }


    static class Phones{
        HashMap<String, HashSet<String>> hm;
        public Phones(){
            this.hm=new HashMap<>();
        }

        public void add(String name, String phone) {
            HashSet<String> hs = hm.getOrDefault(name, new HashSet<>());
            hs.add(phone);
            hm.put(name,hs);
        }
        public void findStr(String name) {
            if (hm.containsKey(name)){
                System.out.println(hm.get(name));
            } else {
                System.out.println("Wrong Name");
            }
        }
    }

















}
