/**
 * CLASSE MODEL REFACTORITZADA - COMMIT 9 (FINAL)
 * Patró aplicat: Move Method.
 * Es mou la lògica interna del comportament dels articles des de Magatzem cap a 
 * la pròpia classe Article, on resideixen les dades originals segons el PDF.
 */
class Article {
    private String nom;
    private int diesPerVendre;
    private int qualitat;

    // Constants mogudes al seu lloc corresponent de negoci
    public static final int MAX_QUALITAT = 50;
    public static final int QUALITAT_MINIMA = 0;
    public static final int LIMIT_DIES_URGENT = 11;
    public static final int LIMIT_DIES_CRITIC = 6;

    public Article(String nom, int diesPerVendre, int qualitat) {
        this.nom = nom;
        this.diesPerVendre = diesPerVendre;
        this.qualitat = qualitat;
    }

    // PATRÓ: Move Method (Lògica moguda aquí per evitar Feature Envy)
    public boolean esArticleEstandard() {
        return !this.nom.equals("Formatge Gidurat")
                && !this.nom.equals("Entrades per al Concert del Trobador");
    }

    public void disminuirQualitatEstandard() {
        if (this.qualitat > QUALITAT_MINIMA) {
            this.qualitat = this.qualitat - 1;
        }
    }

    public void aumentarQualitatEspecials() {
        if (this.qualitat >= MAX_QUALITAT) {
            return; 
        }

        this.qualitat = this.qualitat + 1;

        if (this.nom.equals("Entrades per al Concert del Trobador")) {
            if (this.diesPerVendre < LIMIT_DIES_URGENT && this.qualitat < MAX_QUALITAT) {
                this.qualitat = this.qualitat + 1;
            }
            if (this.diesPerVendre < LIMIT_DIES_CRITIC && this.qualitat < MAX_QUALITAT) {
                this.qualitat = this.qualitat + 1;
            }
        }
    }

    public void gestionarCaducitat() {
        if (this.nom.equals("Formatge Gidurat")) {
            if (this.qualitat < MAX_QUALITAT) {
                this.qualitat = this.qualitat + 1;
            }
        } else if (this.nom.equals("Entrades per al Concert del Trobador")) {
            this.qualitat = 0;
        } else {
            if (this.qualitat > QUALITAT_MINIMA) {
                this.qualitat = this.qualitat - 1;
            }
        }
    }

    // Getters i Setters (Commit 8)
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public int getDiesPerVendre() { return diesPerVendre; }
    public void setDiesPerVendre(int diesPerVendre) { this.diesPerVendre = diesPerVendre; }
    public int getQualitat() { return qualitat; }
    public void setQualitat(int qualitat) { this.qualitat = qualitat; }
}