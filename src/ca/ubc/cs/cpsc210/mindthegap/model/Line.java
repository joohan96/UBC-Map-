package ca.ubc.cs.cpsc210.mindthegap.model;

import java.util.*;

/**
 * Represents a line on the underground with a name, id, list of stations and list of branches.
 *
 * Invariants:
 * - no duplicates in list of stations
 * - iterator iterates over stations in the order in which they were added to the line
 */
public class Line implements Iterable<Station> {
    private List<Station> stns;
    private Set<Branch> branches;
    private LineResourceData lmd;
    private String id;
    private String name;

    /**
     * Constructs a line with given resource data, id and name.
     * List of stations and list of branches are empty.
     *
     * @param lmd     the line meta-data
     * @param id      the line id
     * @param name    the name of the line
     */
    public Line(LineResourceData lmd, String id, String name) {
        this.lmd=lmd;
        this.id=id;
        this.name=name;
        stns = new ArrayList<Station>();
        branches = new LinkedHashSet <Branch>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /**
     * Get colour specified by line resource data
     *
     * @return  colour in which to plot this line
     */
    public int getColour() {
        return lmd.getColour();

    }

    /**
     * Add station to line.
     *
     * @param stn  the station to add to this line
     */
    public void addStation(Station stn) {
        if(stns.contains(stn)==false) {
            stns.add(stn);
            stn.addLine(this);
        }
    }

    /**
     * Remove station from line
     *
     * @param stn  the station to remove from this line
     */
    public void removeStation(Station stn) {
        if(stns.contains(stn)) {
            stns.remove(stn);
            stn.removeLine(this);
        }


    }

    /**
     * Clear all stations from this line
     */
    public void clearStations() {
         List<Station> std = stns; ////////////////////changed
        Line stt = this;
        for(Station next :std){
            next.removeLine(stt);
        }

        stns.clear();
    }

    public List<Station> getStations() {
        return stns;   // stub
    }

    /**
     * Determines if this line has a stop at a given station
     *
     * @param stn  the station
     * @return  true if line has a stop at given station
     */
    public boolean hasStation(Station stn) {
        return (stns.contains(stn));   // stub
    }

    /**
     * Add a branch to this line
     *
     * @param b  the branch to add to line
     */
    public void addBranch(Branch b) {
        branches.add(b);
    }

    public Set<Branch> getBranches() {
        return branches;   // stub
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line stations = (Line) o;

        return !(id != null ? !id.equals(stations.id) : stations.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
