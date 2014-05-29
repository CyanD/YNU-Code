/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trees;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class TNode {

    private String id;
    private String pId;
    private String name;
    private boolean isLeaf;
    private TNode parentNode;
    private Object datas;
    private boolean checked;
    private List<TNode> children;

    public List<TNode> getChildren() {
        return children;
    }

    public void setChildren(List<TNode> children) {
        this.children = children;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public TNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TNode parentNode) {
        this.parentNode = parentNode;
    }

    public boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
