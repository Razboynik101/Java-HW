public class Main {
    public static void main(String[] args) {
creator1();
creator2();
    }



   static public void creator1 (){
         final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        for (int i = 0; i < size ; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
       for (int i = 0; i < size ; i++) {

           arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

       }System.out.println(System.currentTimeMillis() - a);

    }





    static public void creator2 (){
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];


        for (int i = 0; i < size ; i++) {
            arr[i] = 1;

        }

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.out.println(System.currentTimeMillis() - a);

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.init(h,a1,arr);
        t2.init(h,a2,arr);

        a = System.currentTimeMillis();
        t1.run();
        System.out.println(System.currentTimeMillis() - a);

        a = System.currentTimeMillis();
        t2.run();
        System.out.println(System.currentTimeMillis() - a);

        a = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);

    }

}

class MyThread extends Thread{
    int h = 0;
    float[] a = new float[10000000/2];
    float[] arr = new float[10000000];
    public  void init (int h,float[] a1,float[] arr){
    this.a=a;
        this.arr=arr;
        this.h=h;
    }
@Override
    public void run(){
    for (int i = 0; i < h ; i++) {
        a[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}
        }
