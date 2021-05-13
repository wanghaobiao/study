package com.study.leetcode.lc_26;

public class Solution {
    /**
     * 输入：
     * ["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
     * [[1, 1, 0], [1], [2], [3], [1]]
     * 输出：
     * [null, true, true, false, false]
     *
     * 解释：
     * ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
     * parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
     * parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
     * parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
     * parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/design-parking-system
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private  int[] parkingSystem = {};

    public Solution(int big, int medium, int small) {
        parkingSystem = new int[]{big, medium, small};
    }

    public boolean addCar(int carType) {
        parkingSystem [carType - 1]--;
        return parkingSystem[carType - 1] >= 0;
    }

    public static void main(String[] args) {

    }
}
