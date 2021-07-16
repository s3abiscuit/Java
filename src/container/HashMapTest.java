class Solution {
    public int majorityElement(int[] nums) {
        if (nums.length == 1 || nums.length == 2) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }
        System.out.println(map);

        int result = nums[0], max = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            
			if (entry.getValue() >= max) {
                max = entry.getValue();
                result = entry.getKey();
            }
		}
        return result;
    }
}
