package com.graph.Competitive.LeetCodeJan2021;

public class GetClonedTarget {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null) 
            return null;
        if(target.equals(original))
            return cloned;
        
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        TreeNode right = getTargetCopy(original.left, cloned.left, target);
        
        return left != null ? left : right; 
        
    }
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }
