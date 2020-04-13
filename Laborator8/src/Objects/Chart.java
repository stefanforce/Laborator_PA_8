package Objects;

public class Chart {
    int id;
    int albumId;
    int rating;

    public Chart(int id, int albumId, int rating) {
        this.id = id;
        this.albumId = albumId;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
