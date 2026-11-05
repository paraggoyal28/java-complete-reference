package com.example.spring.chapter28;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class TreeNode {
    TreeNode left, right;
    int val;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class LongestPathMax {

    private static int findDecimalFromBinary(String binaryValue) {
        int value = 0;
        int n = binaryValue.length();
        for (int i = n - 1; i >= 0; --i) {
            if (binaryValue.charAt(i) == '1') {
                value += 1 << (n - 1 - i);
            }
        }
        return value;
    }


    private static void traverse(TreeNode root, int depth, String binaryValue, HashMap<Integer, Integer> pathVals) {
        if (root == null) {
            int decimalValue = findDecimalFromBinary(binaryValue);
            pathVals.put(depth, Math.max(pathVals.getOrDefault(depth, 0), decimalValue));
            return;
        }

        binaryValue += root.val;
        traverse(root.left, depth + 1, binaryValue, pathVals);
        traverse(root.right, depth + 1, binaryValue, pathVals);
    }

    public static int findMaxValue(TreeNode root) {
        HashMap<Integer, Integer> pathValues = new HashMap<>();

        String binaryVal = "";
        traverse(root, 0, binaryVal, pathValues);

        int maxPath = 0;

        Iterator<Map.Entry<Integer, Integer>> itr = pathValues.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<Integer, Integer> entry = itr.next();
            maxPath = Math.max(maxPath, entry.getKey());
        }

        return pathValues.getOrDefault(maxPath, 0);

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(0);
        System.out.println(findMaxValue(root));
    }
}
