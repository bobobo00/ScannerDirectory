package ScannerDir;

import java.io.*;
import java.util.Collections;

/**
 * @ClassName Scanner
 * @Description TODO
 * @Auther danni
 * @Date 2019/12/1 11:27]
 * @Version 1.0
 **/

public class Scanner {
    public void scanner(FileNode root){
        if(root.getFile().isDirectory()){
            printNull(root);
            System.out.println(root.getFile().getName()+" ");
            //printLength(root.getFile().length());
            File[] children=root.getFile().listFiles();
            for(File file:children){
                FileNode child=new FileNode(file);
                child.setTier(root.getTier()+child.getTier()+1);
                child.setLength(file.length());
                root.setLength(root.getLength()+file.length());
                if(file.isDirectory()){
                    scanner(child);
                }else{
                    printNull(child);
                    System.out.print(file.getName()+"  ");
                    printLength(file.length());
                }
            }
        }else{
            printNull(root);
            System.out.println(root.getFile().getName()+" ");
            printLength(root.getFile().length());
        }
    }
    private void printLength(double length){
        if(length>1024*1024*1024){
            System.out.printf("%.1f",length/1024/1024/1024);
            System.out.println( "G字节");
        }else if(length>1024*1024){
            System.out.printf("%.1f",length/1024/1024);
            System.out.println( "M字节");
        }else if(length>1024){
            System.out.printf("%.1f",length/1024);
            System.out.println( "K字节");
        }else{
        System.out.printf("%.1f",length);
        System.out.println( "字节");}
    }
    public void scanner(TreeNode root){
        if(root==null){
            return;
        }
        File[] files=root.file.listFiles();
        if(files==null){
            return;
        }
        for(File file:files){
            TreeNode child=new TreeNode(file);
            if(file.isDirectory()){
                scanner(child);
            }else{
                child.length+=file.length();
            }
            root.length+=child.length;
            root.childs.add(child);
        }
    }
    private void sort(TreeNode root){
        Collections.sort(root.childs,(o1,o2)->{
            if(o1.length>o2.length){
                return 1;
            }else if(o1.length<o2.length){
                return -1;
            }else{
                return 0;
            }
        });
    }
    public void reporter(TreeNode root, int level, PrintStream ps){
        for(int i=0;i<level;i++){
            ps.print("- ");
            ps.flush();
        }
        if(level==0){
            ps.print(root.file.getPath()+"  ");
            length(root.length,ps);
            ps.flush();
        }else{
            ps.print(root.file.getName()+" ");
            length(root.length,ps);
            ps.flush();
        }
        if(root.file.isDirectory()){
           // ps.print("--");
           // ps.flush();
        }
        for(TreeNode node:root.childs){
            reporter(node,level+1,ps);
        }

    }
    private void length(double length,PrintStream ps){
        if(length>1024*1024*1024){
           ps.printf(" %.2f",length/1024/1024/1024);
           ps.println( "G字节");
        }else if(length>1024*1024){
            ps.printf(" %.2f",length/1024/1024);
            ps.println( "M字节");
        }else if(length>1024){
            ps.printf(" %.2f",length/1024);
            ps.println( "K字节");
        }else{
            ps.printf(" %.2f",length);
            ps.println( "字节");
        }
        ps.flush();
    }
    private void printNull(FileNode node){
        int numTier=node.getTier();
        while(numTier>0){
            System.out.print("  ");
            numTier--;
        }
    }
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
       // FileNode root=new FileNode(new File("E:\\IDEA-java\\Io"));
        Scanner scanner=new Scanner();
        //scanner.scanner(root);
        TreeNode root=new TreeNode(new File("E:\\IDEA-java\\Io"));
        scanner.scanner(root);
        scanner.sort(root);
        PrintStream ps=new PrintStream(new BufferedOutputStream(new FileOutputStream(
                new File("test.txt"))),true,"utf-8");

        scanner.reporter(root,0,ps);
    }
}
