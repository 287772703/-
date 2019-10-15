/*
*这是hive自定义udf
*
*/
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Myhive extends UDF {
    public int evaluate(String str) {
        int age = 0;
        if (str.matches("\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}")) {
            String[] dateStr = str.split("-");
            int year = Integer.parseInt(dateStr[0]);
            int month = Integer.parseInt(dateStr[1]);
            int day = Integer.parseInt(dateStr[2]);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = simpleDateFormat.format(new Date());
            String[] now = nowDate.split("-");
            int nowYear = Integer.parseInt(now[0]);
            int nowmonth = Integer.parseInt(now[1]);
            int nowDay = Integer.parseInt(now[2]);
            if (nowmonth==month && nowDay <= day ){
                age=nowYear-year-1;
            }else if (nowmonth< month){
                age=nowYear-year-1;
            }else {
                age=nowYear-year;
            }
        } else {
            System.out.println("you input don't matche str");
        }
        return age;
    }

    public static void main(String[] args) {
        new Myhive().evaluate("2010-9-2");
    }

}
