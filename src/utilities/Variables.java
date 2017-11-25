package utilities;

public class Variables {
	public static int[] color = new int[] {
		0,
		0,
		0,
		255,
	};
	
	public static enum tools {
		PENCIL,
		ERASER,
		PIPETTE,
		FILL_BUCKET,
	}
	
	public static tools currentTool = tools.PENCIL;
	public static int brushSize 	= 5;
	public static double interval 	= 0.5;
}