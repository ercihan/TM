public class Band {

    int headposition = 0;
    String[] strip;

    public Band(int size) {
        strip = new String[size];
    }

    public void setBand(int number1, int number2) {

       strip[strip.length/2] = "1";

       for(int i = 0 ; i < number1; i++){
           strip[strip.length/2 - 1 - i] = "0";
       }

        for(int i = 0 ; i < number2; i++){
            strip[strip.length/2 + 1 + i] = "0";
        }

        setHead(strip.length/2-number1);

        setEmptyElements();
    }

    public void setEmptyElements() {
        for (int i=0;i<strip.length;i++){
            if (strip[i] == null){
                strip[i] = "_";
            }
        }
    }

    public void setHead(int position){
        headposition = position;
    }

    public String getStripElement(int position){
        return strip[position];
    }

    public int getHead(){
        return headposition;
    }

    public void setStripElement(int headposition, String symbol, Zustandssteuerung.Direction direccion) {
        strip[headposition] = symbol;
        if(direccion == Zustandssteuerung.Direction.LEFT){
            this.headposition--;
        } else if(direccion == Zustandssteuerung.Direction.RIGHT){
            this.headposition++;
        }
    }

    public int result() {
        int count = 0;
        for (int i = 0 ; i < strip.length;i++){
            if (strip[i].equals("0")){
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString(){

        String toString = "| ";

        int start = headposition -15;
        int end = headposition + 15;

        for (int i = start; i<end;i++){
            if (i<0 || i >= strip.length ){
                toString += "_ | ";
            }else {
                toString += strip[i] + " | ";
            }
        }

        return toString;
    }

    public String headToString() {
        String toString = "";

        for (int i = 0; i < 62;i++){
            toString+= " ";
        }

        return toString + "^";
    }
}
