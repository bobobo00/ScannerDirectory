package ScannerDir;

import java.io.File;

/**
 * @ClassName FileNode
 * @Description TODO
 * @Auther danni
 * @Date 2019/12/1 11:27]
 * @Version 1.0
 **/

public class FileNode {
    private File file;
    private double length;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    private int tier;

    public FileNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
