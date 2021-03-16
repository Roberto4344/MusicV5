import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    //pregunta si hay canciones
    private boolean hayMusica;
    //genera un random 
    Random index = new Random();

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer(String carpeta)
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary(carpeta);
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        hayMusica=false;
    }

    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }

    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }

    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index){
        if(hayMusica == true){
            System.out.println("Ya hay una cancion reproduciendose");
        }
        else if(indexValid(index)) {
            Track track = tracks.get(index);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            track.incrementPlayCount();
            hayMusica = true;
        }
    }

    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }

    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }

    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }

    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }

    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst(){
        if(hayMusica == true){
            System.out.println("Ya hay una cancion reproduciendose");
        }
        else if(tracks.size() > 0) {
            player.startPlaying(tracks.get(0).getFilename());
            tracks.get(0).incrementPlayCount();
            hayMusica = true;
        }       
    }

    /**
     * Stop the player.
     */
    public void stopPlaying(){
        if(hayMusica == true){
            player.stop();
            hayMusica = false;
        }
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;

        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }

    /**
     * Enumera todas las pistas que contengan  la cadena buscada.
     */
    public void findInTitle(String searchString){
        for(Track track : tracks){
            String title = track.getTitle();
            if(title.contains(searchString)){
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * Cambia el año de lanzamiento de una cancion
     */
    public void setLanzamiento(int index,int newFechaLanzamiento){
        if(index < 0) {
            System.out.println("no valido");
        }
        else if(index >= tracks.size()) {
            System.out.println("no valido el maximo es "+ getNumberOfTracks());
        }
        else{
            tracks.get(index).setFechaLanzamiento(newFechaLanzamiento);
        }
    }

    /**
     * creamos un metodo que nos permite saber que cancion esta reproduciendose 
     */
    public void isPlaying(){

        if(hayMusica==true){
            System.out.println("The music is playing");
        }
        else{
            System.out.println( "The music is off");
        }
    }

    /**
     * Muestra los detalles con un iterador
     */
    public void listAllTrackWithIterator(){       
        Iterator <Track> it = tracks.iterator();
        while (it.hasNext()){
            Track t = it.next();            
            System.out.println(t.getDetails());            
        }
    }

    /**
     * Elimina una cancion por el artista
     */
    public void removeByArtist(String artista){
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()) {
            Track t = it.next();
            String artist = t.getArtist();
            if (artist.contains(artista)) {
                it.remove();
            }
        }
    }

    /**
     * Elimina una canion por titulo
     */
    public void removeByTitle(String titulo){       
        Iterator <Track> it = tracks.iterator();
        while (it.hasNext()){
            Track t = it.next();
            String title = t.getTitle();
            if(title.contains(titulo)){
                it.remove();
            }
        }
    }

    /**
     * nos reproduce una cancion al azar
     */
    public void playRandom(){
        int num= index.nextInt(tracks.size());
        if(hayMusica == true){
            System.out.println("Ya hay una cancion reproduciendose");
        }
        else if(indexValid(num)) {
            Track track = tracks.get(num);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            track.incrementPlayCount();
            hayMusica = true;
        }
    }

    /**
     * introduciomos el metodo play sufle que reproduce todas las canciones un poco
     */
    public void playShuffle(){
        ArrayList<Track> copia = new ArrayList(); 
        copia = (ArrayList)tracks.clone();
        while(copia.size()>0){
            int num = index.nextInt(copia.size());
            Track track = copia.get(num);
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            player.playSample(track.getFilename());
            copia.remove(num);
        }
    }
}

