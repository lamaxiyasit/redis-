package com.example.redis_lua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * @Description TODO
 * @Auther chengjiahui
 * @Date 2024/2/26 09:56
 * @Version 1.0
 */
public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int y = 0, o = 0, u = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'y') {
                y++;
            }
            if (input.charAt(i) == 'o') {
                o++;
            }
            if (input.charAt(i) == 'u') {
                u++;
            }
        }
        int min = y;
        min = min > o ? o : min;
        min = min > u ? u : min;
        String temp = "";
        for (int i = 0; i < u - min; i++) {
            temp += "u";
        }
        for (int i = 0; i < min; i++) {
            temp += "you";
        }
        for (int i = 0; i < y - min; i++) {
            temp += "y";
        }
        for (int i = 0; i < o - min; i++) {
            temp += "o";
        }
        System.out.println(temp);
    }
}


class YouRearrange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        str = str.substring(1, str.length() - 1);
        String[] strings = str.split(",");
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            List<Integer> temp = fun(strings[i]);
            for (int j = 0; j < temp.size(); j++) {
                t.add(temp.get(j));
            }
        }
        String ans = "[";
        int temp = t.get(0);
        int count = 1;
        for (int i = 1; i < t.size(); i++) {
            if (temp == t.get(i)) {
                count++;
            } else {
                ans = ans + String.valueOf(temp) + "(" + String.valueOf(count) + "),";
                count = 1;
                temp = t.get(i);
            }
        }
        ans = ans + String.valueOf(temp) + "(" + String.valueOf(count) + ")]";
        System.out.println(ans);
    }

    public static List<Integer> fun(String str) {
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                index = i;
                break;
            }
        }
        String temp = str.substring(0, index);
        int num = Integer.parseInt(temp);
        int count = Integer.parseInt(str.substring(index + 1, str.length() - 1));
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ans.add(num);
        }
        return ans;
    }

    public static String rearrangeString(String input) {
        int countY = 0, countO = 0, countU = 0;

        // 统计'y', 'o', 'u'的数量
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == 'y') countY++;
            else if (ch == 'o') countO++;
            else if (ch == 'u') countU++;
        }

        // 计算最多可以组成的"you"数量
        int youCount = Math.min(countY, Math.min(countO / 2, countU));

        // 构建包含尽可能多"you"的字符串
        StringBuilder rearranged = new StringBuilder();

        // 添加剩余的'u'（如果有）
        // 由于题目要求输出形式为"uyouyouu"，我们可以先添加一个'u'（如果有剩余）
        if (countU > youCount) {
            rearranged.append("u");
            countU--;
        }

        for (int i = 0; i < youCount; i++) {
            rearranged.append("you");
        }

        // 添加剩余的'y', 'o', 'u'
        countY -= youCount;
        countO -= youCount * 2;

        for (int i = 0; i < countY; i++) rearranged.append("y");
        for (int i = 0; i < countO; i++) rearranged.append("o");
        for (int i = 0; i < countU; i++) rearranged.append("u");

        return rearranged.toString();
    }
}

class MaxYouSubstring {
    public static void main(String[] args) {
        String input = "yyoouuuu"; // 用实际的字符串替换
        int yCount = 0, oCount = 0, uCount = 0;

        for (char c : input.toCharArray()) {
            if (c == 'y') {
                yCount++;
            } else if (c == 'o') {
                oCount++;
            } else if (c == 'u') {
                uCount++;
            }
        }

        int youCount = Math.min(yCount, Math.min(oCount / 2, uCount));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < youCount; i++) {
            sb.append("you");
        }

        System.out.println(sb.toString());
    }
}

class MinimizeOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }


    }
}

class MinimumOperationsToMedian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] arr = new long[n];
        long[][] arrs = new long[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
            for (int j = 0; j < n; j++) {
                arrs[i][j] = arr[i];
            }
        }
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(arrs[i][j] - arr[j]);
            }
            System.out.println(sum);
        }

    }
}

class Main {
    static int[] values;
    static List<Integer>[] adj;
    static boolean allEvenOrOdd = true; // 标记所有节点初始是否都是奇数或偶数
    static List<String> operations;
    static int opsCount = 0;

    public static void dfs(int node, int parent) {
        for (int child : adj[node]) {
            if (child != parent) {
                dfs(child, node);
                if (values[node] % 2 != values[child] % 2) {
                    // 如果当前节点与子节点奇偶性不同，进行操作
                    values[node]++;
                    values[child]++;
                    opsCount++;
                    operations.add((node + 1) + " " + (child + 1)); // 输出时索引加1
                }
            }
        }
    }

