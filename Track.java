/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    // cuenta las veces que se ha reproducido una cancion.
    private int playCount;
    //muestra el a�o de lanzamiento.
    private int fechaLanzamiento;

    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
        playCount= 0;
    }

    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
        playCount= 0;
        fechaLanzamiento= 0;
    }

    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ")"+" veces repoducida"+ playCount+"  Lanzamiento"+fechaLanzamiento;
    }

    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
    }

    /** 
     * este metodo se encarga de contar cuantas veces se ha reproducido cada
     * cancion
     */
    public void incrementPlayCount(){
        playCount++;
    }

    /**
     * El play count vuelve a 0
     */
    public void playCountReset(){
        playCount= 0;
    }

    /**
     * un metodo getter
     */
    public int getFechaLanzamiento(){
        return fechaLanzamiento;
    }
    
    /**
     * metodo setter
     */
    public void setFechaLanzamiento(int newFechaLanzamiento){
        this.fechaLanzamiento = newFechaLanzamiento;
    }
}
