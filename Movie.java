import java.net.URL;
import javax.swing.ImageIcon;

public class Movie implements Recommendable {
    private String title;
    private String genre;
    private String posterPath;

    public Movie(String title, String genre, String posterPath) {
        this.title = title;
        this.genre = genre;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public ImageIcon getPosterIcon() {
        URL imgUrl = getClass().getClassLoader().getResource(posterPath);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "제목 : " + title + "\n장르 : " + genre;
    }

    @Override
    public String getRecommendation() {
        return "'" + title + "'을(를) 즐겨보세요!";
    }
}