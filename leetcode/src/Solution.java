/**
 * @author tangshao
 */

import java.util.HashMap;
import java.util.Map;

/**
 * this will include the solutions and practise for leetcode questions
 */
public class Solution {
    //leetcode 1: two sum
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                return new int[]{i, index};
            }
            map.put(x, i);
        }
        return new int[0];
    }

    //leetcode 5: dp
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // initialization
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = L + i - 1;
                //越界 退出当前循环
                if (j >= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    //leetcode 6: z字形变换
    public String zTransform(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    ret.append(s.charAt(j + cycleLen - i));
                }
            }
        }

        return ret.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ans = s.twoSum(new int[]{1, 2, 3, 4, 5}, 5);
        System.out.println(ans[0]);
        System.out.println(ans[1]);

        String h = s.zTransform("Helasahsjhasj", 3);
        System.out.println(h);
    }
}
