package open2020Silver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SocialDistancing {
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
		File file = new File("socdist.in");
		Scanner in = new Scanner(file);		
		n = in.nextInt();
		m = in.nextInt();
		long[][] intervals = new long[m][2];
		for (int i = 0; i < m; i++) {
			long a = in.nextLong();
			long b = in.nextLong();			
			intervals[i][0] = a;
			intervals[i][1] = b;
		}
		Arrays.sort(intervals, (c, d) -> Long.compare(c[0], d[0]));
		long lo = 2;
		long hi = intervals[m - 1][1] - intervals[0][0] + 1;
		while (lo < hi) {
			long mid = lo + (hi - lo) / 2;
			if (canNotDo(intervals, mid)) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		long ans = lo - 1;
		FileWriter myWriter = new FileWriter("socdist.out");
		myWriter.write(String.valueOf(ans));
	    myWriter.close();
	}
	
	public static boolean canNotDo(long[][] intervals, long D) {
		if (intervals[m - 1][1] - intervals[0][0] < (n - 1) * D) {
			return true;
		}
		long count = 0;
		long pre = Long.MIN_VALUE;
		for (long[] interval : intervals) {
			while (Math.max(pre + D, interval[0]) <= interval[1]) {
				pre = Math.max(pre + D, interval[0]);
				count++;
				if (count >= n) {
					return false;
				}
			}
		}
		return count < n;
	}
}