    public static boolean canMakeEqual(int n) {
        // 检查初始奇偶性是否一致
        int firstParity = values[0] % 2;
        for (int i = 1; i < n; i++) {
            if (values[i] % 2 != firstParity) {
                allEvenOrOdd = false;
                break;
            }
        }
        return allEvenOrOdd;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt(); // 查询次数

        while (q-- > 0) {
            int n = scanner.nextInt(); // 节点数量
            values = new int[n];
            adj = new List[n];
            operations = new ArrayList<>();
            opsCount = 0;
            allEvenOrOdd = true;

            for (int i = 0; i < n; i++) {
                values[i] = scanner.nextInt(); // 节点权值
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                adj[u].add(v);
                adj[v].add(u);
            }

            if (!canMakeEqual(n)) {
                System.out.println("No");
                continue;
            }

            dfs(0, -1); // 从节点0开始DFS，假设节点0是根节点

            if (opsCount <= n) {
                System.out.println("Yes");
                System.out.println(operations.size());
                for (String op : operations) {
                    System.out.println(op);
                }
            } else {
                System.out.println("No");
            }
        }
        scanner.close();
    }
}


class DoubleArrayExceptOne {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 数组的大小
        int q = scanner.nextInt(); // 操作次数
        int[] arr = new int[n]; // 存储数组元素

        // 读取数组元素
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // 由于每次操作的元素不确定，我们可以用一个数组来记录每个元素被跳过的次数
        int[] skipCounts = new int[n];

        // 读取操作中未被翻倍的元素索引，并更新skipCounts数组
        for (int i = 0; i < q; i++) {
            int skipIndex = scanner.nextInt() - 1; // 数组索引从0开始
            skipCounts[skipIndex]++;
        }

        long sumAfterOperations = 0;
        for (int i = 0; i < n; i++) {
            long value = (long) arr[i] * powMod(2, q - skipCounts[i], MOD) % MOD;
            sumAfterOperations = (sumAfterOperations + value) % MOD;
        }

        System.out.println(sumAfterOperations);
        scanner.close();
    }

    // 计算base的exp次方对MOD取模的结果
    private static long powMod(int base, int exp, int mod) {
        long result = 1;
        long b = base % mod;

        while (exp > 0) {
            if ((exp & 1) != 0) {
                result = (result * b) % mod;
            }
            b = (b * b) % mod;
            exp >>= 1;
        }

        return result;
    }
}


class EmployeeNumberValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String employeeNumber = scanner.nextLine();
            System.out.println(isValidEmployeeNumber(employeeNumber) ? "yes" : "no");
        }

        scanner.close();
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    private static boolean isValidDate(int year, int month, int day) {
        if (month < 1 || month > 12 || day < 1) {
            return false;
        }

        if (month == 2) {
            return day <= (isLeapYear(year) ? 29 : 28);
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        } else {
            return day <= 31;
        }
    }

    private static boolean isValidEmployeeNumber(String employeeNumber) {
        if (employeeNumber.length() != 10 || !employeeNumber.matches("\\d+")) {
            return false;
        }

        int year = Integer.parseInt(employeeNumber.substring(0, 2));
        year += (year < 70) ? 2000 : 1900;
        int month = Integer.parseInt(employeeNumber.substring(2, 4));
        int day = Integer.parseInt(employeeNumber.substring(4, 6));
        int uniqueNumber = Integer.parseInt(employeeNumber.substring(6));

        if (year < 1970 || year > 2023) {
            return false;
        }

        if (!isValidDate(year, month, day)) {
            return false;
        }

        long fullNumber = Long.parseLong(employeeNumber);
        return fullNumber % 13 == 0;
    }
}


class FenwickTree {
    private int[] tree;
    private int n;

    public FenwickTree(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public void update(int i, int delta) {
        while (i <= n) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & -i;
        }
        return sum;
    }
}

class InversionCountAfterFlipping {
    public static void main(String[] args) {
        // 输入处理省略，假设n为数组长度，arr为数组
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // 树状数组用于计算逆序对
        FenwickTree tree = new FenwickTree(n);

        // 计算初始逆序对数量
        int totalInversions = 0;
        for (int i = 0; i < n; i++) {
            totalInversions += i - tree.query(arr[i]);
            tree.update(arr[i], 1);
        }

        // 对于每个元素i，计算取反后的逆序对数量
        // 这需要具体理解题意和确定如何高效计算每次变化
        // 直接计算可能导致时间复杂度过高
        // 需要寻找计算逆序对变化的高效方法
        System.out.println("Initial inversions: " + totalInversions);
    }
}

