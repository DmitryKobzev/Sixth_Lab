package bsu;

import java.util.*;

public class Work {
    private List<String> tag;
    private String textWithoutTag;
    private String inputText;
    Work(){
        tag = new ArrayList<>();
    }
    public void findTagAndWriteInList(String file){
        inputText = new String(WorkWithFile.read(file));
        Scanner sc = new Scanner(inputText);
        StringBuffer sb = new StringBuffer();
        boolean isOpenTag = false;
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '<' && ((i!=str.length()-1)&&(str.charAt(i+1)!='/'))){
                    isOpenTag = true;
                }
                else if(isOpenTag && str.charAt(i)!='>'){
                    sb.append(str.charAt(i));
                }
                else if(str.charAt(i)=='>' && isOpenTag) {
                    isOpenTag = false;
                    tag.add(sb.toString());
                    sb.delete(0,sb.length());
                }
            }
        }
    }
    public void sortListByLengthTag(){
        Collections.sort(tag, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
    }
    public void outputList(){
        System.out.println(tag);
    }
    public void writeInFileList(String file){
        StringBuffer sb = new StringBuffer();
        for(String item:tag){
            sb.append(item + "\n");
        }
        WorkWithFile.write(file,sb.toString());
    }
    public void defineTextWithoutTag(String file) {
        String text = new String(WorkWithFile.read(file));
        Scanner sc = new Scanner(text);
        StringBuffer sb = new StringBuffer();
        boolean isOpenTag = false;
        boolean fileIsWritten = false;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '<' && !isOpenTag) {
                    isOpenTag = true;
                }
                if (!isOpenTag) {
                    sb.append(str.charAt(i));
                }
                if (str.charAt(i) == '>' && isOpenTag) {
                    isOpenTag = false;
                }
            }
            sb.append("\n");
        }
        textWithoutTag = new String(sb.toString());
    }
    public void searchAndWriteResultInFiles(List<String>words, String to1,String to2) {
        int count = -1;
        Scanner sc = new Scanner(textWithoutTag);
        List<Integer>listInteger = new ArrayList<>();
        while (sc.hasNextLine()) {
            count++;
            String str = new String(sc.nextLine().toLowerCase());
            Iterator<String>iter = words.iterator();
            while(iter.hasNext()){
                String word = iter.next().toLowerCase();
                if(str.contains(word)){
                    listInteger.add(count);
                    iter.remove();
                }
            }
        }
        for(String item:words){
            listInteger.add(-1);
        }
        StringBuffer s = new StringBuffer();
        for(Integer item:listInteger){
            s.append(item + "\n");
        }
        WorkWithFile.write("output2.out" ,s.toString());
        s.delete(0,s.length());
        for(String item:words){
            s.append(item + "\n");
        }
        WorkWithFile.write("output3.out" ,s.toString());

    }
    public List<String> makeList(String fileName) {
        String subtext = WorkWithFile.read(fileName);
        List<String>words = new ArrayList<>();
        Scanner sc = new Scanner(subtext).useDelimiter("[; \n]+");
        while(sc.hasNext()){
            words.add(sc.next());
        }
        return words;
    }
}
