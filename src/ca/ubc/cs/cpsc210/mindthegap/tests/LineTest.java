package ca.ubc.cs.cpsc210.mindthegap.tests;

import ca.ubc.cs.cpsc210.mindthegap.model.Branch;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yi on 7/28/2015.
 */
public class LineTest {
    private Line L;
    private Station stn;
    private Station stn2;
    private Branch br;
    private Set<Branch> branches;

    @Before
    public void setUp()  {
        L = new Line (LineResourceData.CENTRAL, "id", "name");
        stn = new Station("namee", "idd", new LatLon(90, 90));
        br= new Branch("branche");
        stn2 = new Station("nasdmee", "idd", new LatLon(20, 90));

    }

    @Test
    public void testGetName()  {
    assertTrue("name".equals(L.getName()));
    }

    @Test
    public void testGetId()  {
        assertTrue("id".equals(L.getId()));
    }

    @Test
    public void testGetColour()  {
        assertTrue(0xFFDC241F==(L.getColour()));
    }

    @Test
    public void testAddStation()  {
        L.addStation(stn);
        L.addStation(stn2);
        assertTrue(1 == (L.getStations()).size());

    }

    @Test
    public void testRemoveStation()  {
        L.addStation(stn);
        L.removeStation(stn);
        assertFalse(L.hasStation(stn));

    }

    @Test
    public void testClearStations()  {
        L.addStation(stn);
        L.clearStations();
        assertTrue(0 == (L.getStations()).size());

    }


    @Test
    public void testHasStation()  {
        L.addStation(stn);
        assertTrue(L.hasStation(stn));
    }

    @Test
    public void testAddBranch()  {
        L.addBranch(br);

        assertTrue((L.getBranches()).contains(br));

    }


}