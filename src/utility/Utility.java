package utility;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Utility {

    public static String formatIntMatrix (int mat [][]){
        AtomicInteger maxNumberOfDigits = new AtomicInteger();
        Arrays.stream(mat).forEach(row ->{
            Arrays.stream(row).forEach(n -> {
                int digits_of_n = (int) (Math.log10(n) + 1);
                if(digits_of_n>maxNumberOfDigits.get())
                    maxNumberOfDigits.set(digits_of_n);
            });
        });
        int n = mat[0].length;
        int m = mat.length;
        String matStr = "";
        int dn = maxNumberOfDigits.get();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                String formatString = "%" +dn +"d ";
                String newStr = String.format(formatString,mat[i][j]);
                matStr += newStr;
            }
            matStr += "\n";
        }
        return matStr;
    }


    public static String formatStringMatrix (String mat [][]){
        AtomicInteger maxNumberOfChars = new AtomicInteger();
        Arrays.stream(mat).forEach(row ->{
            Arrays.stream(row).forEach(str -> {
                if(str.length()>maxNumberOfChars.get())
                    maxNumberOfChars.set(str.length());
            });
        });
        int n = mat[0].length;
        int m = mat.length;
        String matStr = "";
        int cn = maxNumberOfChars.get() + 1 ;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                String formatString = "%" +cn +"s ";
                String newStr = String.format(formatString,mat[i][j]);
                matStr += newStr;
            }
            matStr += "\n";
        }
        return matStr;
    }


}
