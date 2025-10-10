import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public double readDouble() {
        return scanner.nextDouble();
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public String readString() {
        return scanner.next();
    }
}