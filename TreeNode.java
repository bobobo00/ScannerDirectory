package ScannerDir;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeNode
 * @Description TODO
 * @Auther danni
 * @Date 2019/12/1 15:16]
 * @Version 1.0
 **/

public class TreeNode {
    File file;
    double length;
    List<TreeNode> childs=new ArrayList<>();

    public TreeNode(File file) {
        this.file = file;
    }
}
