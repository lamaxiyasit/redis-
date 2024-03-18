package com.example.redis_lua;

/**
 * @Description TODO
 * @Auther chengjiahui
 * @Date 2024/2/29 15:13
 * @Version 1.0
 */
public class main {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuilder();

        int index = 0;
        int direction = 1;
        for (char c : s.toCharArray()) {
            sb[index].append(c);
            if (index == 0) {
                direction = 1;
            } else if (index == numRows - 1) {
                direction = -1;
            }
            index += direction;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : sb) {
            result.append(row);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        main m = new main();
        String res = m.convert("SHEINFORALL", 4);
        System.out.println(res);
    }
}
