package battleship;


import java.io.*;

public class Battleship {
    public static final String All_SHIPS_SUNK = "All ships sunk!";
    public static final String BAD_ARG_COUNT = "Wrong number of arguments for command";

    public static final String MISSING_SETUP_FILE = "No setup file specified.";

    public static final String MAX_DIM = "20";
    public static final String DIM_TOO_BIG = "Board dimensions too big to display";

    public static final String BAD_CONFIG_FILE = "Malformed board text file";
    public static final String PROMPT = "> ";

    public static final String WHITESPACE = "\\s+";

    /**
     * @param args command line args.
     * Reads the file and plays the game.
     * Trys to read the file and play game, catches the error
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(BAD_ARG_COUNT);
            return;
        }


        File file = new File(args[0]);
        PrintStream out = new PrintStream(System.out);

        try {
            out.println("Checking if" + args[0] + "is a saved game file...");

            ObjectInputStream ois;
            Board game;

            try {
                FileInputStream in = new FileInputStream(file);
                ois = new ObjectInputStream(in);
                game = (Board) ois.readObject();

            } catch (IOException|ClassNotFoundException e ) {
                out.println("no; will read as a text setup file.");

                BufferedReader br = new BufferedReader(new FileReader(file));
                String st = br.readLine();

                String[] stArray = st.split(" ");
                if(Integer.parseInt(stArray[0]) > Integer.parseInt(MAX_DIM )|| Integer.parseInt(stArray[1]) > Integer.parseInt(MAX_DIM)){
                    System.err.println(DIM_TOO_BIG);
                }
                game = new Board(Integer.parseInt(stArray[0]), Integer.parseInt(stArray[1]));

                st = br.readLine();
                while (st != null) {
                    if (st.equals(""))
                        break;
                    stArray = st.split(" ");
                    Ship ship = new Ship(game, Integer.parseInt(stArray[0]), Integer.parseInt(stArray[1]), Ship.Orientation.valueOf(stArray[2]), Integer.parseInt(stArray[3]));
                    game.addShip(ship);
                    st = br.readLine();


                }
                br.close();
            }
            String[] arr;

            //out.println();
            game.display(out);

            BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
            out.print(PROMPT);
            String line = reader.readLine();
            while (line != null) {
                if(game.allSunk()){
                    out.println(All_SHIPS_SUNK);
                    break;
                }
                arr = line.split(" ");
//                out.print(PROMPT);
                String command = arr[0];
                if (command.equals("h")) {
                    int enterRow = Integer.parseInt(arr[1]);
                    int enterCol = Integer.parseInt(arr[2]);

                    game.getCell(enterRow, enterCol).hit();
                    game.display(out);
                } else if (command.equals("!")) {
                    game.fullDisplay(out);
                } else if (command.equals("q")) {
                    break;
                } else if (command.equals("s")) {
                    FileOutputStream fos = new FileOutputStream(arr[1]);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(game);//object writes

                    oos.close();
                } else {
                    out.println("invalid command!");
                }
                out.print(PROMPT);
                line = reader.readLine();

            }


        } catch (FileNotFoundException e) {
            System.err.println(MISSING_SETUP_FILE);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (OverlapException e) {
            System.err.println(e.getMessage());
        } catch (OutOfBoundsException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(BAD_CONFIG_FILE);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(BAD_CONFIG_FILE);
        } catch (IllegalArgumentException e) {
            System.err.println(BAD_CONFIG_FILE);
        } catch (CellPlayedException e) {
            System.err.println(e.getMessage());

        } finally {
            out.close();
        }
    }
}


