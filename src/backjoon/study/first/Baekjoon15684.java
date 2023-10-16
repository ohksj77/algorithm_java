package backjoon.study.first;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon15684 {
    private static int n, m, h, answer;
    private static int[][] map;
    private static boolean finish = false;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        if (m == 0) {
            bw.write("0");
            bw.flush();
        } else {
            int x, y;
            map = new int[h + 1][n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
                map[x][y + 1] = 2;
            }
            for (int i = 0; i <= 3; i++) {
                answer = i;
                dfs(1, 0);
                if (finish) break;
            }
            bw.write((finish) ? String.valueOf(answer) : "-1");
            bw.flush();
        }
    }

    private static void dfs(int x, int count) {
        if (finish) return;
        if (answer == count) {
            if (check()) {
                finish = true;
            }
            return;
        }
        for (int i = x; i < h + 1; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    dfs(i, count + 1);
                    map[i][j] = map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= n; i++) {
            int x = 1, y = i;
            for (int j = 0; j < h; j++) {
                if (map[x][y] == 1) {
                    y++;
                } else if (map[x][y] == 2) {
                    y--;
                }
                x++;
            }
            if (y != i) {
                return false;
            }
        }
        return true;
    }
}
