package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제 : https://www.acmicpc.net/problem/14889
해설 : https://st-lab.tistory.com/122
정리내용(블로그)
    -  : 
    -  : 
    -  : 
*/
public class prob14889 {
}

/*
결과 
    - 메모리 : 20892
    - 시간 : 368
    - 시간복잡도 : 
*/
class prob14889_1{

    static int N;
    static int[][] map;
    static boolean[] visit;

    static int Min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visit = new boolean[N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " "); //이럴때는 tokenizer가 낫겠다.
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0);
        System.out.println(Min);

    }

    private static void combi(int idx, int count) {

        if (count == N/2) {
            diff();
            return;
        }

        for (int i = idx; i<N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                combi(i+1, count+1);
                visit[i] = false;
            }
        }
    }

    static void diff() {
        int team_start = 0;
        int team_link = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // i 번째 사람과 j 번째 사람이 true라면 스타트팀으로 점수 플러스 
                if (visit[i] == true && visit[j] == true) {
                    team_start += map[i][j];
                    team_start += map[j][i];
                }
                // i 번째 사람과 j 번째 사람이 false라면 링크팀으로 점수 플러스 
                else if (visit[i] == false && visit[j] == false) {
                    team_link += map[i][j];
                    team_link += map[j][i];
                }
            }
        }
        // 두 팀의 점수 차이 (절댓값)
        int val = Math.abs(team_start - team_link);
		
		/*
		  두 팀의 점수차가 0이라면 가장 낮은 최솟값이기 때문에
		  더이상의 탐색 필요없이 0을 출력하고 종료하면 된다.
		 */
        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }

        Min = Math.min(val, Min);

    }

}
