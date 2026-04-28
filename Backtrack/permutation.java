//What is permutation ?
// permutation is the different ways of arranging the element
//Example 
// arr[]= {1,2,3};
// permutations are 
// [1,2,3]
// [1,3,2]
// [2,1,3]
// [2,3,1]
// [3,1,2]
// [3,2,1]

//steps to find the permutation
//1.Start from index = 0
//2.Treat each index as a position to fix a number.
//3.Loop from current index → end
//4.Try every element as the current position.
//5.Swap (choose) & Put one element at the current index.
//6.Recurse (explore)
//7.Move to next index (indx + 1).
//8.Backtrack (undo swap)
//9.Restore original array to try next possibility.
//10.Base case
//When indx == n → store current permutation 

import java.util.*;

public class permutation {
    public void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void helper(int arr[], ArrayList<ArrayList<Integer>> res, int indx) {
        if (indx == arr.length) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int val : arr) {
                temp.add(val);
            }
            res.add(temp);
            return;
        }
        for (int i = indx; i < arr.length; i++) {
            swap(arr, i, indx);
            helper(arr, res, indx + 1);
            swap(arr, i, indx);
        }
    }

    public ArrayList<ArrayList<Integer>> getPermutation(int arr[]) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        helper(arr, list, 0);

        return list;
    }

    public static void main(String[] args) {
        int arr1[] = { 1, 1, 2 };
        permutation per = new permutation();
        ArrayList<ArrayList<Integer>> res = per.getPermutation(arr1);
        System.out.println(res);

    }
}
