package ca.ubc.cs.cpsc210.mindthegap.model;



import java.util.*;

/**
 * Represents an arrivals board for a particular station, on a particular line,
 * for trains traveling in a particular direction (as indicated by platform prefix).
 *
 * Invariant: maintains arrivals in order of time to station
 * (first train to arrive will be listed first).
 */
public class ArrivalBoard implements Iterable<Arrival> {
    private List<Arrival> arrivals;
    private String travelDirn;
    private Line line;


    /**
     * Constructs an arrival board for the given line with an empty list of arrivals
     * and given travel direction.
     *
     * @param line        line on which arrivals listed on this board operate (cannot be null)
     * @param travelDirn  the direction of travel
     */
    public ArrivalBoard(Line line, String travelDirn) {
        arrivals = new ArrayList<Arrival>();
        this.travelDirn=travelDirn;
        this.line=line;
    }

    public Line getLine() {
        return line;

    }

    public String getTravelDirn() {

        return travelDirn;
    }

    public List<Arrival> getArrivals(){
        return arrivals;
    }


    /**
     * Get total number of arrivals posted on this arrival board
     *
     * @return  total number of arrivals
     */
    public int getNumArrivals() {
        return arrivals.size();

    }

    /**
     * Add a train arrival this arrivals board.
     *
     * @param arrival  the arrival to add to this arrivals board
     */
    public void addArrival(Arrival arrival) {
        int i=0;
        for(Arrival next : arrivals){
           if(arrival.getTimeToStationInMins() > next.getTimeToStationInMins()){
               i++;
           }
           if(arrival.getTimeToStationInMins() < next.getTimeToStationInMins()){
               break;
           }
        }
        arrivals.add(i,arrival);
    }

    /**
     * Clear all arrivals from this arrival board
     */
    public void clearArrivals() {
        arrivals = new ArrayList<Arrival>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalBoard arrivals = (ArrivalBoard) o;

        if (travelDirn != null ? !travelDirn.equals(arrivals.travelDirn) : arrivals.travelDirn != null) return false;
        return !(line != null ? !line.equals(arrivals.line) : arrivals.line != null);

    }

    @Override
    public int hashCode() {
        int result = travelDirn != null ? travelDirn.hashCode() : 0;
        result = 31 * result + (line != null ? line.hashCode() : 0);
        return result;
    }

    @Override
    /**
     * Produces an iterator over the arrivals on this arrival board
     * ordered by time to arrival (first train to arrive is produced
     * first).
     */
    public Iterator<Arrival> iterator() {
        // Do not modify the implementation of this method!
        return arrivals.iterator();
    }
}
