package ca.ubc.cs.cpsc210.mindthegap.parsers;


import ca.ubc.cs.cpsc210.mindthegap.model.Branch;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parser for route strings in TfL line data
 */
public class BranchStringParser {


    /**
     * Parse a branch string obtained from TFL
     *
     * @param branch branch string
     * @return list of lat/lon points parsed from branch string
     */
    public static List<LatLon> parseBranch(String branch) {
        List<LatLon> forest = new ArrayList<LatLon>();
        List<String> list = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        if(branch.length() > 7){
          branch = branch.substring(2, (branch.length() - 2));
        }
        for (String retval : branch.split(",")) {
            list.add(retval);
        }
        for (String next : list) {
            if (next.contains("[")) {

                String a = next.substring(1);
                list2.add(a);
            }
            if (next.contains("]") && next.length()>2) {
                String b = next.substring(0, next.length() - 1);
                list2.add(b);
            }
        }
        Double a = null;
        Double b = null;
        for (String next : list2) {
            if (a == null) {
                a = Double.parseDouble(next);
            } else {
                b = Double.parseDouble(next);

                forest.add(new LatLon(b,a));
                a = null;
                b = null;
            }


        }
        return forest;
    }
}