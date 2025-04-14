package com.hhp.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeBuilder {

    public static List<TreeNode> buildTree(List<List<String>> data) {
        Map<String, TreeNode> rootMap = new LinkedHashMap<>();
        for (List<String> path : data) {
            if (path.isEmpty()) continue;
            String rootName = path.get(0);
            TreeNode current = rootMap.computeIfAbsent(rootName, k -> new TreeNode(rootName));
            for (int i = 1; i < path.size(); i++) {
                current = current.addChildren(path.get(i));
            }
        }
        return new ArrayList<>(rootMap.values());
    }

    public static void printTree(List<TreeNode> roots) {
        for (TreeNode root : roots) {
            printNode(root, 0);
        }
    }

    private static void printNode(TreeNode node, int level) {
        System.out.println("  ".repeat(Math.max(0, level)) + node.getName());
        for (TreeNode child : node.getChildren().values()) {
            printNode(child, level + 1);
        }
    }

    public static Map<String, Object> convertToMap(List<TreeNode> nodes) {
        return nodes.stream()
                .collect(Collectors.toMap(
                        TreeNode::getName,
                        node -> buildMap(node.getChildren()),
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
    }

    private static Map<String, Object> buildMap(Map<String, TreeNode> children) {
        Map<String, Object> map = new LinkedHashMap<>();
        children.forEach((name, node) -> {
            map.put(name, buildMap(node.getChildren()));
        });
        return map;
    }

    public static void printMapHelper(Map<String, Object> map, int level) {
        String indent = "  ".repeat(level);
        map.forEach((key, value) -> {
            System.out.println(indent + key);
            if (value instanceof Map) {
                printMapHelper((Map<String, Object>) value, level + 1);
            }
        });
    }
}
