/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TOOL;

import EXC.BooleanFormatException;
import EXC.StringFormatException;
import java.util.Scanner;

public class Inputter {

    static Scanner sc = new Scanner(System.in);

    public static int getInteger(String msg, String errMsg) {
        int value;
        while (true) {
            try {
                System.out.println(msg);
                value = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(errMsg);
            }
        }
        return value;
    }

    public static int getInteger(String msg, String errMsg1, String errMsg2, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.println(msg);
                value = Integer.parseInt(sc.nextLine());
                if (value > max || value < min) {
                    throw new Exception();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(errMsg1);
            } catch (Exception e) {
                System.out.println(errMsg2);
            }
        }
        return value;
    }

    public static int getInteger(String msg, String errMsg, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.println(msg);
                value = Integer.parseInt(sc.nextLine());
                if (value > max || value < min) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
        return value;
    }

    public static double getDouble(String msg, String errMsg) {
        double value;
        while (true) {
            try {
                System.out.println(msg);
                value = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(errMsg);
            }
        }
        return value;
    }

    public static double getDouble(String msg, String errMsg1, String errMsg2, double min, double max) {
        double value;
        while (true) {
            try {
                System.out.println(msg);
                value = Double.parseDouble(sc.nextLine());
                if (value > max || value < min) {
                    throw new Exception();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(errMsg1);
            } catch (Exception e) {
                System.out.println(errMsg2);
            }
        }
        return value;
    }

    public static double getDouble(String msg, String errMsg, double min, double max) {
        double value;
        while (true) {
            try {
                System.out.println(msg);
                value = Double.parseDouble(sc.nextLine());
                if (value > max || value < min) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
        return value;
    }

    public static String getString(String msg, String errMsg) {
        String value;
        while (true) {
            try {
                System.out.println(msg);
                value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
        return value.trim();
    }

    public static String getString(String msg, String errMsg1, String errMsg2, String regex) {
        String value = "";
        while (true) {
            try {
                System.out.println(msg);
                value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception();
                }
                if (!value.trim().matches(regex)) {
                    throw new StringFormatException();
                }
                break;
            } catch (StringFormatException e) {
                System.out.println(errMsg1);
            } catch (Exception e) {
                System.out.println(errMsg2);
            }
        }
        return value.trim();
    }

    public static String getString(String msg, String errMsg, String regex) {
        String value = "";
        while (true) {
            try {
                System.out.println(msg);
                value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception();
                }
                if (!value.trim().matches(regex)) {
                    throw new StringFormatException();
                }
                break;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
        return value.trim();
    }

    public static boolean getBool(String msg, String errMsg) {
        String value = "";
        while (true) {
            try {
                System.out.println(msg);
                value = sc.nextLine();
                if (Inputter.isBool(value)) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
        return Inputter.getBoolVal(value);
    }

    public static boolean isBool(String input) {
        return "no".equals(input) || "n".equals(input) || "y".equals(input) || "yes".equals(input) || "0".equals(input) || "1".equals(input) || input.toLowerCase().equals("true") || input.toLowerCase().equals("false") || input.toLowerCase().equals("t") || input.toLowerCase().equals("f");
    }

    public static boolean getBoolVal(String input) {
        return !("0".equals(input) || input.toLowerCase().equals("false") || input.toLowerCase().equals("f") || input.toLowerCase().equals("n")) || input.toLowerCase().equals("no");
    }

    public static boolean isBoolean(String input) throws Exception {
        switch (input.toLowerCase()) {
            case "true":
            case "1":
            case "yes":
            case "y":
            case "t":
                return true;
            case "false":
            case "0":
            case "no":
            case "n":
            case "f":
                return false;
            default:
                throw new BooleanFormatException();
        }
    }

    public static boolean getBoolean(String msg, String errMsg) {
        boolean value;
        while (true) {
            try {
                System.out.println(msg);
                value = isBoolean(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
        return value;
    }

    public static boolean getBoolean(String msg, String errMsg1, String errMsg2) {
        boolean value;
        while (true) {
            try {
                System.out.println(msg);
                value = isBoolean(sc.nextLine());
                break;
            } catch (BooleanFormatException e) {
                System.out.println(errMsg1);
            } catch (Exception e) {
                System.out.println(errMsg2);
            }
        }
        return value;
    }

    public static String getName(String msg, String errMsg, int minLength, int maxLength) {
        String value = null;
        while (true) {
            try {
                System.out.println(msg);
                value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception();
                }
                if (!value.trim().matches("[a-zA-Z0-9 .,()\\-']{" + minLength + "," + maxLength + "}")) {
                    throw new StringFormatException();
                }
                break;
            } catch (StringFormatException e) {
                System.out.println("The length of the string should be from " + minLength + " to " + maxLength);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return normalizeString(value);

    }

    public static void closeScanner() {
        sc.close();
    }

    public static String removeSpace(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str.toLowerCase();
    }

    public static String normalizeString(String str) {
        if (str.isEmpty()) {
            return "";
        }
        str = removeSpace(str);
        String temp[] = str.split(" ");
        str = ""; // 
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) //
            {
                str += " ";
            }
        }
        return str;
    }

    public static String getString(String msg) {
        System.out.println(msg);
        return normalizeString(sc.nextLine());
    }

    public static String getString(String msg, int min, int max) {
        System.out.println(msg);
        while (true) {
            String str = normalizeString(sc.nextLine());
            if (min == 0 && str.equals("")) {
                return "";
            }
            if (str.length() > max || str.length() < min) {
                System.out.println("The string length should be between " + min + " and " + max);
            } else {
                return str;
            }
        }
    }

    public static String getString(String msg, int min, int max, String regex) {
        System.out.println(msg);
        while (true) {
            String str = normalizeString(sc.nextLine());
            if (str.equals("")) {
                return "";
            }
            if (str.length() > max || str.length() < min) {
                System.out.println("The string length should be between " + min + " and " + max);
            } else if (!str.matches(regex)) {
                System.out.println("Invalid value!");
            } else {
                return str;
            }
        }
    }
}
