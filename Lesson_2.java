import java.util.Random;

public class Lesson_2 {
    public static int SIZE = 4;
    public static void main(String[] args) {
        int result = 0;

        Random rnd = new Random();
        String[][] arr = new String[SIZE][SIZE];

        for (int i = 0; i <SIZE ; i++) {
            for (int j = 0; j <SIZE ; j++) {
                arr[i][j]= String.valueOf(rnd.nextInt(30));
            }
        }
        try {
            result = Counter(arr);
        } catch (MyArraySizeExeption e){
            e.printStackTrace();
        }  catch (MyArrayDataExeption e){
            e.printStackTrace();
        }
        System.out.println(result);
    }



    public static int Counter(String[][] a){
        int result = 0;
        if (a.length!=SIZE) throw new MyArraySizeExeption();
        for (int i = 0; i < a.length; i++) {
            if (a.length!=SIZE) throw new MyArraySizeExeption();
            for (int j = 0; j < a[0].length ; j++) {
                try {
                    result += Integer.parseInt(a[i][j]);
                }catch (NumberFormatException e){
                    throw new MyArrayDataExeption(i,j);
                }

            }
        }
        return result; }
}


class MyArrayDataExeption extends RuntimeException{
    int row;
    int colomn;
    public MyArrayDataExeption (int row,int colomn){
        super(row+" "+colomn);
        this.colomn=colomn;
        this.row = row;
    }
}

class MyArraySizeExeption extends RuntimeException{

    public MyArraySizeExeption (){
        super("wrong size!");
    }
}