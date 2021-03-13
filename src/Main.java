import java.io.*;
import java.util.Arrays;

public class Main {

    public static int[] array;
    public static int[] pSolutions = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String[] data = line.split(" ");
        array = new int[size];
        for (int i = 0; i < data.length; i++) {
            array[i] = Integer.parseInt(data[i]);
        }
        int m = Integer.parseInt(br.readLine());
        Arrays.sort(array);
        findSolutions(m);
        bw.write("Peter should buy books whose prices are "+ array[pSolutions[0]] + " and "+ array[pSolutions[1]]+".\n");
        br.close();
        bw.close();
    }

    public static int binarySearch(int x, int takenPos) {
        int pos = -1;
        int s = 0;
        int e = array.length - 1;
        while (s <= e && pos < 0) {
            int mp = (s + e) / 2;
            if (array[mp] == x && mp != takenPos) {
                pos = mp;
            } else if (array[mp] < x) {
                s = mp + 1;
            } else if (array[mp] == x && takenPos == mp) {
                try {
                    if (array[mp + 1] == x) {
                        pos = mp + 1;
                    } else if (array[mp - 1] == x) {
                        pos = mp - 1;
                    }
                } catch (Exception ignore) {
                }
            } else {
                e = mp - 1;
            }
        }
        return pos;
    }

    public static void findSolutions(int m) {
        int i = m / 2;
        int j = m / 2;
        if (m % 2 != 0) {
            i = (m / 2) - 1;
        }
        pSolutions[0] = -1;
        pSolutions[1] = -1;
        while (pSolutions[0] == -1 && pSolutions[1] == -1) {
            pSolutions[0] = binarySearch(i, pSolutions[1]);
            pSolutions[1] = binarySearch(j, pSolutions[0]);
            j++;
            i--;
        }
    }
}
