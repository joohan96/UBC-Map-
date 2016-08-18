package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A parser for the data returned by TFL line route query
 */
public class TfLLineParser extends TfLAbstractParser {

    /**
     * Parse line from JSON response produced by TfL.  No stations added to line if TfLLineDataMissingException
     * is thrown.
     *
     * @param lmd line meta-data
     * @return line parsed from TfL data
     * @throws JSONException               when JSON data does not have expected format
     * @throws TfLLineDataMissingException when
     *                                     <ul>
     *                                     <li> JSON data is missing lineName, lineId or stopPointSequences elements </li>
     *                                     <li> for a given sequence: </li>
     *                                     <ul>
     *                                     <li> the stopPoint array is missing </li>
     *                                     <li> all station elements are missing one of name, lat, lon or stationId elements </li>
     *                                     </ul>
     *                                     </ul>
     */
    public static Line parseLine(LineResourceData lmd, String jsonResponse)
            throws JSONException, TfLLineDataMissingException {

        JSONObject poiJsonArray = new JSONObject(jsonResponse);

        String lineId = null;
        try {
            lineId = poiJsonArray.getString("lineId");
        } catch (JSONException e) {
            throw new TfLLineDataMissingException();
        }


        String lineName = null;
        try {
            lineName = poiJsonArray.getString("lineName");
        } catch (JSONException e) {
            throw new TfLLineDataMissingException();
        }

        Line line = new Line(lmd, lineId, lineName);
        JSONArray lineJsonArray = poiJsonArray.getJSONArray("lineStrings");///this was oneeeeeeeeeeeeeeee

        parseLS(line, lineJsonArray);


        JSONArray stationJsonArray = null;
        try {
            stationJsonArray = poiJsonArray.getJSONArray("stopPointSequences");
        } catch (JSONException e) {
            throw new TfLLineDataMissingException();
        }


        parseSPS(line, stationJsonArray);
        (StationManager.getInstance()).addStationsOnLine(line);
        return line;








    }



    private static void parseLS(Line line, JSONArray lineJsonArray) throws JSONException {
        for(int i = 0; i < lineJsonArray.length(); i++) {
            String LineString = lineJsonArray.getString(i);
            if (LineString != null) {
                line.addBranch(new Branch(LineString));
            }

        }

    }

    private static void parseSPS(Line line, JSONArray stationJsonArray) throws JSONException, TfLLineDataMissingException {

        int numOfWorkingStations = 0;
        for (int i = 0; i < stationJsonArray.length(); i++) {
            JSONArray JsonArraySP = null;
            try {
                JsonArraySP = (stationJsonArray.getJSONObject(i)).getJSONArray("stopPoint");
            } catch (JSONException e) {
                throw new TfLLineDataMissingException();
            }

            for (int j = 0; j < JsonArraySP.length(); j++) {
               boolean temp = parseStopPoint( line, JsonArraySP.getJSONObject(j));
                if(temp){
                    numOfWorkingStations++;
                }
            }
        if(numOfWorkingStations==0){
            throw new TfLLineDataMissingException();
        }

        }


    }

    private static boolean parseStopPoint( Line line, JSONObject jsonObject)  {

        String id = null;
        try {
            id = jsonObject.getString("stationId");
        } catch (JSONException e) {
            return false;
        }

        String name = null;
        try {
            name = jsonObject.getString("name");
        } catch (JSONException e) {
            return false;
        }

        String nameSimpple = TfLAbstractParser.parseName(name);
        double lat = 0;
        try {
            lat = jsonObject.getDouble("lat");
        } catch (JSONException e) {
           return false;
        }


        double lon = 0;
        try {
            lon = jsonObject.getDouble("lon");
        } catch (JSONException e) {
            return false;
        }


        LatLon latlon = new LatLon(lat, lon);

        if ((StationManager.getInstance()).getStationWithId(id) == null) {
                line.addStation(new Station(id, nameSimpple, latlon));
               return true;
            } else {
                line.addStation((StationManager.getInstance()).getStationWithId(id));
                return true;
            }







    }
}