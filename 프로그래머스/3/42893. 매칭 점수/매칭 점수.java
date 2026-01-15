import java.util.*;

class Solution {
    public int solution(String word, String[] pages) {
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> linkIndexes = new HashMap<>();
        int[] basicScores = new int[pages.length];
        int[] externalLinks = new int[pages.length];
        float[] links = new float[pages.length];
        word = word.toLowerCase();
        for (int i = 0; i < pages.length; i++) {
            // System.out.println(pages[i]);
            // 링크 찾기
            int linkStartIdx = pages[i].indexOf("<meta property=\"og:url\" content=\"") + 
                "<meta property=\"og:url\" content=\"".length();
            int linkEndIdx = pages[i].indexOf("\"", linkStartIdx);
            String link = pages[i].substring(linkStartIdx, linkEndIdx);
            map.put(link, new ArrayList<>());
            linkIndexes.put(link, i);
            // System.out.println("link: " + link);
            
            // 본문 찾기
            int bodyStartIdx = pages[i].indexOf("<body>") + "<body>".length();
            int bodyEndIdx = pages[i].indexOf("</body>", bodyStartIdx);
            String body = pages[i].substring(bodyStartIdx, bodyEndIdx);
            // System.out.println(body);
            // 링크 찾기
            while (body.contains("<a href")) {
                int hrefStartIdx = body.indexOf("<a href=\"") + "<a href=\"".length();
                int hrefEndIdx = body.indexOf("\"", hrefStartIdx);
                String href = body.substring(hrefStartIdx, hrefEndIdx);
                map.get(link).add(href);
                // System.out.println("href: " + href);
                int aStartIdx = body.indexOf("<a href=\"");
                int aEndIdx = body.indexOf("</a>", aStartIdx) + "</a>".length();
                body = body.substring(0, aStartIdx) + body.substring(aEndIdx, body.length());
                externalLinks[i]++;
            }
            // System.out.println(body);
            // System.out.println();
            
            body = body.toLowerCase();
            for (int j = 0; j <= body.length() - word.length(); j++) {
                String sub = body.substring(j, j + word.length());
                if (sub.equals(word)) {
                    boolean front = (j == 0 || !Character.isLetter(body.charAt(j - 1)));
                    boolean back = (j + word.length() >= body.length() || 
                                     !Character.isLetter(body.charAt(j + word.length())));
                    if (front && back) {
                        basicScores[i]++;
                    }
                }
            }
        }
        
        for (String myLink : map.keySet()) {
            // System.out.println("myLink: " + myLink);
            for (String externalLink : map.keySet()) {
                if (myLink.equals(externalLink)) {
                    continue;
                }
                for (String link : map.get(externalLink)) {
                    if (myLink.equals(link)) {
                        int myLinkIndex = linkIndexes.get(myLink);
                        int externalLinkIndex = linkIndexes.get(externalLink);
                        // System.out.println("externalLink: " + externalLink);
                        if (externalLinks[externalLinkIndex] > 0) {
                            links[myLinkIndex] += 
                                (float)(basicScores[externalLinkIndex]) / externalLinks[externalLinkIndex];   
                        }
                    }
                }
            }
        }
        int answer = 0;
        float max = basicScores[0] + links[0];
        for (int i = 0; i < pages.length; i++) {
            float matchingScore = basicScores[i] + links[i];
            if (max < matchingScore) {
                answer = i;
                max = matchingScore;
            }
        }
        return answer;
    }
}