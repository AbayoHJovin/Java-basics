package DSA;

import java.util.*;

public class DataStructure {
    public static void main(String[] args){
        List<String> districts = new ArrayList<String>();
        districts.addAll(Arrays.asList("NYAGATARE","GASABO","HUYE","BUGESERA","GASABO","GASABO","NYARUGENGE","GASABO","HUYE","HUYE","RUHANGO","NYAMAGABE","BUGESERA","GASABO","NYARUGENGE","MUHANGA","GICUMBI","MUSANZE","RUBAVU","KICUKIRO","KICUKIRO","GISAGARA","GAKENKE","RUHANGO","GICUMBI","NYAMASHEKE","NYANZA","NYANZA","RUHANGO","RUHANGO"));
        int i = 1;
        for (String district:districts){
            System.out.println(i + ". " + district);
            i++;
        }
    }
}
