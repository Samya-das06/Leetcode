class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        res = []
        n = len(nums)

        for i in xrange(n - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            target = -nums[i]
            left, right = i + 1, n - 1

            while left < right:
                sum_lr = nums[left] + nums[right]

                if sum_lr == target:
                    res.append([nums[i], nums[left], nums[right]])

                    # Skip duplicates efficiently
                    left_val = nums[left]
                    right_val = nums[right]
                    while left < right and nums[left] == left_val:
                        left += 1
                    while left < right and nums[right] == right_val:
                        right -= 1

                elif sum_lr < target:
                    left += 1
                else:
                    right -= 1

        return res