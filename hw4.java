/* Реализовать консольное приложение, которое:
Принимает от пользователя строку вида
text~num
Нужно рассплитить строку по ~, сохранить text в связный список на позицию num.
Если введено print~num, выводит строку из позиции num в связном списке и удаляет её из списка.

     // text~num (например, word~4)
// print~num (распечатать текст)
// word~1
// foo~5
// qwerty~10
// bar~5
// print~10 -> qwerty
// print~1 -> word
// print~2 -> пустая строка или исключение NoSuchElementException
// print~5 -> bar
*/

import java.util.LinkedList;
import java.util.Scanner;

public class hw4 {
    public static void main(String[] params) {
        LinkedList<String> list = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("[text~num]-->");
            String input = scanner.nextLine();
            if (input.equals("-1")) {
                break;
            }

            int idx = input.lastIndexOf("~");
            if (input.isEmpty() || idx == -1) {
                throw new RuntimeException("Ошибка: ввод не содержит символа ~");
            }

            String text = input.substring(0, idx);
            String numText = input.substring(idx+1);
            int num;

            if (numText.isEmpty()) {
                System.out.println("Не введён номер позиции");
                continue;
            }

            try {
                num = Integer.parseInt(numText);
            } catch (NumberFormatException e) {
                System.out.println("Не определён номер позиции");
                continue;
            }

            if (num < 0) {
                System.out.println("Номер позиции < 0");
                continue;
            }

            if (text.equals("print")) {
                if (num < list.size()) {
                    String textForPrint = list.get(num);
                    System.out.println("=>" + (textForPrint == null ? "\"\"" : textForPrint));
                    list.set(num, null);
                } else {
                    System.out.println("=>" + "\"\"");
                }
            } else {
                for (int i = 0; i <= num; i++) {
                    if (i >= list.size()) {
                        list.addLast(null);
                    }
                }

                list.set(num, text);
            }
            scanner.close();
        }

    }
}
