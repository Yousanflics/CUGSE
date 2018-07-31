/*
*
*/
public class BinarySearchTree<T extends Comparable<? super T>> {

    /**结点数据结构*/
    static class BinaryNode<T>
    {
        T data;
        BinaryNode<T> left;
        BinaryNode<T> right;
        public BinaryNode(T data) {
            this(data,null,null);
        }
        public BinaryNode( T data, BinaryNode<T> left, BinaryNode<T> right) {
            this.data =data;
            this.left = left;
            this.right =right;
        }
        /*public BinaryNode()
        {
            data =null;
            this.left = left;
            this.right =right;
        }*/
    }

    private BinaryNode<T> rootTree;
    /**构造一颗空的二叉查找树*/
    public BinarySearchTree()
    {
        rootTree = null;
    }
    /**清空二叉查找树*/
    public void clear()
    {
        rootTree = null;
    }
    /**判断是否为空*/
    public boolean isEmpty()
    {
        return rootTree == null;
    }
    /**查找指定的元素,默认从
     * 根结点出开始查询*/
    public boolean contains(T t)
    {
        return contains(t, rootTree);

    }
    /**找到二叉查找树中的最小值*/
    public T findMin()
    {
        if(isEmpty())
        {
            System.out.println("二叉树为空");
            return null;
        }else
            return findMin(rootTree).data;

    }
    /**找到二叉查找树中的最大值*/
    public T findMax()
    {
        if(isEmpty())
        {
            System.out.println("二叉树为空");
            return null;
        }else
            return findMax(rootTree).data;
    }
    /**插入元素*/
    public void insert(T t)
    {
        rootTree = insert(t, rootTree);
    }
    /**删除元素*/
    public void remove(T t)
    {
        rootTree = remove(t,rootTree);
    }
    /**打印二叉查找树*/
    public void printTree()
    {

    }
    /**从某个结点出开始查找元素*/
    public boolean contains(T t, BinaryNode<T> node)
    {
        if(node==null)
            return false;
        int result = t.compareTo(node.data);
        if(result>0)
            return contains(t,node.right);
        else if(result<0)
            return contains(t, node.left);
        else
            return true;
    }
    /**查询出最小元素所在的结点*/
    public BinaryNode<T> findMin(BinaryNode<T> node)
    {
        if(node==null)
            return null;
        else if(node.left==null)
            return node;
        return findMin(node.left);//递归查找
    }
    /**查询出最大元素所在的结点*/
    public BinaryNode<T> findMax(BinaryNode<T> node)
    {
        if(node!=null)
        {
            while(node.right!=null)
                node=node.right;
        }
        return node;
    }
    /**在某个位置开始判断插入元素*/
    public BinaryNode<T> insert(T t,BinaryNode<T> node)
    {
        if(node==null)
        {
            //新构造一个二叉查找树
            return new BinaryNode<T>(t, null, null);
        }
        int result = t.compareTo(node.data);
        if(result<0)
            node.left= insert(t,node.left);
        else if(result>0)
            node.right= insert(t,node.right);
        else
            ;//doNothing
        return node;
    }
    /**在某个位置开始判断删除某个结点*/
    public BinaryNode<T> remove(T t,BinaryNode<T> node)
    {
        if(node == null)
            return node;//if find nothing? No change
        int result = t.compareTo(node.data);
        if(result>0)
            node.right = remove(t,node.right);
        else if(result<0)
            node.left = remove(t,node.left);
        else if(node.left!=null&&node.right!=null)
        {
            node.data = findMin(node.right).data;
            node.right = remove(node.data,node.right);
        }
        else
            node = (node.left!=null)?node.left:node.right;
        return node;

    }
    public BinaryNode<Integer> init()
    {
        BinaryNode<Integer> node3 = new BinaryNode<Integer>(3);
        BinaryNode<Integer> node1 = new BinaryNode<Integer>(1);
        BinaryNode<Integer> node4 = new BinaryNode<Integer>(4,node3,null);
        BinaryNode<Integer> node2 = new BinaryNode<Integer>(2,node1,node4);
        BinaryNode<Integer> node8 = new BinaryNode<Integer>(8);
        BinaryNode<Integer> root = new BinaryNode<Integer>(6,node2,node8);
        return root;
    }
    public void preOrder(BinaryNode node) {
        if (node != null) {
            System.out.print(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    /*简单测试*/
    public static void main(String[] args) {
        BinarySearchTree  searchTree = new BinarySearchTree<>();
        BinaryNode<Integer> node= searchTree.init();
        searchTree.rootTree=node;
        searchTree.preOrder(searchTree.rootTree);
        searchTree.remove(4);
        searchTree.preOrder(searchTree.rootTree);
    }

}