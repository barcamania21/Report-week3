package FindMissingNumber;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class FindMissingNumber{

    public long countNumberInRange(long min, long max) throws FileNotFoundException{
        Scanner in = new Scanner(new FileReader("/Users/naduong1001/Desktop/a.txt"));
        long count=0;
        while (in.hasNext()){
            int num = in.nextInt();
            if(num >= min && num <= max){
                count++;
            }
        }
        return count;
    }

    public long findMisingNumber() throws FileNotFoundException {
        long min = Integer.MIN_VALUE;
        long max = Integer.MAX_VALUE;
        while (min < max ) {
            long mid = (max - min) / 2 + min;
            // nếu viết mid = (max + min)/2 có thể gây ra hiện tượng tràn số.
            long count = countNumberInRange(min, mid);

            if (count < mid - min + 1) {
                max = mid;
            } else {
                min = mid;
            }
        }
        return min;
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new FindMissingNumber().findMisingNumber());
    }
}
