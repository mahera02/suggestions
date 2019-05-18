package com.sug.iv.suggestions.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LocationTrie {
	private TrieNode root;

	public LocationTrie() {
		root = new TrieNode(' ');
	}

	public void insert(String location, float latitude, float longitude) {
		if (search(location) == true)
			return;

		TrieNode current = root;
		TrieNode pre;
		for (char ch : location.toCharArray()) {
			pre = current;
			if (current.getChild(ch) == null)
				current.children.add(new TrieNode(ch));
			TrieNode child = current.getChild(ch);
			current = child;
			child.parent = pre;
		}
		current.longitude = longitude;
		current.latitide = latitude;
		current.isEnd = true;
	}

	public boolean search(String location) {
		TrieNode current = root;
		for (char ch : location.toCharArray()) {
			if (current.getChild(ch) == null)
				return false;
			else {
				current = current.getChild(ch);
			}
		}
		if (current.isEnd == true) {
			return true;
		}
		return false;
	}

	public List<Location> autocompleteL(String prefix) {
		TrieNode lastNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.getChild(prefix.charAt(i));
			if (lastNode == null)
				return new ArrayList<>();
		}

		return lastNode.getLocations();
	}
}

class TrieNode {
	private static final float DEFAULT = 0;
	char data;
	float longitude;
	float latitide;

	LinkedList<TrieNode> children;
	TrieNode parent;
	boolean isEnd;

	public TrieNode(char c) {
		data = c;
		children = new LinkedList<>();
		isEnd = false;
	}

	public TrieNode getChild(char c) {
		if (children != null)
			for (TrieNode eachChild : children)
				if (eachChild.data == c)
					return eachChild;
		return null;
	}

	protected List<Location> getLocations() {
		List<Location> list = new ArrayList<>();
		if (isEnd) {
			list.add(new Location(toString(), latitide, longitude, DEFAULT));
		}

		if (children != null) {
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i) != null) {
					list.addAll(children.get(i).getLocations());
				}
			}
		}
		return list;
	}

	public String toString() {
		if (parent == null) {
			return "";
		} else {
			return parent.toString() + new String(new char[] { data });
		}
	}
}