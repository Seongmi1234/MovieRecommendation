public class RomanceMovie extends Movie {
    private String mainCouple;

    public RomanceMovie(String title, String mainCouple, String posterPath) {
        super(title, "로맨스", posterPath);
        this.mainCouple = mainCouple;
    }

    public String getMainCouple() {
        return mainCouple;
    }

    @Override
    public String toString() {
        return "제목 : " + getTitle() + "\n장르 : " + getGenre() + "\n주연커플 : " + mainCouple;
    }
}
