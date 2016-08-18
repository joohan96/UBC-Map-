package ca.ubc.cs.cpsc210.mindthegap.tests;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.ArrivalBoard;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yi on 7/27/2015.
 */
public class ArrivalBoardTest {
    private ArrivalBoard AB;
    private ArrivalBoard ABc;

    @Before
    public void setUp()  {

        Line line = new Line (null, "id", "name");
       AB = new ArrivalBoard( line,"South");
        ABc = new ArrivalBoard( line,"South");

    }



    @Test
    public void testGetNumArrivals()  {
        Arrival a = new Arrival(50, "destination","platform");
        Arrival b = new Arrival(70, "destination2","platform2");
        AB.addArrival(a);
        AB.addArrival(b);
        assertTrue(AB.getNumArrivals()==2);

    }


    @Test
    public void testClearArrivals()  {
        Arrival a = new Arrival(50, "destination","platform");
        Arrival b = new Arrival(70, "destination2","platform2");
        AB.addArrival(a);
        AB.addArrival(b);
        AB.clearArrivals();
        assertTrue(AB.getNumArrivals() == 0);

    }
    @Test
    public void testaddArrival(){
        Arrival a = new Arrival(50, "destination","platform");
        Arrival b = new Arrival(70, "destination2","platform2");
        AB.addArrival(a);
        AB.addArrival(b);
        Arrival c = (AB.getArrivals()).get(0);
        int f = c.getTimeToStationInMins();
        assertTrue(f == 1);

    }


}