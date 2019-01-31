import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest implements Serializable {
    @Test
    public void test01() {
        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        String regex = "import \\w+(\\.[*a-zA-Z0-9_]+)+;";
        Pattern p = Pattern.compile(regex);
        try {
            url = new URL("https://www.cnblogs.com/qianzf/p/6796588.html");
            urlConnection = url.openConnection();
            pw = new PrintWriter(new FileWriter("F:\\xxx.txt"), true);
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String buf = null;
            while ((buf = br.readLine()) != null) {
                //System.out.println(buf);
                Matcher matcher = p.matcher(buf);
                while (matcher.find()) {
                    pw.println(matcher.group());
                    System.out.println(matcher.group());
                }
            }
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }

    @Test
    public void test02() {
        String regex = "Kelvin";
        Pattern pattern = Pattern.compile(regex);
        String buf = "Kelvin hhh KelvinShop";
        Matcher matcher = pattern.matcher(buf);

        //System.out.println("12345 abcde 78906".matches("\\d+"));

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        //String after = matcher.replaceAll("Kevin");
        //System.out.println(after);
    }

    @Test
    public void test03() {
        System.out.println("123".matches("\\d+"));
        System.out.println("abc123".matches("\\w+"));
        System.out.println(",./123abc".matches("\\w+"));
    }

    @Test
    //匹配
    public void test04() {
        //要匹配的正则表达式
        String regex = "\\b(\\w|\\.)+@\\w+(\\.\\w+)+\\b";
        //被匹配的字符串
        String source = "My email is abcde.12345@163.com.";
        //编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        //根据源字符串生成Matcher
        Matcher matcher = pattern.matcher(source);
        System.out.println(matcher.groupCount());
        //依次匹配子串
        while (matcher.find()) {
            //本次被匹配到的字串
            System.out.println(matcher.group());
        }
        String replaceAll = matcher.replaceAll("hhh");
        System.out.println(replaceAll);
    }

    @Test
    //替换
    public void test05() {
        Pattern pattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        String source = "java Java jAva ILoveJavA youHateJAVA adsdsfd";
        Matcher matcher = pattern.matcher(source);
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (matcher.find()) {
            i++;
            if (i % 2 == 1)
                matcher.appendReplacement(builder, "you");
            else
                matcher.appendReplacement(builder, "me");
        }
        matcher.appendTail(builder);
        System.out.println(builder);
    }

    @Test
    public void test06() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\xxx.txt")));
        String buffer = null;
        while ((buffer = reader.readLine()) != null) {
            System.out.println(buffer);
        }
    }

    @Test
    public void test07() throws Exception {
        URL url = new URL("https://www.bilibili.com/");
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    public void test08() throws Exception {
        FileReader reader = new FileReader("F:\\in.txt");
        FileWriter writer = new FileWriter("F:\\out.txt");
        BufferedReader br = new BufferedReader(reader);
        BufferedWriter bw = new BufferedWriter(writer);
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    @Test
    public void test09() {
        String filename = "D:" + File.separator;
        File file = new File(filename);
        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void test10() throws Exception {
        RandomAccessFile raf = new RandomAccessFile("F:\\in.txt", "rw");
        raf.seek(5);
        String line = raf.readLine();
        System.out.println(line);
    }

    class Student implements Serializable {
        Integer id;
        String name;

        public Student(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Student() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void test11() throws Exception {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("F:\\out.txt"));
        Student student = new Student(12, "张三");
        outputStream.writeObject(student);
        outputStream.flush();
        outputStream.close();
        student = null;

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("F:\\out.txt"));
        student = (Student) inputStream.readObject();
        System.out.println(student);
    }

    @Test
    public void test12() {
        BufferedReader reader =null;
        BufferedWriter writer=null;
        try {
            reader = new BufferedReader(new FileReader("F:\\in.txt"));
            writer=new BufferedWriter(new FileWriter("F:\\out.txt"));
            String line=null;
            while((line=reader.readLine())!=null){
                writer.write(line);
                writer.newLine();
                System.out.println(line);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
