package cn.followtry.incubate;

/**
 * 矩阵旋转问题
 * Created by followtry on 2017/7/18.
 */
public class MatrixRotation {
    static int[][] a={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    static int[][] b = new int[4][4];
    static int[][] c = new int[4][4];
    static int[][] d = new int[4][4];
    public static void main(String[] args) {
        showArr(a,"原数组矩阵：");
        for (int i=0;i< a.length ;i++) {
            for (int j=0;j<a[i].length;j++) {
                //顺时针翻转90度
                b[i][a.length-1-j]=a[j][i];
                //逆时针旋转90度
                c[a.length-1-j][i]=a[i][j];
                //旋转180度
                d[a.length-1-i][a.length-1-j]=a[i][j];
            }
        }
        showArr(d,"180度旋转矩阵：");
        showArr(b,"顺时针旋转矩阵：");
        showArr(c,"逆时针旋转矩阵：");
    }
    public static void showArr(int[][] a,String msg) {
        System.out.println(msg+"\n数组长度："+a.length);
        for (int i=0;i< a.length ;i++) {
            for (int j=0;j<a[i].length;j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
}
