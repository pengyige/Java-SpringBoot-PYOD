package top.yigege.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 城市表
 * </p>
 *
 * @author yigege
 * @since 2021-01-08
 */
@TableName("t_city")
public class City extends Model {

    private static final long serialVersionUID = 1L;

    private String cityId;

    private String cityEn;

    private String cityZh;

    private String provinceEn;

    private String provinceZh;

    private String countryEn;

    private String countryZh;

    private String leaderEn;

    private String leaderZh;

    private String lat;

    private String lon;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }
    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }
    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }
    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }
    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }
    public String getCountryZh() {
        return countryZh;
    }

    public void setCountryZh(String countryZh) {
        this.countryZh = countryZh;
    }
    public String getLeaderEn() {
        return leaderEn;
    }

    public void setLeaderEn(String leaderEn) {
        this.leaderEn = leaderEn;
    }
    public String getLeaderZh() {
        return leaderZh;
    }

    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "City{" +
        "cityId=" + cityId +
        ", cityEn=" + cityEn +
        ", cityZh=" + cityZh +
        ", provinceEn=" + provinceEn +
        ", provinceZh=" + provinceZh +
        ", countryEn=" + countryEn +
        ", countryZh=" + countryZh +
        ", leaderEn=" + leaderEn +
        ", leaderZh=" + leaderZh +
        ", lat=" + lat +
        ", lon=" + lon +
        "}";
    }
}
