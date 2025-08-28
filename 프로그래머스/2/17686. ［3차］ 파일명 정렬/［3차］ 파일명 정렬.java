import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Solution {

    public String[] solution(String[] files) {
        
        File[] fileArr = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            fileArr[i] = createFile(i, files[i]);
        }
        Arrays.sort(fileArr);        
        return Stream.of(fileArr).map(File::getOriginName).toArray(String[]::new);
    }
    
    private File createFile(int id, String originName) {
        Matcher headMatcher = Pattern.compile("([A-Za-z\\-\\.\\s]+)").matcher(originName);
        headMatcher.find();
        String head = headMatcher.group();
                
        Matcher numberMatcher = Pattern.compile("([0-9]+)").matcher(originName);
        numberMatcher.find();
        String number = numberMatcher.group();

        return new File(id, originName, head, number);
    }
    
    static class File implements Comparable<File> {
        private int id;
        private String originName;
        private String head;
        private int number;
        
        public File(int id, String originName, String head, String number) {
            this.id = id;
            this.originName = originName;
            this.head = head.toUpperCase();
            this.number = Integer.parseInt(number);
        }
        
        @Override
        public int compareTo(File other) {
            int headDiff = head.compareTo(other.head);
            if (headDiff == 0) {
                int numberDiff = Integer.compare(number, other.number);
                if (numberDiff == 0) {
                    return Integer.compare(id, other.id);
                }
                return numberDiff;
            }
            return headDiff;
        }
        
        public String getOriginName() {
            return originName;
        }
    }
}