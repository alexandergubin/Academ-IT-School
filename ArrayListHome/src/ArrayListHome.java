import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        String filePath = ".\\ArrayListHome\\src\\input.txt";
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> arrayList2 = new ArrayList<>(Arrays.asList(1, 2, 1, 5, 6, 5, 3, 2, 6, 1, 5, 3, 2, 6, 5, 1, 3, 2, 6, 5));

        System.out.println("ArrayList из файла");
        System.out.println(readFileToArrayList(filePath).toString());

        System.out.println("Удалить все четные");
        System.out.println(deleteEvenNumbers(arrayList).toString());

        System.out.println("Удалить все повторы");
        System.out.println(deleteRepeats(arrayList2).toString());
    }

    private static ArrayList<String> readFileToArrayList(String filePath) {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(filePath), "UTF-8")) {
            while (scanner.hasNext()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static ArrayList<Integer> deleteEvenNumbers(ArrayList<Integer> arrayList) {
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
            }
        }
        return arrayList;
    }

    private static ArrayList<Integer> deleteRepeats(ArrayList<Integer> arrayList) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer e : arrayList) {
            if (!result.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
