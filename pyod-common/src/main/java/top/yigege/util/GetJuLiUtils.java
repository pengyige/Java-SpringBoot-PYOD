package top.yigege.util;


import java.math.RoundingMode;
import java.text.NumberFormat;

import freemarker.template.utility.StringUtil;


/**
 * author: yigege
 * created on: 2020/3/11 18:40
 * description:
 */
public class GetJuLiUtils {


    private static NumberFormat nf = NumberFormat.getNumberInstance();

 /*   private static final double EARTH_RADIUS = 6378137.0;

    public static double getDistance(double longitude,double latitue,double longitude2,double latitue2){
        double lat1 = rad(latitue);
        double lat2 = rad(latitue2);
        double a = lat1 - lat2;
        double b = rad(longitude)-rad(longitude2);
        double s = 2*Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin(b/2),2)));
        s=s*EARTH_RADIUS;
        s=Math.round(s*10000)/10000;
        return s;
    }
    private static double rad(double d){
        return d*Math.PI/180.0;
    }

*/

    /**
     * 计算距离 返回单位km
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = (lat1 * Math.PI / 180.0);

        double radLat2 = (lat2 * Math.PI / 180.0);

        double a = radLat1 - radLat2;

        double b = (lng1 - lng2) * Math.PI / 180.0;

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)

                + Math.cos(radLat1) * Math.cos(radLat2)

                * Math.pow(Math.sin(b / 2), 2)));
        final double EARTH_RADIUS = 6378137.0;
        s = s * EARTH_RADIUS;

        s = Math.round(s * 10000) / 10000;

        return s;
    }

    /**
     * 距离格式化
     *
     * @param distance 以千米为单位
     * @return
     */
    public static String distanceKMFormat(double distance) {
        return distance > 1 ? (distance + "km") : (distance * 1000 + "m");
    }


    /**
     * 距离只保留两位小数
     * @param distance 以米为单位
     * @return
     */
    public static String distanceFormat(double distance) {
        String str;
        double value = distance / 1000;

        if (distance >= 1000 && distance <= 20000) {
            str = "km";
            return String.format("%s %s",(int)(value),str);
        } else if (distance > 20000 )  {
            /*if (null != city && StringUtils.isNotBlank(city.getName())) {
                return city.getName();
            }*/

            str = "km";
            String result = String.format(">20 %s",str);
            return result;
        }
        else {
            str = "m";
            return String.format("%s %s",(int)(distance),str);
        }

    }


    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }



}
