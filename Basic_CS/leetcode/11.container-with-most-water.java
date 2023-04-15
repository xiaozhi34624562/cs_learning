/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 * 
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/container-with-most-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

// @lc code=start
class Solution {
    // 暴力求解法，遍历
    // 复杂度O(n2)
    public int maxArea(int[] height) {
       int s = 0;
       for (int i = 0; i < height.length - 1; i++) {
           for (int j = i + 1; j < height.length; j++){
               s = Math.max(s, Math.min(height[j], height[i]) * (j - i));
           }
       } 
       return s;
    }

    // 复杂度O(n) 双指针法
    public int maxArea(int[] height) {
        int s = 0, l = 0, r = height.length -1;
        while (l < r) {
            s = Math.max(s, Math.min(height[r], height[l]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return s;
    }
}
// @lc code=end

