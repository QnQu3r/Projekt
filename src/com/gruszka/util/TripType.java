package com.gruszka.util;

public enum TripType {
	TRIP("Trip"),
	TURNUS("Turnus");	

	    private final String text;

	    /**
	     * @param text
	     */
	    TripType(final String text) {
	        this.text = text;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return text;
	    }
}
