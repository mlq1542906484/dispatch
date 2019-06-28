package com.jiadun.dispatch.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coders
 * @date 2017年11月9日23:33:45
 */
@Data
public class TreeNode {
	protected Long id;
	protected Long parentId;
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	public void add(TreeNode node) {
		children.add(node);
	}
}
