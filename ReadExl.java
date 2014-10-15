import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import java.net.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
public class ReadExl
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) 
    {
        String fileToBeRead = "Drone.xls"; // excel位置
        URL url = new URL("http://www.ceve-market.org/api/market/type/34.xml");
        String Data=((HttpUrlConnection)url.openConnection()).getInputStream();
        int coloum = 2;
        try 
        {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
            HSSFSheet sheet = workbook.getSheet("Sheet1");
 
            for (int i = 2; i <= 32; i++) 
            {
                HSSFRow row = sheet.getRow((short) i);
                HSSFCell cell = row.getCell((short) coloum);
                cell.setCellValue(250);
            }
            FileOutputStream out = null;
            try 
            {
                out = new FileOutputStream(fileToBeRead);
                workbook.write(out);
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            finally 
            {
                try 
                {
                    out.close();
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}