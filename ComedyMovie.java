public class ComedyMovie extends Movie {

    private String director;

    public ComedyMovie(String title, String director, String posterPath) {
        super(title, "코미디", posterPath);
        this.director = director;
    }
    
    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "제목 : " + getTitle() + "\n장르 : " + getGenre() + "\n영화감독 : " + director;
    }
}
