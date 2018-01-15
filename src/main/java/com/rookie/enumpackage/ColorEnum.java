package com.rookie.enumpackage;

import java.io.Serializable;

/**
 * Created by zhangyechen on 2017/12/13.
 */
public enum ColorEnum implements Serializable {

    RED("001","红色"),
    BLUE("002","蓝色"),
    YELLOW("003","黄色");

    /* 颜色码值 */
    private String colorCode;
    /* 颜色描述 */
    private String colorName;

    ColorEnum(String code, String colorName) {
        this.colorCode = code;
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    /**
     * 根据code值获取枚举实例
     * @param code 枚举码值
     * @return 枚举实例
     */
    public static ColorEnum getColorEnumByCode(String code) {

        for (ColorEnum ce: ColorEnum.values()) {
            if(code.equals(ce.getColorCode())){
                return ce;
            }
        }

        return null;
    }

}
