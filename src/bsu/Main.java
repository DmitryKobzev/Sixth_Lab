package bsu;

public class Main {
    public static void main(String[] args) {
        Work mhw = new Work();
        mhw.findTagAndWriteInList("input1.html");
        mhw.sortListByLengthTag();
        mhw.writeInFileList("output1.out");
        mhw.defineTextWithoutTag("input1.html");
        mhw.searchAndWriteResultInFiles(mhw.makeList("input2.in"),"output2.out", "output3.out");
    }
}
