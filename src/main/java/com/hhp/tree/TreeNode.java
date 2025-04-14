package com.hhp.tree;

import java.util.LinkedHashMap;
import java.util.Map;

public class TreeNode {
    private final String name;
    private final Map<String, TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new LinkedHashMap<>();
    }

    public TreeNode addChildren(String childName) {
        return children.computeIfAbsent(childName, k -> new TreeNode(childName));
    }

    public String getName() {
        return name;
    }

    public Map<String, TreeNode> getChildren() {
        return children;
    }
}
