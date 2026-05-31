/**
 * CLASSE MODEL REFACTORITZADA - COMMIT 8
 * Patró aplicat: Encapsulate Field.
 * Es canvien els atributs a 'private' i es generen getters i setters per protegir les dades.
 */
class Article {
    // PATRÓ: Encapsulate Field (Atributs privats)
    private String nom;
    private int diesPerVendre;
    private int qualitat;

    public Article(String nom, int diesPerVendre, int qualitat) {
        this.nom = nom;
        this.diesPerVendre = diesPerVendre;
        this.qualitat = qualitat;
    }

    // GETTERS I SETTERS
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDiesPerVendre() {
        return diesPerVendre;
    }

    public void setDiesPerVendre(int diesPerVendre) {
        this.diesPerVendre = diesPerVendre;
    }

    public int getQualitat() {
        return qualitat;
    }

    public void setQualitat(int qualitat) {
        this.qualitat = qualitat;
    }
}