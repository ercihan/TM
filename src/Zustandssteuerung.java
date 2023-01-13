import java.util.Scanner;

public class Zustandssteuerung {

    enum Direction {
        LEFT,
        RIGHT,
        STAY
    }

    int number1 =0;
    int number2 =0;
    int modus = 0;

    Band bandUp;
    Band bandDown;

    int calculationCount = 0;

    public Zustandssteuerung(){

        readNumbers();

        bandUp = new Band(number1 * number2 +100);
        bandDown = new Band(number1 * number2 +100);

        bandUp.setBand(number1,number2);
        bandDown.setHead(0);
        bandDown.setEmptyElements();

        String state = "q0"; //Anfangszustand

        console(state);
        if (modus == 0){
            System.out.println("Zum Fortfahren Enter drücken");
            try
            {
                System.in.read();
            }
            catch(Exception e)
            {}
        }


        do {
            switch(state) {
                case "q0":
                    state = q0();
                    break;
                case "q1":
                    state = q1();
                    break;
                case "q2":
                    state = q2();
                    break;
                case "q3":
                    state = q3();
                    break;
                default:
                    System.out.println("no match");
            }
            calculationCount++;

            console(state);
            if (modus == 0){
                System.out.println("Zum Fortfahren Enter drücken");
                try
                {
                    System.in.read();
                }
                catch(Exception e)
                {}
            }
        }while ((!state.equals("q4")) || state.equals("q99"));


        System.out.println("-----------------------");
        System.out.println("Berechnungsschritt: "+calculationCount);
        System.out.println("Zustand: "+state);
        System.out.println("Ergebnis: "+bandDown.result());
        System.out.println("-----------------------");
    }


    private void readNumbers(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Geben Sie Zahl 1 ein");
        number1 = scan.nextInt();
        System.out.println("Geben Sie Zahl 2 ein");
        number2 = scan.nextInt();

        do {
            System.out.println("Tippen Sie 0 für Step Modus oder 1 für Laufmodus");
            modus = scan.nextInt();
        }while (modus != 0 && modus != 1);


    }

    private void console(String state){
        System.out.println("-----------------------");
        System.out.println("Berechnungsschritt: "+calculationCount);
        System.out.println("Zustand: "+state);
        System.out.println("Band Oben:\n"+bandUp.toString());
        System.out.println(bandUp.headToString());
        System.out.println("Band Unten:\n"+bandDown.toString());
        System.out.println(bandDown.headToString());
        System.out.println("-----------------------");
    }



    private String q0(){
        if (bandUp.getStripElement(bandUp.getHead()).equals("0") && bandDown.getStripElement(bandDown.getHead()).equals("_")){
            bandUp.setStripElement(bandUp.getHead(),"_", Direction.RIGHT);
            bandDown.setStripElement(bandDown.getHead(),"_", Direction.STAY);
            return "q1";
        }
        if (bandUp.getStripElement(bandUp.getHead()).equals("1") && bandDown.getStripElement(bandDown.getHead()).equals("_")){
            bandUp.setStripElement(bandUp.getHead(),"0", Direction.STAY);
            bandDown.setStripElement(bandDown.getHead(),"_", Direction.STAY);
            return "q4";
        }

        return "q99";
    }

    private String q1() {
        if (bandUp.getStripElement(bandUp.getHead()).equals("0") && bandDown.getStripElement(bandDown.getHead()).equals("_")) {
            bandUp.setStripElement(bandUp.getHead(),"0", Direction.RIGHT);
            bandDown.setStripElement(bandDown.getHead(),"_", Direction.STAY);

            return "q1";
        }

        if (bandUp.getStripElement(bandUp.getHead()).equals("1") && bandDown.getStripElement(bandDown.getHead()).equals("_")) {
            bandUp.setStripElement(bandUp.getHead(),"0", Direction.RIGHT);
            bandDown.setStripElement(bandDown.getHead(),"_", Direction.STAY);

            return "q2";
        }

        return "q99";
    }

    private String q2(){
        if (bandUp.getStripElement(bandUp.getHead()).equals("0") && bandDown.getStripElement(bandDown.getHead()).equals("_")){
            bandUp.setStripElement(bandUp.getHead(),"1", Direction.LEFT);
            bandDown.setStripElement(bandDown.getHead(),"_", Direction.STAY);
            return "q3";
        }
        if (bandUp.getStripElement(bandUp.getHead()).equals("_") && bandDown.getStripElement(bandDown.getHead()).equals("_")){
            bandUp.setStripElement(bandUp.getHead(),"_", Direction.STAY);
            bandDown.setStripElement(bandDown.getHead(),"_", Direction.STAY);
            return "q4";
        }

        return "q99";
    }

    private String q3(){
        if (bandUp.getStripElement(bandUp.getHead()).equals("0") && bandDown.getStripElement(bandDown.getHead()).equals("_")){
            bandUp.setStripElement(bandUp.getHead(),"0", Direction.LEFT);
            bandDown.setStripElement(bandDown.getHead(),"0", Direction.RIGHT);
            return "q3";
        }
        if (bandUp.getStripElement(bandUp.getHead()).equals("_") && bandDown.getStripElement(bandDown.getHead()).equals("_")){
            bandUp.setStripElement(bandUp.getHead(),"_", Direction.RIGHT);
            bandDown.setStripElement(bandDown.getHead(),"_", Direction.STAY);
            return "q0";
        }

        return "q99";
    }


}
