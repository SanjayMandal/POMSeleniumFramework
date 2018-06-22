package com.utilities;

public interface iReport {



	void logStep(String sStatus, String stepName,String sactual, 
			boolean isScreenShot);

	void logStep(String sStatus, String stepName, String sactual);

	void endtest();
	 
}

