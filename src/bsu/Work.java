package bsu;

import java.util.*;


public class Work  {
        private List<String> tag;
        private String textWithOutTag;
        Work(){
            tag=new ArrayList<>();
        }
        public void FindTagAndWriteInList(String nameOfFile){
            String str=new String(WorkWithFile.read(nameOfFile));
                Scanner sc=new Scanner(str);
                StringBuffer sb=new StringBuffer();
                boolean isOpenTag=false;
                while(sc.hasNextLine()){
                        String str_work=sc.nextLine();
                        for(int i=0;i<str_work.length();i++){
                                if(str_work.charAt(i)=='<' && ((i!=str_work.length()-1) &&(str_work.charAt(i+1)!='/'))){
                                        isOpenTag=true;
                                }
                                else if(isOpenTag && str_work.charAt(i)!='>'){
                                        sb.append(str_work.charAt(i));
                                }
                                else if(str_work.charAt(i)== '>' && isOpenTag){
                                        isOpenTag=false;
                                        tag.add(sb.toString());
                                        sb.delete(0,sb.length());
                                }
                        }

                }
        }
        public void defineTextWithOutTag(String nameOfFile) {
            String str = new String(WorkWithFile.read(nameOfFile));
            Scanner sc = new Scanner(str);
            StringBuffer sb = new StringBuffer();
            boolean isOpenTag = false;
            while (sc.hasNextLine()) {
                String str_work = sc.nextLine();
                for (int i=0;i<str_work.length();i++){
                    if(str_work.charAt(i)=='<' && !isOpenTag) {
                        isOpenTag = true;
                    }
                        if(!isOpenTag){
                            sb.append(str_work.charAt(i));
                        }
                     if(str_work.charAt(i)=='>' && isOpenTag){
                        isOpenTag=false;
                    }
                }
            }
            textWithOutTag=new String(sb.toString());
            System.out.println(textWithOutTag);
        }
        public void sortListByTag(){
            Collections.sort(tag, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length()-o2.length();
                }
            });
        }
        public void OutputList(){
            System.out.println(tag);
        }
        public void WriteInFile(String outputfile){
            StringBuffer sb=new StringBuffer();
            for(String item:tag){
                sb.append(item + "\n");
            }
            WorkWithFile.write(outputfile,sb.toString());
        }
}
