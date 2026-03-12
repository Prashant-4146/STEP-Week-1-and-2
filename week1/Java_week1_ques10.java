import java.util.*;

class VideoData {
    String videoId;
    String content;

    VideoData(String videoId, String content) {
        this.videoId = videoId;
        this.content = content;
    }
}

public class Java_week1_ques10 {

    private LinkedHashMap<String, VideoData> L1;
    private LinkedHashMap<String, VideoData> L2;
    private HashMap<String, VideoData> L3;

    private int l1Hits = 0;
    private int l2Hits = 0;
    private int l3Hits = 0;

    public Java_week1_ques10() {

        L1 = new LinkedHashMap<String, VideoData>(10000, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> e) {
                return size() > 10000;
            }
        };

        L2 = new LinkedHashMap<String, VideoData>(100000, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> e) {
                return size() > 100000;
            }
        };

        L3 = new HashMap<>();

        L3.put("video_123", new VideoData("video_123", "Movie A"));
        L3.put("video_999", new VideoData("video_999", "Movie B"));
    }

    public VideoData getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            l1Hits++;
            System.out.println("L1 Cache HIT");
            return L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {
            l2Hits++;
            System.out.println("L2 Cache HIT");
            VideoData v = L2.get(videoId);
            L1.put(videoId, v);
            return v;
        }

        if (L3.containsKey(videoId)) {
            l3Hits++;
            System.out.println("L3 Database HIT");
            VideoData v = L3.get(videoId);
            L2.put(videoId, v);
            return v;
        }

        System.out.println("Video not found");
        return null;
    }

    public void getStatistics() {

        int total = l1Hits + l2Hits + l3Hits;

        double l1Rate = total == 0 ? 0 : (l1Hits * 100.0) / total;
        double l2Rate = total == 0 ? 0 : (l2Hits * 100.0) / total;
        double l3Rate = total == 0 ? 0 : (l3Hits * 100.0) / total;

        System.out.println("L1 Hit Rate: " + l1Rate + "%");
        System.out.println("L2 Hit Rate: " + l2Rate + "%");
        System.out.println("L3 Hit Rate: " + l3Rate + "%");
    }

    public static void main(String[] args) {

        Java_week1_ques10 cache = new Java_week1_ques10();

        cache.getVideo("video_123");
        cache.getVideo("video_123");
        cache.getVideo("video_999");

        cache.getStatistics();
    }
}
