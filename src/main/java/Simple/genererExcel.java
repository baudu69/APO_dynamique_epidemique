package Simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;

public class genererExcel {
    private static final String EXCEL__FILE__LOCATION = "C:\\Users\\Bastien Audu\\Documents\\excel.xls";
    public static void SIR(ArrayList<Float> S, ArrayList<Float> I, ArrayList<Float> R) {



        //1. Create an Excel file
        WritableWorkbook mySecondWbook = null;
        try {

            mySecondWbook = Workbook.createWorkbook(new File(EXCEL__FILE__LOCATION));
            WritableSheet myFirstSheet = mySecondWbook.createSheet("Sheet 1", 0);

            WritableCellFormat cFormat = new WritableCellFormat();
            WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
            cFormat.setFont(font);

            Label label = new Label(0, 0, "S", cFormat);
            myFirstSheet.addCell(label);
            for (int i = 1; i < S.size(); i++) {
                Number number = new Number(0, i, S.get(i - 1));
                myFirstSheet.addCell(number);
            }

            label = new Label(1, 0, "I", cFormat);
            myFirstSheet.addCell(label);
            for (int i = 1; i < I.size(); i++) {
                Number number = new Number(1, i, I.get(i - 1));
                myFirstSheet.addCell(number);
            }

            label = new Label(2, 0, "R", cFormat);
            myFirstSheet.addCell(label);
            for (int i = 1; i < R.size(); i++) {
                Number number = new Number(2, i, R.get(i - 1));
                myFirstSheet.addCell(number);
            }


            mySecondWbook.write();

        } catch (WriteException | IOException e) {
            e.printStackTrace();
        } finally {

            if (mySecondWbook != null) {
                try {
                    mySecondWbook.close();
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                }
            }


        }


    }
    public static void SEIEVO(ArrayList<Float> S, ArrayList<Float> E, ArrayList<Float> I, ArrayList<Float> R) {



        //1. Create an Excel file
        WritableWorkbook mySecondWbook = null;
        try {

            mySecondWbook = Workbook.createWorkbook(new File(EXCEL__FILE__LOCATION));
            WritableSheet myFirstSheet = mySecondWbook.createSheet("Sheet 1", 0);

            WritableCellFormat cFormat = new WritableCellFormat();
            WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
            cFormat.setFont(font);

            Label label = new Label(0, 0, "S", cFormat);
            myFirstSheet.addCell(label);
            for (int i = 1; i < S.size(); i++) {
                Number number = new Number(0, i, S.get(i - 1));
                myFirstSheet.addCell(number);
            }

            label = new Label(1, 0, "E", cFormat);
            myFirstSheet.addCell(label);
            for (int i = 1; i < S.size(); i++) {
                Number number = new Number(1, i, E.get(i - 1));
                myFirstSheet.addCell(number);
            }

            label = new Label(2, 0, "I", cFormat);
            myFirstSheet.addCell(label);
            for (int i = 1; i < I.size(); i++) {
                Number number = new Number(2, i, I.get(i - 1));
                myFirstSheet.addCell(number);
            }

            label = new Label(3, 0, "R", cFormat);
            myFirstSheet.addCell(label);
            for (int i = 1; i < R.size(); i++) {
                Number number = new Number(3, i, R.get(i - 1));
                myFirstSheet.addCell(number);
            }


            mySecondWbook.write();

        } catch (WriteException | IOException e) {
            e.printStackTrace();
        } finally {

            if (mySecondWbook != null) {
                try {
                    mySecondWbook.close();
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                }
            }


        }


    }
}
