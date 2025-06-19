public class ActionMovie extends Movie {

    private String mainActor;

    public ActionMovie(String title, String mainActor, String posterPath) {
        super(title, "액션", posterPath);
        this.mainActor = mainActor;
    }

    public String getMainActor() {
        return mainActor;
    }

    @Override
    public String toString() {
        return "제목 : " + getTitle() + "\n장르 : " + getGenre() + "\n주연배우 : " + mainActor;
    }
}
