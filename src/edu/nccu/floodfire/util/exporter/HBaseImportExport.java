package edu.nccu.floodfire.util.exporter;

public class HBaseImportExport {
public String DEFAULT_COLUMN_FAMILY = "c1";
	
	public class HBaseCol {
		public String family;
		public String qualifier;
		public HBaseCol(String family,String qualifier) {
			this.family = family;
			this.qualifier = qualifier;
		}
	}

}
