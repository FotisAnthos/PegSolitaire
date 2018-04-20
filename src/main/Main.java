package main;

public class Main {

	public static void main(String[] args){
		String baseDebugPath = System.getProperty("user.dir");
		String defaultPuzzlePath = baseDebugPath + "\\test1.txt";
		String method, input_path, output_path;

		boolean debug = false;

		if(debug  == false) {//normal execution
			method = "";
			if(args[0].contentEquals("breadth"))
				method = "breadth";
			else if(args[0].contentEquals("depth"))
				method = "depth";
			else if(args[0].contentEquals("best"))
				method = "best";
			else syntax_message();

			if(args[1] != null)
				input_path = args[1];
			else input_path = defaultPuzzlePath;
			
			if(args[2] != null)
				output_path = args[2];
			else output_path = "C:\\Users\\fotis\\Desktop\\solution.txt";
		}

		else {//debug == true
			input_path = defaultPuzzlePath;
			output_path = baseDebugPath + "\\solution.txt";
			method = "best";
		}

		new Game(input_path, output_path, method);//create new game	
		return;

	}

	private static void syntax_message() {
		System.out.println("Use syntax:\n\n");
		System.out.println("\tfreeCell <method> <input_file> <output_file> \n\n");
		System.out.println("where:\n ");
		System.out.println("\t<method> = the method of AI solution to be used\n");
		System.out.println("\t<input_file> = String indicating the input file.\n");
		System.out.println("\t<output_file> = String indicating the output file.\n\n");
		//System.out.println("e.g. the call \n\n\t\n");
		System.out.println("Constraints: Possible methods: breadth, depth, best.");

		System.exit(2);
	}

}
