package bsu;

public class Main {
    public static void main(String[] args) {
        Work call=new Work();
        call.FindTagAndWriteInList("input1.html");
        call.defineTextWithOutTag("input1.html");
        call.OutputList();
        call.sortListByTag();
        call.OutputList();
        call.WriteInFile("output1.txt");
    }
}
