import java.util.Arrays;
import java.util.stream.IntStream;

class Test {
    public static void main(String[] args) {
        swapArray();
        diagonalMatrixCheck(null);
    }

    static void swapArray() {
        int[] input = { 1, 4, 3, 0, 2 };
        // arr[0] == input[0]
        // arr[1] == last
        // arr[2] == last - 1
        // arr[3] == 2
        // arr[4] == last - 2

        int[] arr = { 1, 4, 6, 5, 23, 3, 2 };

        IntStream.range(0, arr.length / 2).forEach(i -> {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        });

        System.out.println(Arrays.toString(arr));
    }

    static void diagonalMatrixCheck(int[][] input) {
        int[][] array = {
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 1, 0, 1, 0, 1, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0 },
                { 0, 0, 1, 1, 0, 1, 1, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 1, 1, 1, 1, 0 }
        };

        int result = 0;
        int size = array.length;
        if (IntStream.range(0, size).map(n -> array[n][size - n - 1]).allMatch(n -> n == 1)) {
            result++;
        }
        System.out.println(result);
    }
}