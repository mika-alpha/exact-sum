import java.io.*;
import java.util.Arrays;

public class Main {

    public static int[] array;
    public static int[] pSolutions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        pSolutions = new int[2];
        String line = br.readLine();
        while(line!=null) {
            int size = Integer.parseInt(line);
            String[] data = br.readLine().split(" ");
            array = new int[size];
            for (int i = 0; i < data.length; i++) {
                array[i] = Integer.parseInt(data[i]);
            }
            int m = Integer.parseInt(br.readLine());
            Arrays.sort(array);
            findSolutions(m);
            bw.write("Peter should buy books whose prices are " + array[pSolutions[0]] + " and " + array[pSolutions[1]] + ".\n\n");
            br.readLine();
            line = br.readLine();
        }
        br.close();
        bw.close();
    }

    public static int binarySearch(int x) {
        int pos = -1;
        int s = 0;
        int e = array.length - 1;
        while (s <= e && pos < 0) {
            int mp = (s + e) / 2;
            if (array[mp] == x) {
                pos = mp;
            } else if (array[mp] < x) {
                s = mp + 1;
            } else if (array[mp] > x){
                e = mp - 1;
            }
        }
        return pos;
    }

    public static void findSolutions(int m) {
        pSolutions[0] = -1;
        pSolutions[1] = -1;
        int i = m / 2;
        int j = (m+1) / 2;
        if (m % 2 == 0) {
            pSolutions[0] = binarySearch(i);
            if (pSolutions[0] != -1){
                if ((pSolutions[0]+1) < array.length){
                    if (array[pSolutions[0]+1] == i){
                        pSolutions[1] = pSolutions[0]+1;
                    }
                } else if ((pSolutions[0]-1) >= 0){
                    if (array[pSolutions[0]-1] == i){
                        pSolutions[1] = pSolutions[0]-1;
                    }
                }
            }
            i--;
            j++;
        }
        while (pSolutions[0] < 0 || pSolutions[1] < 0) {
            pSolutions[0] = binarySearch(i);
            pSolutions[1] = binarySearch(j);
            i--;
            j++;
        }
    }
}