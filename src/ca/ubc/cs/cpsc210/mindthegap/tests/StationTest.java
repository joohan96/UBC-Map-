package ca.ubc.cs.cpsc210.mindthegap.tests;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import static ca.ubc.cs.cpsc210.mindthegap.util.SphericalGeometry.distanceBetween;
import static org.junit.Assert.*;

/**
 * Created by Yi on 7/28/2015.
 */
public class StationTest {
    private Station stn;
    private ArrivalBoard AB;
    private Line L;
    private Arrival a;
    @Before
    public void setUp()  {
        L = new Line (LineResourceData.CENTRAL, "id", "name");
        AB = new ArrivalBoard( L,"South");
        stn = new Station("id", "name", new LatLon(90, 90));
        a = new Arrival(752, "Walthamstow Central Underground Station","Northbound - Platform 6");
    }

    @Test
    public void testGetName()  {
        assertTrue("name".equals(stn.getName()));

    }

    @Test
    public void testGetLocn() {
        assertTrue(new LatLon(90, 90).equals(stn.getLocn()));

    }

    @Test
    public void testGetID()  {
        assertTrue("id".equals(stn.getID()));

    }

    @Test
    public void testGetLines()  {
        stn.addLine(L);
        assertTrue((stn.getLines()).size() == 1);

    }

    @Test
    public void testGetNumArrivalBoards()  {
        stn.addArrival(L, a);
        assertTrue(stn.getNumArrivalBoards()==1);


    }


    @Test
    public void testRemoveLine()  {
        stn.addLine(L);
        stn.removeLine(L);
        assertTrue((stn.getLines()).size() == 0);

    }

    @Test
    public void testHasLine()  {
        stn.addLine(L);
        assertTrue(stn.hasLine(L));

    }


    @Test
    public void testClearArrivalBoards()  {
        stn.addArrival(L,a );
        stn.clearArrivalBoards();
        assertTrue(stn.getNumArrivalBoards() == 0);

    }
}