public class SciFiMovie extends Movie {

    private String mainTheme;

    public SciFiMovie(String title, String mainTheme, String posterPath) {
        super(title, "SF", posterPath);
        this.mainTheme = mainTheme;
    }

    public String getMainTheme() {
        return mainTheme;
    }

    @Override
    public String toString() {
        return "제목 : " + getTitle() + "\n장르 : " + getGenre() + "\n영화주제 : " + mainTheme;
    }
}
