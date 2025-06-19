import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MovieMain extends JFrame {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private JTextField genreInput = new JTextField(15);
    private JPanel movieContainer = new JPanel(new GridLayout(0, 2, 30, 30));
    private JPanel resultPanel = new JPanel(new BorderLayout());

    public MovieMain() {
        setTitle("영화 추천 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);

        movieList.add(new ComedyMovie("쥬만지", "조 존스톤", "posters/쥬만지.jpeg"));
        movieList.add(new ComedyMovie("극한직업", "이병헌", "posters/극한직업.jpeg"));
        movieList.add(new ActionMovie("범죄도시2", "마동석", "posters/범죄도시2.jpeg"));
        movieList.add(new ActionMovie("어벤져스", "로버트 다우니 주니어", "posters/어벤져스.jpeg"));
        movieList.add(new RomanceMovie("노트북", "라이언 고슬링 & 레이첼 맥아담스", "posters/노트북.jpeg"));
        movieList.add(new RomanceMovie("라라랜드", "라이언 고슬링 & 엠마 스톤", "posters/라라랜드.jpeg"));
        movieList.add(new SciFiMovie("인터스텔라", "상대성 이론", "posters/인터스텔라.jpeg"));
        movieList.add(new SciFiMovie("마션", "화성 탐사", "posters/마션.jpeg"));

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        topPanel.add(new JLabel("(액션/로맨스/SF/코미디)"), gbc);

        gbc.gridx = 1;
        topPanel.add(genreInput, gbc);

        JButton searchButton = new JButton("검색");
        JButton showAllButton = new JButton("전체 영화 보기");

        gbc.gridx = 2;
        topPanel.add(searchButton, gbc);
        gbc.gridx = 3;
        topPanel.add(showAllButton, gbc);

        resultPanel.add(movieContainer, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(_ -> searchByGenre());
        showAllButton.addActionListener(_ -> showAllMovies());

        setVisible(true);
    }

    private JPanel createMoviePanel(Movie movie) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(300, 500));

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(movie.getPosterPath()));
        Image image = icon.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
        JLabel poster = new JLabel(new ImageIcon(image));
        poster.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(poster);
        panel.add(Box.createVerticalStrut(10));

        JLabel title = new JLabel("제목 : " + movie.getTitle(), SwingConstants.CENTER);
        JLabel genre = new JLabel("장르 : " + movie.getGenre(), SwingConstants.CENTER);
        JLabel extra;
        if (movie instanceof ActionMovie)
            extra = new JLabel("주연배우 : " + ((ActionMovie) movie).getMainActor(), SwingConstants.CENTER);
        else if (movie instanceof RomanceMovie)
            extra = new JLabel("주연커플 : " + ((RomanceMovie) movie).getMainCouple(), SwingConstants.CENTER);
        else if (movie instanceof SciFiMovie)
            extra = new JLabel("영화주제 : " + ((SciFiMovie) movie).getMainTheme(), SwingConstants.CENTER);
        else
            extra = new JLabel("영화감독 : " + ((ComedyMovie) movie).getDirector(), SwingConstants.CENTER);
        JLabel rec = new JLabel(movie.getRecommendation(), SwingConstants.CENTER);

        for (JLabel label : new JLabel[]{title, genre, extra, rec}) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        return panel;
    }

    private void searchByGenre() {
        String genre = genreInput.getText().trim();
        movieContainer.removeAll();

        ArrayList<Movie> matches = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getGenre().equalsIgnoreCase(genre)) matches.add(movie);
            if (matches.size() == 2) break;
        }

        if (matches.isEmpty()) {
            movieContainer.add(new JLabel("해당 장르의 영화를 찾을 수 없습니다."));
        } else {
            for (Movie movie : matches) {
                movieContainer.add(createMoviePanel(movie));
            }
        }

        revalidate();
        repaint();
    }

    private void showAllMovies() {
        movieContainer.removeAll();
        for (Movie movie : movieList) {
            movieContainer.add(createMoviePanel(movie));
        }
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new MovieMain();
    }
}
