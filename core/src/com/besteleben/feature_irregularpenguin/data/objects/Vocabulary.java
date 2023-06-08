package com.besteleben.feature_irregularpenguin.data.objects;

/**
 * eine DTO Klasse um die Kommunikation mit der Datenbank zu realisieren
 */
public class Vocabulary {
    /** id der vokabel */
    private int id;
    /** beinhaltet den rueckgabe wert german */
    private String german;
    /** das englische infinitive der vokabel */
    private String infinitive;
    /** die englische simple_past form der vokabel */
    private String simple_past;
    /** die englische partizip form */
    private String past_participle;

    /**
     * der Konstruktor der aufgerufen werden soll um das DTO mit der zugehoerigen vokabel aus der datenquelle zu befuellen
     * @param id fuer die id
     * @param german fuer die deutsche vokabel
     * @param infinitive die infinitive form der vokabel
     * @param simple_past die simple past form der vokabel
     * @param past_participle partizip form der vokabel
     */
    public Vocabulary(int id, String german, String infinitive, String simple_past, String past_participle) {
        this.id = id;
        this.german = german;
        this.infinitive = infinitive;
        this.simple_past = simple_past;
        this.past_participle = past_participle;
    }

    /**
     * funktion um das DTO formatiert auszugeben. Most likely für debugging
     * @return String des DTO und der enthaltenen Daten.
     */
    @Override
    public String toString() {
        return "Vocabulary{" +
                "id=" + id +
                ", german='" + german + '\'' +
                ", infinitive='" + infinitive + '\'' +
                ", simple_past='" + simple_past + '\'' +
                ", past_participle='" + past_participle + '\'' +
                '}';
    }

    /**
     * Gets german.
     *
     * @return value of german
     */
    public String getGerman() {
        return german;
    }

    /**
     * Gets infinitive.
     *
     * @return value of infinitive
     */
    public String getInfinitive() {
        return infinitive;
    }

    /**
     * Gets simple_past.
     *
     * @return value of simple_past
     */
    public String getSimple_past() {
        return simple_past;
    }

    /**
     * Gets past_participle.
     *
     * @return value of past_participle
     */
    public String getPast_participle() {
        return past_participle;
    }

    //todo String Methode + equals + getter and setter
}
