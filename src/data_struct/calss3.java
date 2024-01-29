/*
 * 2024.1.27
 * 二叉树
 *  先序、中序、后序 遍历(基于递归序)
 *
 */
package data_struct;

import java.util.Stack;

public class calss3 {
    class Node{
        int value;
        Node left;
        Node right;
        public Node(int V){
            value = V;
        }
    }
    /*
     * 递归序
     * f (Node head){
     *     if( head == null){
     *         return;
     *     }
     *     // 1.
     *     f(head.left);
     *     // 2.
     *     f(head.right);
     *     // 3.
     * }
     */
    //先序遍历
    public static void pre(Node head){
        if (head == null){
            return;
        }
        pre(head.left);
        pre(head.right);
    }
    /*
     * 非递归版本
     * 1） 弹出就打印
     * 2） 如有右孩子，先压入右孩子
     * 3） 如有左孩子，就压入左孩子
     */
    public static void pre_non(Node head){
        System.out.println("pre-order");
        if (head != null){
            Stack<Node> stack = new Stack<>();  //准备好栈
            stack.add(head);                    //压入根结点
            while(!stack.isEmpty()){
                Node current = stack.pop();  //弹出栈顶元素
                System.out.print(current.value + " ");
                if (current.right != null){
                    stack.push(current.right);
                }
                if (current.left != null){
                    stack.push(current.left);
                }
            }
        }
        System.out.println(" Done! ");
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}