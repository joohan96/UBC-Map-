package ca.ubc.cs.cpsc210.mindthegap.tests;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yi on 7/27/2015.
 */
public class ArrivalTest {
    private Arrival a;
    @Before
    public void setUp()  {
         a = new Arrival(752, "Walthamstow Central Underground Station","Northbound - Platform 6");
    }

    @Test
    public void testGetTravelDirn()  {
        String str = "Northbound";
        assertTrue(str.equals(a.getTravelDirn()));

        String str3 = "Southbound";
        assertFalse(str3.equals(a.getTravelDirn()));

    }


    @Test
    public void testGetPlatformName()  {
        String str= "Platform 6";
        assertTrue(str.equals(a.getPlatformName()));

    }

    @Test
    public void testGetTimeToStationInMins()  {
        int time=752;
        assertEquals(13, a.getTimeToStationInMins());
    }



}