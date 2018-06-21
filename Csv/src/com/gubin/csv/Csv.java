package com.gubin.csv;

import java.io.*;
import java.util.Scanner;

public class Csv {

    public static void main(String[] args) {
        if (args.length == 2) {
            convertCsvToHtml(args[0], args[1]);
        } else {
            System.out.println("необходимо ввести 2 значения, имя исходного файла и имя файла для вывода");
        }
    }

    private static void convertCsvToHtml(String csvFilePath, String htmlFilePath) {
        StringBuilder htmlText = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(csvFilePath), "UTF-8");
             PrintWriter writer = new PrintWriter(htmlFilePath)) {

            if (scanner.hasNext()) {

                htmlText.append("<!DOCTYPE html>").append(System.lineSeparator());
                htmlText.append("<html>").append(System.lineSeparator());
                htmlText.append("<head><title>Academ IT School</title>").append(System.lineSeparator());
                htmlText.append("<style type=\"text/css\"> table { border-collapse: collapse; border: 1px solid black; }   td, th { border: 1px solid black; } </style>").append(System.lineSeparator());
                htmlText.append("</head>").append(System.lineSeparator());
                htmlText.append("<body>").append(System.lineSeparator());
                htmlText.append("<table>").append(System.lineSeparator());

                while (scanner.hasNext()) {
                    boolean isInQuotes = false;
                    htmlText.append("   <tr>");
                    String line = scanner.nextLine();

                    int i = 0;
                    char currentChar = line.charAt(i);
                    StringBuilder cell = new StringBuilder();

                    while (true) {
                        if (cell.length() == 0) {
                            cell.append("<td>");
                        }
                        if (i < line.length()) {
                            currentChar = line.charAt(i);
                        }

                        if ((currentChar != '\"') && !isInQuotes) {
                            if ((currentChar == ',') || (i > line.length() - 1)) {
                                cell.append("</td>");
                                htmlText.append(cell);
                                cell.delete(0, cell.length());
                                i++;
                                if (i >= line.length()) {
                                    if (line.charAt(i - 1) == ',') {
                                        htmlText.append("<td></td>");
                                    }
                                    break;
                                }
                            } else {
                                cell.append(changeSpecialCharacters(currentChar));
                                i++;
                            }
                        } else if ((currentChar == '\"') && !isInQuotes) {
                            i++;
                            isInQuotes = true;
                        } else {                                                   //если мы в ячейке с ковычками
                            if (currentChar == '\"') {
                                if ((i < line.length() - 1) && (line.charAt(i + 1) == '\"')) {        // если двойные ковычки
                                    cell.append("&quot;");
                                    i += 2;
                                    if (i > line.length() - 1) {     //если там дальше идет перевод строки
                                        line = scanner.nextLine();
                                        i = 0;
                                        cell.append("<br/>");
                                    }
                                } else {                         // если одинарная ковычка, закрывает ячейку
                                    cell.append("</td>");
                                    htmlText.append(cell);
                                    cell.delete(0, cell.length());
                                    isInQuotes = false;
                                    i += 2;
                                    if (i > line.length() - 1) { // если это был конец строки
                                        break;
                                    }
                                }
                            } else {
                                cell.append(changeSpecialCharacters(currentChar));
                                i++;
                                if (i > line.length() - 1) {     //если там дальше идет перевод строки
                                    line = scanner.nextLine();
                                    i = 0;
                                    cell.append("<br/>");
                                }
                            }
                        }
                    }

                    htmlText.append("</tr>").append(System.lineSeparator());
                }
                htmlText.append("</table>").append(System.lineSeparator());
                htmlText.append("</body></html>");
                writer.println(htmlText.toString());
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
    }

    private static String changeSpecialCharacters(char inputChar) {
        String result;
        switch (inputChar) {
            case '&':
                result = "&amp;";
                break;
            case '\'':
                result = "&apos;";
                break;
            case '<':
                result = "&lt;";
                break;
            case '>':
                result = "&gt;";
                break;
            default:
                result = Character.toString(inputChar);
        }
        return result;
    }

}