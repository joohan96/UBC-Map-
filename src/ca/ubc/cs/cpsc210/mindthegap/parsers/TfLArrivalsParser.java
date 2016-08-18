package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * A parser for the data returned by the TfL station arrivals query
 */
public class TfLArrivalsParser extends TfLAbstractParser {

    /**
     * Parse arrivals from JSON response produced by TfL query.  All parsed arrivals are
     * added to given station assuming that corresponding JSON object as all of:
     * timeToStation, platformName, lineID and one of destinationName or towards.  If
     * any of the aforementioned elements is missing, the arrival is not added to the station.
     *
     * @param stn             station to which parsed arrivals are to be added
     * @param jsonResponse    the JSON response produced by TfL
     * @throws JSONException  when JSON response does not have expected format
     * @throws TfLArrivalsDataMissingException  when all arrivals are missing at least one of the following:
     * <ul>
     *     <li>timeToStation</li>
     *     <li>platformName</li>
     *     <li>lineId</li>
     *     <li>destinationName and towards</li>
     * </ul>
     */
    //destinationName/
    //lineId
    //platformName/
    //timeToStation/
    //towards/
    public static void parseArrivals(Station stn, String jsonResponse)
            throws JSONException, TfLArrivalsDataMissingException {
        JSONArray poiJsonArray = new JSONArray(jsonResponse);
        int ArrivalParsed = 0;

        for(int i = 0; i < poiJsonArray.length(); i++) {
            boolean a =parseStation(stn, poiJsonArray.getJSONObject(i));
            if(a==true){
                ArrivalParsed++;
            }
        }

        if(ArrivalParsed==0){
            throw new TfLArrivalsDataMissingException();
        }


    }


    private static boolean parseStation( Station stn, JSONObject poiJsonObject)  {

       boolean missingData=false;
        String destinationName = null;
        try {
            destinationName = TfLAbstractParser.parseName(poiJsonObject.getString("destinationName"));
        } catch (JSONException e) {
            try {
                destinationName = poiJsonObject.getString("towards");
            } catch (JSONException f) {
                return false;

            }
        }

        //   destinationName = poiJsonObject.getString("towards");


        int timeToStationStr = 0;
        try {
            timeToStationStr = poiJsonObject.getInt("timeToStation");
        } catch (JSONException e) {
            return false;
        }
        Integer it = timeToStationStr;


        String platformName = null;
        try {
            platformName = poiJsonObject.getString("platformName");
        } catch (JSONException e) {
            return false;
        }


        Arrival a = new Arrival(timeToStationStr, destinationName,platformName);

        String lineId = null;
        try {
            lineId = poiJsonObject.getString("lineId");
        } catch (JSONException e) {
            return false;
        }

        String lineName = null;
        try {
            lineName = poiJsonObject.getString("lineName");
        } catch (JSONException e) {
            return false;
        }

        LineResourceData LRD = LineResourceData.CENTRAL;
        switch (lineId.charAt(0)) {
            case 'c':
                LRD = LineResourceData.CENTRAL;
                break;
            case 'n':
                LRD = LineResourceData.NORTHERN;
                break;
            case 'p':
                LRD = LineResourceData.PICCADILLY;
                break;
            case 'v':
                LRD = LineResourceData.VICTORIA;
                break;
            case 'b':
                LRD = LineResourceData.BAKERLOO;
                break;
            case 'd':
                LRD = LineResourceData.DISTRICT;
                break;
            case 'j':
                LRD = LineResourceData.JUBILEE;
                break;
            default:  missingData=true;
        }

        if (missingData==false) {
            Line line = new Line(LRD, lineId, lineName);
            stn.addArrival(line, a);
            return true;
        }
        return false;
    }
}
