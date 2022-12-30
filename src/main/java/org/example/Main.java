package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FilmFinder finder = new FilmFinder();

        System.out.println(finder.getAllInfoBy(1));
        System.out.println(finder.getAllInfoBy(2));
        System.out.println(finder.getAllInfoBy(3));
        System.out.println(finder.getAllInfoBy(4));
        System.out.println(finder.getAllInfoBy(5));

    }
}

class FilmFinder {
    public FilmFinder() {
        init();
    }

    DataBase db;

    public DataBase getDb() {
        return db;
    }

    public String getAllInfoBy(int id) {
        Cinema c = db.films.get(id - 1);

        return String.format("%d %s %s %s %s %s",
                c.id,
                c.name,
                db.genres.get(c.genreId - 1).name,
                db.studios.get(c.creatorId - 1).titleName,
                db.year.get(c.creatorId - 1).year_ci,
                db.time.get(c.creatorId - 1).times);
    }

    DataBase init() {
        db = new DataBase();
        Cinema c1 = new Cinema(1, "Ад", 1, 1, 1);
        Cinema c2 = new Cinema(2, "Луч мечты", 4, 2, 4);
        Cinema c3 = new Cinema(3, "Fail", 3, 4, 3);
        Cinema c4 = new Cinema(4, "Жукии", 3, 3, 2);
        Cinema c5 = new Cinema(5, "Betmet", 3, 5, 5);

        db.films.add(c1);
        db.films.add(c2);
        db.films.add(c3);
        db.films.add(c4);
        db.films.add(c5);

        db.genres.add(new Genre(1, "Ужасы"));
        db.genres.add(new Genre(2, "Драма"));
        db.genres.add(new Genre(3, "Комедия"));
        db.genres.add(new Genre(4, "Мелодрама"));

        FilmCreatorFactory pf = new FilmCreatorFactory();

        db.addStudios(pf.getFilmProducer("Origin film studio"));
        db.addStudios(pf.getFilmProducer("Марвел"));
        db.addStudios(pf.getFilmProducer("Мосфильм"));
        db.addStudios(pf.getFilmProducer("DC"));
        db.addStudios(pf.getFilmProducer("WB"));

        db.year.add(new Years(1, "1998"));
        db.year.add(new Years(2, "2005"));
        db.year.add(new Years(3, "2012"));
        db.year.add(new Years(4, "2021"));
        db.year.add(new Years(5, "1985"));

        db.time.add(new Time(1, (float) 2.35));
        db.time.add(new Time(2, (float) 1.45));
        db.time.add(new Time(3, (float) 3.05));
        db.time.add(new Time(4, 2.50F));
        db.time.add(new Time(5, (float) 1.55));


        return db;
    }
}

class DataBase {
    ArrayList<Cinema> films = new ArrayList<>();
    ArrayList<Studio> studios = new ArrayList<>();
    ArrayList<Genre> genres = new ArrayList<>();
    ArrayList<Years> year = new ArrayList<>();
    ArrayList<Time> time = new ArrayList<>();

    public void addStudios(Studio producer) {
        studios.add(producer);
    }
}

class Time {
    int id;

    float times;

    public Time(int timesId, float timesCinema) {
        this.id = timesId;
        this.times = timesCinema;
    }


}
class Years {

    int id;

    int year_ci;

    public Years(int yearsId, String years) {
        this.id = yearsId;
        this.year_ci = Integer.parseInt(years);
    }

}

class Cinema {
    int id;
    int creatorId;
    String name;
    int genreId;
    int year_cinema;


    public Cinema(int cinemaId, String name, int genreId, int creatorId, int years_cinema) {
        this.id = cinemaId;
        this.creatorId = creatorId;
        this.name = name;
        this.genreId = genreId;
        this.year_cinema = years_cinema;

    }
}

class Studio {
    int id;
    String titleName;
}

class Genre {
    int id;
    String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class FilmCreatorFactory {
    int count = 1;

    public Studio getFilmProducer(String name) {
        Studio fp = new Studio();
        fp.id = count++;
        fp.titleName = name;
        return fp;
    }
}