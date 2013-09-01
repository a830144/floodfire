package edu.nccu.floodfire.flume;

import org.apache.flume.node.Application;

public class FlumeStrater {

	public static void main(String args[]) {
		FlumeStrater f = new FlumeStrater();
		f.run();
	}

	public void run() {
		try {
			String farg = "-fD:\\floodfire\\config\\flume-conf.properties";

			String narg = "-ntwitter";

			String[] args = new String[] { farg, narg };

			Application application = new Application();

			application.setArgs(args);

			if (application.parseOptions()) {
				application.run();
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
