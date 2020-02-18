class LRS
{
	// Function to find LRS of substrings X[0..m-1], X[0..n-1]
	public static String LRS(String X, int m, int n, int[][] T)
	{
		// if we have reached the end of either sequence
		// return empty string
		if (m == 0 || n == 0) {
			return new String("");
		}

		// if characters at index m and n matches and index is different
		if (X.charAt(m - 1) == X.charAt(n - 1) && m != n) {
			return LRS(X, m - 1, n - 1, T) + X.charAt(m - 1);
		}
		else
		// else if characters at index m and n don't match
		{
			if (T[m - 1][n] > T[m][n - 1]) {
				return LRS(X, m - 1, n, T);
			} else {
				return LRS(X, m, n - 1, T);
			}
		}
	}

	// Function to fill lookup table by finding the length of LRS
	// of substring X[0..n-1]
	public static void LRSLength(String X, int[][] T)
	{
		// first row and first column of the lookup table
		// are already 0 as T[][] is globally declared

		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= X.length(); i++)
		{
			for (int j = 1; j <= X.length(); j++)
			{
				// if characters at index i and j matches
				// and the index is different
				if (X.charAt(i - 1) == X.charAt(j - 1) && i != j) {
					T[i][j] = T[i - 1][j - 1] + 1;
				}
				// else if characters at index i and j are different
				else {
					T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
				}
			}
        }
    }

        public static int LRSLen(String X)
	{
		int n = X.length();

		// lookup table stores solution to already computed sub-problems

		// i.e. T[i][j] stores the length of LRS of substring
		// X[0..i-1] and X[0..j-1]
		int[][] T = new int[n + 1][n + 1];

		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				// if characters at index i and j matches
				// and the index is different
				if (X.charAt(i - 1) == X.charAt(j - 1) && i != j) {
					T[i][j] = T[i - 1][j - 1] + 1;
				}
				// else if characters at index i and j are different
				else {
					T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
				}
			}
		}

		// LRS will be last entry in the lookup table
		return T[n][n];
	}


		// Uncomment below code to print the lookup table
		/*for (int i = 0; i <= X.length(); i++) {
			System.out.println(Arrays.toString(T[i]));
		}*/


	// main function
	public static void main(String[] args)
	{
		String X = "TTGGAACC";

		// T[i][j] stores the length of LRS of substring X[0..i-1], X[0..j-1]
		int[][] T = new int[X.length() + 1][X.length() + 1];

		// fill lookup table
		LRSLength(X, T);

		// find Longest Repeating Subsequence
		System.out.print(LRS(X, X.length(), X.length(), T)+" "+LRSLen(X));
	}
}