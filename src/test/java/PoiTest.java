import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class PoiTest {
    @Test
    public void test01() throws IOException {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream("F:/new.docx");

        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingBeforeLines(100);
        XWPFRun run = paragraph.createRun();
        run.setText("一、设备费");
        run.setFontSize(18);
        run.setBold(true);

        paragraph=document.createParagraph();
        run=paragraph.createRun();
        run.setText("北京航空航天大学拟购置1类设备3台（套），总价格20万元。");

        XWPFTable table = document.createTable(3,8);
        List<XWPFTableRow> rows = table.getRows();
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> tableCells = row.getTableCells();
            for (XWPFTableCell tableCell : tableCells) {
                tableCell.setText("啊啊啊啊");
            }
        }
        document.write(out);
        out.close();

    }



    class Student{
        private int id;
        private String name;
        private int age;

        public Student() {
        }

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
