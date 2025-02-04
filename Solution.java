import java.util.*;
public class Solution {  public static int[] twoSum(int[] nums, int target) {
            int[] returnsum =new int[2];
            int n =nums.length;
            Map<Integer,Integer> targetMap=new HashMap<>();

            for (int i=0;i<n;i++){
                int targ0=target-nums[i];
                if (targetMap.get(targ0)!=null)
                {
  returnsum[1]=i;
                    int val=targetMap.get(targ0);
                    returnsum[0]=targetMap.get(targ0);
                    return returnsum;
                }
  targetMap.put(nums[i],i);
            }
return null;
            }public static void main(String[] args) {
    int target = Integer.parseInt(args[1]);
    String[] strNumbers = args[0].split(" ");
    int[] nums = new int[strNumbers.length];
    for (int i = 0; i < strNumbers.length; i++) {
        nums[i] = Integer.parseInt(strNumbers[i].trim());
        //  System.out.println("nums: " + nums[i] );
    }
    int[] result = twoSum(nums,target);
    System.out.println("Indices: " + result[0] + ", " + result[1]);

}        }
