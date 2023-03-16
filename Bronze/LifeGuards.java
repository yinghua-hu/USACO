//package jan2018Bronze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LifeGuards {
	public static void main(String[] args) throws IOException {
		File file = new File("lifeguards.in");
		Scanner in = new Scanner(file);		
		int n = in.nextInt();
		int[][] intervals = new int[n][2];
		for (int i = 0; i < n; i++) {
			int a = in.nextInt();
			int b = in.nextInt();			
			intervals[i][0] = a;
			intervals[i][1] = b;
		}
		Arrays.sort(intervals, (c, d) -> c[0] - d[0]);
		int[] t = new int[1001];
		for (int[] interval : intervals) {
			int a = interval[0];
			int b = interval[1];
			t[a]++;
			t[b]--;
		}
		int totalCoverage = 0;
		int[] c = new int[1001];
		int sum = 0;
		for (int u = 0; u <= 1000; u++) {
			sum += t[u];
			if (sum > 0) {
				totalCoverage++;
			}
			c[u] = sum;
		}
		int minRemove = Integer.MAX_VALUE;
		for (int[] interval : intervals) {
			int a = interval[0];
			int b = interval[1];
			int count = 0;
			for (int u = a; u < b; u++) {
				if (c[u] == 1) {
					count++;
				}
			}
			minRemove = Math.min(minRemove, count);
		}
		int ans = totalCoverage - minRemove;
//		System.out.println(ans);
		FileWriter myWriter = new FileWriter("lifeguards.out");
		myWriter.write(String.valueOf(ans));
	    myWriter.close();
	}
}
