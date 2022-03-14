import java.util.ArrayList;
import java.util.BitSet;

public class StartData {
    
    public ArrayList<Character> calls;
    public ArrayList<String> path;
    public BitSet rung;

    public StartData(ArrayList<Character> calls, ArrayList<String> path, BitSet rung) {
        this.calls = calls;
        this.path = path;
        this.rung = rung;
    }
}
