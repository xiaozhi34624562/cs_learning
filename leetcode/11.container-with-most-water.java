/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
       int s = 0;
       for (int i = 0; i < height.length - 1; i++) {
           for (int j = i + 1; j < height.length; j++){
               s = Math.max(s, Math.min(height[j], height[i]) * (j - i));
           }
       } 
       return s;
    }
}
// @lc code=end

