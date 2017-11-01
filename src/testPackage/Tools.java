/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

/**
 *
 * @author ��ѧ��
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Tools {

    static String SqlmapDir;
    static String DB;

    /**
     * @param args
     * @throws IOException
     */


    public static int delBat(File dir) {
        File[] files = dir.listFiles();
        int count = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                delBat(files[i]);
            } else {
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                if (strFileName.endsWith(".bat")) {
                    System.out.println("����ɾ����" + strFileName);
                    files[i].delete();
                    count++;
                }
            }
        }
        return count;
    }
//ɾ������ļ���

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //�ݹ�ɾ��Ŀ¼�е���Ŀ¼��
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // Ŀ¼��ʱΪ�գ�����ɾ��
        return dir.delete();
    }
//�ж��Ƿ��Ѵ��ھɵ�bat

    public static boolean isExistBat(File dir) {
        File[] f = dir.listFiles();
        boolean flag = false;
        for (int i = 0; i < f.length; i++) {
            if (f[i].isDirectory()) {
                isExistBat(f[i]);
            } else {
                if (f[i].getName().endsWith(".bat")) {
                    //System.out.println(f[i].getName()+"���ļ�Ϊbat�ļ�");
                    flag = true;
                }
            }
        }
        return flag;
    }
//�ж��Ƿ����ļ�

    public static boolean isExistFile(String dir, String s) {
        File file = new File(dir + "\\" + s);
        //System.out.println(file.toString());
        return (file.exists());
    }
//��ʱ��������������޷����������ļ��У�ʹ��getFiles()

    public static ArrayList<String> getFileList(File file) {
        ArrayList<String> result = new ArrayList<String>();
        if (!file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            result.add(file.getAbsolutePath());
        } else {
            // �ڲ������࣬���������ļ�����  
            File[] directoryList = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().indexOf("txt") > -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            for (int i = 0; i < directoryList.length; i++) {
                result.add(directoryList[i].getAbsolutePath());
            }
        }
        return result;
    }
//��ȡĿ¼�����е�txt�ļ���������Ŀ¼��

    public static ArrayList<String> getFiles(File fileDir, String fileType) {
        ArrayList<String> lfile = new ArrayList<String>();
        File[] fs = fileDir.listFiles();
        for (File f : fs) {
            if (f.isFile()) {
                if (fileType
                        .equals(f.getName().substring(
                                f.getName().lastIndexOf(".") + 1,
                                f.getName().length()))) {
                    lfile.add(f.getAbsolutePath());
                }
            } else {
                ArrayList<String> ftemps = getFiles(f, fileType);
                lfile.addAll(ftemps);
            }
        }
        return lfile;
    }

//    static void readXML() {
//        String rootPath = System.getProperty("user.dir").replace("\\", "/");
//        // ����ʹ�þ���·��
//        //System.out.println(rootPath);
//        SAXReader reader = new SAXReader();
//        File f = new File(rootPath + "\\config.xml");
//        try {
//
//            // ��ȡXML�ļ� 
//            Document doc = reader.read(f);
//
//            Element root = doc.getRootElement();
//
//            //System.out.println(root.getName()); 
//            List<Element> param = root.elements();
//
//            for (Element element : param) {
//                if (element.getName().equals("SqlmapDir")) {
//                    SqlmapDir = element.getText();
//                } else if (element.getName().equals("DB")) {
//                    DB = element.getText();
//                }
//
//            }
//
//        } catch (DocumentException e) {
//
//            e.printStackTrace();
//
//        }
//    }

    public void killProcess() {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
