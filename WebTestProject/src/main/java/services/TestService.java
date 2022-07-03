package services;

public class TestService {
	private final static String PX = "px";
	private final static String RGB = "rgb";
	private final static String SEMICOLON = ";";
	public static int compareFontSizePx(String fontX, String fontY) {
		int intFontx = Integer.parseInt(fontX.substring(0,fontX.indexOf(PX)));
		int intFonty = Integer.parseInt(fontY.substring(0,fontY.indexOf(PX)));
		if(intFontx == intFonty) {
			return 0;
		}
		else if(intFontx > intFonty){
			return 1;
		}
		else {
			return 2;
		}
	}
	
	public static String getRgbColorFromString(String rgbColorRaw) {
		if(!rgbColorRaw.isBlank()) {
			return rgbColorRaw.substring(rgbColorRaw.indexOf(RGB),rgbColorRaw.indexOf(SEMICOLON));
		}
		else {
			return rgbColorRaw;
		}

	}

}
